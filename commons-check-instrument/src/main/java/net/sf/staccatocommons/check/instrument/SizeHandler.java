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

import net.sf.staccatocommons.check.annotation.Size;

/**
 * @author flbulgarelli
 * 
 */
public class SizeHandler extends AbstractCheckAnnotationHandler<Size> {

	/**
	 * Creates a new {@link SizeHandler}
	 */
	public SizeHandler(boolean ignoreReturns) {
		super(ignoreReturns);
	}

	public Class<Size> getSupportedAnnotationType() {
		return Size.class;
	}

	protected String createCheckCode(String argumentMnemonic, String argumentIdentifier,
		Size annotation) {
		return String.format(
			"that().isSize(\"%s\", %s, %s)",
			argumentMnemonic,
			argumentIdentifier,
			annotation.value());
	}

	protected String getVarMnemonic(Size annotation) {
		return annotation.var();
	}

}
