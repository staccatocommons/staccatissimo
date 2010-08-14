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
import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class NonNullArgumentProcessor extends
	AbstractArgumentProcessor {

	@Override
	public boolean canProcess(Object annotation) {
		return annotation instanceof NonNull;
	}

	protected String createArgumentCheck(int argNumber, Object annotation) {
		NonNull nonNull = (NonNull) annotation;
		return "nonNull(\"" + parameterName(argNumber, nonNull.var()) + "\", $"
			+ (argNumber + 1)
			+ ");";
	}

}
