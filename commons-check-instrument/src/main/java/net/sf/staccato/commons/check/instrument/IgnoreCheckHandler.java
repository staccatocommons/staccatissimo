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
package net.sf.staccato.commons.check.instrument;

import java.lang.annotation.Annotation;

import net.sf.staccato.commons.instrument.context.ConstructorAnnotationContext;
import net.sf.staccato.commons.instrument.context.MethodAnnotationContext;
import net.sf.staccato.commons.instrument.handler.AbstractDeactivatorAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.ClassAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.ConstructorAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccato.commons.lang.check.annotation.IgnoreChecks;

/**
 * @author flbulgarelli
 * 
 */
public class IgnoreCheckHandler extends AbstractDeactivatorAnnotationHandler implements
	ClassAnnotationHandler, ConstructorAnnotationHandler, MethodAnnotationHandler {

	/**
	 * Creates a new {@link IgnoreCheckHandler}
	 */
	public IgnoreCheckHandler() {
		super();
	}

	public Class<? extends Annotation> getSupportedAnnotationType() {
		return IgnoreChecks.class;
	}

	public void preProcessAnnotatedMethod(Object annotation, MethodAnnotationContext context) {
		onPreProcess();
	}

	public void postProcessAnnotatedMethod(Object annotation, MethodAnnotationContext context) {
		onPostProcess();
	}

	public void preProcessAnnotatedConstructor(Object annotation, ConstructorAnnotationContext context) {
		onPreProcess();
	}

	public void postProcessAnnotatedConstructor(Object annotation,
		ConstructorAnnotationContext context) {
		onPostProcess();
	}

	public void preProcessAnnotatedClass(Object annotation) {
		onPreProcess();
	}

	public void postProcessAnnotatedClass(Object annotation) {
		onPostProcess();
	}
}
