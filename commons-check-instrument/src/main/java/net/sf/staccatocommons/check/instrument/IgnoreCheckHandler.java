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
package net.sf.staccatocommons.check.instrument;

import javassist.CannotCompileException;
import net.sf.staccatocommons.instrument.context.ClassAnnotationContext;
import net.sf.staccatocommons.instrument.context.ConstructorAnnotationContext;
import net.sf.staccatocommons.instrument.context.MethodAnnotationContext;
import net.sf.staccatocommons.instrument.handler.ClassAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.ConstructorAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.deactivator.AbstractActivationAnnotationHandler;
import net.sf.staccatocommons.restrictions.processing.IgnoreChecks;

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

	@Override
	public void preProcessAnnotatedClass(IgnoreChecks annotation, ClassAnnotationContext context)
		throws CannotCompileException {
		deactivateAll();

	}

	@Override
	public void postProcessAnnotatedClass(IgnoreChecks annotation, ClassAnnotationContext context)
		throws CannotCompileException {
		activateAll();
	}

}
