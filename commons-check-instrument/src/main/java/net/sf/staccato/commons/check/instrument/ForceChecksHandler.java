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

import net.sf.staccato.commons.check.annotation.ForceChecks;
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
public class ForceChecksHandler extends AbstractActivationAnnotationHandler<ForceChecks> implements
	ClassAnnotationHandler<ForceChecks>, ConstructorAnnotationHandler<ForceChecks>,
	MethodAnnotationHandler<ForceChecks> {

	/**
	 * Creates a new {@link ForceChecksHandler}
	 */
	public ForceChecksHandler() {
		super();
	}

	public Class<ForceChecks> getSupportedAnnotationType() {
		return ForceChecks.class;
	}

	public void preProcessAnnotatedMethod(ForceChecks annotation, MethodAnnotationContext context) {
		activateAll();
	}

	public void postProcessAnnotatedMethod(ForceChecks annotation, MethodAnnotationContext context) {
		deactivateAll();
	}

	public void preProcessAnnotatedConstructor(ForceChecks annotation,
		ConstructorAnnotationContext context) {
		activateAll();
	}

	public void postProcessAnnotatedConstructor(ForceChecks annotation,
		ConstructorAnnotationContext context) {
		deactivateAll();
	}

	public void preProcessAnnotatedClass(ForceChecks annotation) {
		activateAll();
	}

	public void postProcessAnnotatedClass(ForceChecks annotation) {
		deactivateAll();
	}
}
