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

	void preProcessAnnotatedConstructor(@NonNull A annotation,
		@NonNull ConstructorAnnotationContext context) throws CannotCompileException;

	void postProcessAnnotatedConstructor(@NonNull A annotation,
		@NonNull ConstructorAnnotationContext context) throws CannotCompileException;

}
