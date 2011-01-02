/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.instrument.examples;

import javassist.CannotCompileException;
import net.sf.staccato.commons.instrument.context.ArgumentAnnotationContext;
import net.sf.staccato.commons.instrument.handler.ArgumentAnnotationHandler;

/**
 * @author flbulgarelli
 * 
 */
public class NonNegativeDecimalHandler implements ArgumentAnnotationHandler<NonNegativeDecimal> {

	public Class<NonNegativeDecimal> getSupportedAnnotationType() {
		return NonNegativeDecimal.class;
	}

	public void processAnnotatedArgument(NonNegativeDecimal annotation,
		ArgumentAnnotationContext context) throws CannotCompileException {
		context.getArgumentBehavior().insertBefore(
			"if (" + context.getArgumentIdentifier() + ".compareTo(java.math.BigDecimal.ZERO) < 0) { "
				+ "throw new IllegalArgumentException(\"" + annotation.value() + " must be positive\"); }");
	}
}
