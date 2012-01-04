/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.instrument.handler;

import java.lang.annotation.Annotation;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import net.sf.staccatocommons.instrument.context.MethodAnnotationContext;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Interface for annotation handlers that can process annotations in methods.
 * <p>
 * This interface exposes both a
 * {@link #preProcessAnnotatedMethod(Annotation, MethodAnnotationContext)} and
 * {@link #postProcessAnnotatedMethod(Annotation, MethodAnnotationContext)}, in
 * order to let clients perform any instrumentation or context update whenever
 * they need - after, before or around processing argument annotations.
 * </p>
 * <p>
 * Although in most scenarios implementing the handling logic in either on
 * preprocesing or postprocessing is the same, there are specific cases where
 * the distinction is important. Typical case is when a
 * {@link MethodAnnotationHandler} instruments a method by surrounding it with
 * some kind of block - a try-catch, for instance: if that instrumentation is
 * performing during preprocessing, and then, an
 * {@link ArgumentAnnotationHandler} that discovers an annotation on the same
 * method inserts some portion of code, that code may be placed after or before
 * the block. However, if performed on postprocessing, such portion of code will
 * be always surrounded by the block.
 * </p>
 * 
 * 
 * @param <A>
 *          the type of annotation this handler can process
 * @author flbulgarelli
 * @see AnnotationHandler
 */
public interface MethodAnnotationHandler<A extends Annotation> extends AnnotationHandler<A> {

  /**
   * Process an <code>annotation</code> discovered in a method by the
   * instrumenter, instrumenting the given <code>context</code> where the
   * annotation was found. This message is sent by the instrumenter before
   * discovering any argument annotation.
   * 
   * @param annotation
   *          the annotation to process
   * @param context
   *          the instrumentable context where the annotation was found
   * @throws CannotCompileException
   *           if compilation errors occur during instrumentation
   * @throws NotFoundException
   *           if any type needed by instrumentation was not found
   */
  void preProcessAnnotatedMethod(@NonNull A annotation, @NonNull MethodAnnotationContext context)
    throws CannotCompileException, NotFoundException;

  /**
   * Process an <code>annotation</code> discovered in a method by the
   * instrumenter, instrumenting the given <code>context</code> where the
   * annotation was found. This message is sent by the instrumenter after
   * discovering and processing all arguments annotations.
   * 
   * @param annotation
   *          the annotation to process
   * @param context
   *          the instrumentable context where the annotation was found
   * @throws CannotCompileException
   *           if compilation errors occur during instrumentation
   * @throws NotFoundException
   *           if any type needed by instrumentation was not found
   */
  void postProcessAnnotatedMethod(@NonNull A annotation, @NonNull MethodAnnotationContext context)
    throws CannotCompileException, NotFoundException;

}
