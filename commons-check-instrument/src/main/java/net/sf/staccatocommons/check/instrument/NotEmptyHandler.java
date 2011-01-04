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

import net.sf.staccatocommons.check.annotation.NotEmpty;

/**
 * @author flbulgarelli
 * 
 */
public class NotEmptyHandler extends AbstractCheckAnnotationHandler<NotEmpty> {

	/**
	 * Creates a new {@link NotEmptyHandler}
	 */
	public NotEmptyHandler(boolean ignoreReturns) {
		super(ignoreReturns);
	}

	@Override
	public Class<NotEmpty> getSupportedAnnotationType() {
		return NotEmpty.class;
	}

	protected String createCheckCode(String argumentMnemonic, String argumentIdentifier,
		NotEmpty annotation) {
		return String.format("that().isNotEmpty( \"%s\", %s)", argumentMnemonic, argumentIdentifier);
	}

	protected String getVarMnemonic(NotEmpty annotation) {
		return annotation.value();
	}

}
