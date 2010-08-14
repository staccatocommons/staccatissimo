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

import javassist.CannotCompileException;
import javassist.CtMethod;
import net.sf.staccato.commons.check.inject.processor.ReturnProcessor;
import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class NonNullReturnProcessor implements
	ReturnProcessor {

	@Override
	public boolean canProcess(Object annotation) {
		return annotation instanceof NonNull;
	}

	@Override
	public void process(Object annotation, CtMethod behaviour)
		throws CannotCompileException {
		behaviour.insertAfter(createNullReturnCheck((NonNull) annotation));
	}

	private static String createNullReturnCheck(NonNull nonNull) {
		return "net.sf.staccato.commons.lang.check.Assert.nonNull( \""
			+ createVarName(nonNull) + "\", $_);";
	}

	private static String createVarName(NonNull nonNull) {
		return nonNull.var().isEmpty() ? "returnValue" : nonNull.var();
	}
}
