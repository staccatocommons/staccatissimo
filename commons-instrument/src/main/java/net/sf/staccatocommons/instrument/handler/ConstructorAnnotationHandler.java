/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.instrument.handler;

import java.lang.annotation.Annotation;

import javassist.CannotCompileException;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.instrument.context.ConstructorAnnotationContext;

/**
 * @author flbulgarelli
 * 
 */
public interface ConstructorAnnotationHandler<A extends Annotation> extends AnnotationHandler<A> {

	/**
	 * Process an <code>annotation</code> discovered in a constructor by the
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
	 */
	void preProcessAnnotatedConstructor(@NonNull A annotation,
		@NonNull ConstructorAnnotationContext context) throws CannotCompileException;

	/**
	 * Process an <code>annotation</code> discovered in a constructor by the
	 * instrumenter, instrumenting the given <code>context</code> where the
	 * annotation was found. This message is sent by the instrumenter after
	 * discovering all arguments annotations.
	 * 
	 * @param annotation
	 *          the annotation to process
	 * @param context
	 *          the instrumentable context where the annotation was found
	 * @throws CannotCompileException
	 *           if compilation errors occur during instrumentation
	 */
	void postProcessAnnotatedConstructor(@NonNull A annotation,
		@NonNull ConstructorAnnotationContext context) throws CannotCompileException;

}
