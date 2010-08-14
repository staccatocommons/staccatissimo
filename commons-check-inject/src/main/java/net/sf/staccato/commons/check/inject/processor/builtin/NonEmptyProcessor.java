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
import net.sf.staccato.commons.lang.check.annotation.NonEmpty;

/**
 * @author flbulgarelli
 * 
 */
public class NonEmptyProcessor extends
	AbstractArgumentProcessor {

	public boolean canProcess(Object annotation) {
		return annotation instanceof NonEmpty;
	}

	@Override
	protected String createArgumentCheck(int argumentNumber, Object annotation) {
		NonEmpty nonEmpty = (NonEmpty) annotation;
		return "notEmpty( \"" + parameterName(argumentNumber, nonEmpty.var())
			+ "\", $" + (argumentNumber + 1) + ");";
	}
}
