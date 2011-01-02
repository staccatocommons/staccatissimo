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

import net.sf.staccato.commons.check.annotation.Matches;

/**
 * @author flbulgarelli
 * 
 */
public class MatchesHandler extends AbstractCheckAnnotationHandler<Matches> {

	/**
	 * Creates a new {@link MatchesHandler}
	 */
	public MatchesHandler(boolean ignoreReturns) {
		super(ignoreReturns);
	}

	@Override
	public Class<Matches> getSupportedAnnotationType() {
		return Matches.class;
	}

	protected String createCheckCode(String argumentMnemonic, String argumentIdentifier,
		Matches annotation) {
		return String.format(
			"that().matches( \"%s\", %s, \"%s\")",
			argumentMnemonic,
			argumentIdentifier,
			annotation.value());
	}

	protected String getVarMnemonic(Matches annotation) {
		return annotation.var();
	}

}
