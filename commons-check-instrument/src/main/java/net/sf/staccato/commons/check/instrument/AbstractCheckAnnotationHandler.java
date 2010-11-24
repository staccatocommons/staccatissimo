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

import javassist.CannotCompileException;
import net.sf.staccato.commons.instrument.context.ArgumentAnnotationContext;
import net.sf.staccato.commons.instrument.context.MethodAnnotationContext;
import net.sf.staccato.commons.instrument.handler.ArgumentAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.Deactivable;
import net.sf.staccato.commons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.StackedDeactivableSupport;
import net.sf.staccato.commons.lang.SoftException;
import net.sf.staccato.commons.lang.check.Ensure;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractCheckAnnotationHandler implements MethodAnnotationHandler,
	ArgumentAnnotationHandler, Deactivable {

	protected static final String ENSURE_FULLY_QUALIFIED_NAME = "net.sf.staccato.commons.lang.check.Ensure.";
	protected static final String ASSERT_FULLY_QUALIFIED_NAME = "net.sf.staccato.commons.lang.check.Assert.";
	private StackedDeactivableSupport deactivableSupport = new StackedDeactivableSupport();

	@Override
	public final void processAnnotatedArgument(Object annotation, ArgumentAnnotationContext context) {
		if (!deactivableSupport.isActive())
			return;
		try {
			context.getArgumentBehavior().insertBefore(
				ENSURE_FULLY_QUALIFIED_NAME + createArgumentCheck(annotation, context));
		} catch (CannotCompileException e) {
			context.logErrorMessage("Could not insert argument check: {0}", e.getMessage());
			throw SoftException.soften(e);
		}
	}

	@Override
	public final void preProcessAnnotatedMethod(Object annotation, MethodAnnotationContext context) {
		if (!deactivableSupport.isActive())
			return;
		Ensure.is(!context.isVoid(), "Context must not be void");
		try {
			context.getMethod().insertAfter(
				ASSERT_FULLY_QUALIFIED_NAME + createMethodCheck(annotation, context));
		} catch (CannotCompileException e) {
			context.logErrorMessage("Could not insert method check: {0}", e.getMessage());
			throw SoftException.soften(e);
		}
	}

	@Override
	public void postProcessAnnotatedMethod(Object annotation, MethodAnnotationContext context) {
	}

	/**
	 * @param argumentNumber
	 * @param annotation
	 * @return
	 */
	protected abstract String createArgumentCheck(Object annotation,
		ArgumentAnnotationContext argumentContext);

	protected String argumentName(int argNumber, String annotatedVarName) {
		return annotatedVarName.isEmpty() ? "var" + argNumber : annotatedVarName;
	}

	protected String createReturnName(String returnName) {
		return returnName.isEmpty() ? "returnValue" : returnName;
	}

	protected abstract String createMethodCheck(Object annotation, MethodAnnotationContext context);

	public final void deactivate() {
		deactivableSupport.deactivate();
	}

	public final void activate() {
		deactivableSupport.activate();
	}

}
