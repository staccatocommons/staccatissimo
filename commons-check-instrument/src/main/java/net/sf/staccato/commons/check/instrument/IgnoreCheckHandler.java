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

import net.sf.staccato.commons.check.annotation.IgnoreChecks;
import net.sf.staccato.commons.instrument.context.ConstructorAnnotationContext;
import net.sf.staccato.commons.instrument.context.MethodAnnotationContext;
import net.sf.staccato.commons.instrument.handler.ClassAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.ConstructorAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.deactivator.AbstractActivationAnnotationHandler;

/**
 * @author flbulgarelli
 * 
 */
public class IgnoreCheckHandler extends AbstractActivationAnnotationHandler<IgnoreChecks> implements
	ClassAnnotationHandler<IgnoreChecks>, ConstructorAnnotationHandler<IgnoreChecks>,
	MethodAnnotationHandler<IgnoreChecks> {

	/**
	 * Creates a new {@link IgnoreCheckHandler}
	 */
	public IgnoreCheckHandler() {
		super();
	}

	public Class<IgnoreChecks> getSupportedAnnotationType() {
		return IgnoreChecks.class;
	}

	public void preProcessAnnotatedMethod(IgnoreChecks annotation, MethodAnnotationContext context) {
		deactivateAll();
	}

	public void postProcessAnnotatedMethod(IgnoreChecks annotation, MethodAnnotationContext context) {
		activateAll();
	}

	public void preProcessAnnotatedConstructor(IgnoreChecks annotation,
		ConstructorAnnotationContext context) {
		deactivateAll();
	}

	public void postProcessAnnotatedConstructor(IgnoreChecks annotation,
		ConstructorAnnotationContext context) {
		activateAll();
	}

	public void preProcessAnnotatedClass(IgnoreChecks annotation) {
		deactivateAll();
	}

	public void postProcessAnnotatedClass(IgnoreChecks annotation) {
		activateAll();
	}
}
