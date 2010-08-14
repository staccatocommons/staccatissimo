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
package net.sf.staccato.commons.check.inject.processor;

import javassist.CannotCompileException;
import javassist.CtBehavior;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractArgumentProcessor implements
	ArgumentProcessor {

	protected static final String ENSURE_FULLY_QUALIFIED_NAME = "net.sf.staccato.commons.lang.check.Ensure.";

	@Override
	public void process(Object annotation, int argumentNumber, CtBehavior method)
		throws CannotCompileException {
		method.insertBefore(ENSURE_FULLY_QUALIFIED_NAME
			+ createArgumentCheck(argumentNumber, annotation));
	}

	/**
	 * @param argumentNumber
	 * @param annotation
	 * @return
	 */
	protected abstract String createArgumentCheck(int argumentNumber,
		Object annotation);

	protected String parameterName(int argNumber, String annotatedVarName) {
		return annotatedVarName.isEmpty() ? "var" + argNumber : annotatedVarName;
	}

}
