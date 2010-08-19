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
package net.sf.staccato.commons.check.inject.processor.builtin;

import java.lang.annotation.Annotation;

import net.sf.staccato.commons.check.inject.processor.AbstractCheckAnnotationProcessor;
import net.sf.staccato.commons.instrument.ArgumentContext;
import net.sf.staccato.commons.instrument.MethodContext;
import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class NonNullProcessor extends AbstractCheckAnnotationProcessor {

	@Override
	public Class<? extends Annotation> getSupportedAnnotationType() {
		return NonNull.class;
	}

	@Override
	protected String createArgumentCheck(Object annotation,
		ArgumentContext context) {
		NonNull nonNull = (NonNull) annotation;
		return String.format(
			"nonNull( \"%s\", %s)",
			parameterName(context.getParameterNumber(), nonNull.var()),
			context.getArgumentName());
	}

	// TODO improve before continue adding the rest of the annotations
	protected String createMethodCheck(Object annotation, MethodContext context) {
		return "net.sf.staccato.commons.lang.check.Assert.nonNull( \""
			+ createVarName((NonNull) annotation) + "\", " + context.getReturnName()
			+ ");";
	}

	private static String createVarName(NonNull nonNull) {
		return nonNull.var().isEmpty() ? "returnValue" : nonNull.var();
	}

}
