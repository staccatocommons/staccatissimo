/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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
import net.sf.staccatocommons.instrument.context.ArgumentAnnotationContext;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Interface for annotation handlers that can process annotations in method or
 * constructor arguments
 * 
 * @author flbulgarelli
 * @see AnnotationHandler
 */
public interface ArgumentAnnotationHandler<A extends Annotation> extends AnnotationHandler<A> {

  /**
   * Process an annotation discovered by the instrumenter, instrumenting the
   * given <code>context</code> where the annoation was found
   * 
   * @param annotation
   *          the annotation to process
   * @param context
   *          the instrumentable context where the annotation was found
   * @throws CannotCompileException
   *           if any compilation errors occurs
   * @throws NotFoundException
   *           if any type needed by instrumentation was not found
   */
  void processAnnotatedArgument(@NonNull A annotation, @NonNull ArgumentAnnotationContext context)
    throws CannotCompileException, NotFoundException;

}
