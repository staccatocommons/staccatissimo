/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.restrictions.instrument.check;

import net.sf.staccatocommons.instrument.context.AnnotationContext;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public class NotNegativeHandler extends AbstractCheckAnnotationHandler<NotNegative> {

	/**
	 * Creates a new {@link NotNegativeHandler}
	 */
	public NotNegativeHandler(boolean ignoreReturns) {
		super(ignoreReturns);
	}

	public Class<NotNegative> getSupportedAnnotationType() {
		return NotNegative.class;
	}

	protected String createCheckCode(String argumentMnemonic, String argumentIdentifier,
		NotNegative annotation, AnnotationContext context) {
		return String.format("that().isNotNegative( \"%s\", %s)", argumentMnemonic, argumentIdentifier);
	}

	protected String getVarMnemonic(NotNegative annotation) {
		return annotation.value();
	}

}
