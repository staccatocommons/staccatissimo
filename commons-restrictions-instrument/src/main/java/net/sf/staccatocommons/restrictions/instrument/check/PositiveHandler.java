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
package net.sf.staccatocommons.restrictions.instrument.check;

import net.sf.staccatocommons.instrument.context.AnnotationContext;
import net.sf.staccatocommons.restrictions.check.Positive;

/**
 * @author flbulgarelli
 * 
 */
public class PositiveHandler extends AbstractCheckAnnotationHandler<Positive> {

	/**
	 * Creates a new {@link PositiveHandler}
	 */
	public PositiveHandler(boolean ignoreReturns) {
		super(ignoreReturns);
	}

	public Class<Positive> getSupportedAnnotationType() {
		return Positive.class;
	}

	protected String createCheckCode(String argumentMnemonic, String argumentIdentifier,
		Positive annotation, AnnotationContext context) {
		return String.format("that().isPositive( \"%s\", %s)", argumentMnemonic, argumentIdentifier);
	}

	protected String getVarMnemonic(Positive annotation) {
		return annotation.value();
	}

}
