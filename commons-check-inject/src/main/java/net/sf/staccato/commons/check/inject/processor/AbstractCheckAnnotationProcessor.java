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
package net.sf.staccato.commons.check.inject.processor;

import javassist.CannotCompileException;
import net.sf.staccato.commons.instrument.AbstractAnnotationProcessor;
import net.sf.staccato.commons.instrument.ArgumentContext;
import net.sf.staccato.commons.instrument.ConstructorContext;
import net.sf.staccato.commons.instrument.MethodContext;
import net.sf.staccato.commons.lang.SoftException;
import net.sf.staccato.commons.lang.check.Ensure;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractCheckAnnotationProcessor extends
	AbstractAnnotationProcessor {

	protected static final String ENSURE_FULLY_QUALIFIED_NAME = "net.sf.staccato.commons.lang.check.Ensure.";
	protected static final String ASSERT_FULLY_QUALIFIED_NAME = "net.sf.staccato.commons.lang.check.Assert.";

	@Override
	public void processAnnotatedArgument(Object annotation,
		ArgumentContext context) {
		try {
			context.getBehavior().insertBefore(
				ENSURE_FULLY_QUALIFIED_NAME + createArgumentCheck(annotation, context));
		} catch (CannotCompileException e) {
			context.logErrorMessage(
				"Could not insert argument check: {0}",
				e.getMessage());
			throw SoftException.soften(e);
		}
	}

	@Override
	public void processAnnotatedMethod(Object annotation, MethodContext context) {
		Ensure.isTrue("context", !context.isVoid(), "must not be void");
		try {
			context.getMethod().insertAfter(createMethodCheck(annotation, context));
		} catch (CannotCompileException e) {
			context.logErrorMessage(
				"Could not insert method check: {0}",
				e.getMessage());
			throw SoftException.soften(e);
		}
	}

	@Override
	public void processAnnotatedConstructor(Object annotation,
		ConstructorContext context) {
		throw new AssertionError(
			"constructors can not be annotated with check annotations");
	}

	/**
	 * @param argumentNumber
	 * @param annotation
	 * @return
	 */
	protected abstract String createArgumentCheck(Object annotation,
		ArgumentContext argumentContext);

	protected String parameterName(int argNumber, String annotatedVarName) {
		return annotatedVarName.isEmpty() ? "var" + argNumber : annotatedVarName;
	}

	protected abstract String createMethodCheck(Object annotation,
		MethodContext context);

}
