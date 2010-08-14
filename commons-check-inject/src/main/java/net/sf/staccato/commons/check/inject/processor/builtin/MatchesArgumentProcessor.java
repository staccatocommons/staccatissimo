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

import net.sf.staccato.commons.check.inject.processor.AbstractArgumentProcessor;
import net.sf.staccato.commons.lang.check.annotation.Matches;

/**
 * @author flbulgarelli
 * 
 */
public class MatchesArgumentProcessor extends
	AbstractArgumentProcessor {

	@Override
	public boolean canProcess(Object annotation) {
		return annotation instanceof Matches;
	}

	// FIXME improve caching the regexp statically in a class variable
	@Override
	protected String createArgumentCheck(int argumentNumber, Object annotation) {
		Matches matches = (Matches) annotation;
		return "matches( \"" + parameterName(argumentNumber, matches.var())
			+ "\", $" + (argumentNumber + 1) + ", \"" + matches.value() + "\" );";
	}

}
