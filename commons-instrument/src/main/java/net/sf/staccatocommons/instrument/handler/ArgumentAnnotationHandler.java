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
import net.sf.staccatocommons.instrument.context.ArgumentAnnotationContext;

/**
 * @author flbulgarelli
 * 
 */
public interface ArgumentAnnotationHandler<A extends Annotation> extends AnnotationHandler<A> {

	void processAnnotatedArgument(A annotation, ArgumentAnnotationContext context)
		throws CannotCompileException;

}