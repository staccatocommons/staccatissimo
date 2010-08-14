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
public interface ArgumentProcessor {

	/**
	 * @param annotation
	 * @return if this processor can process the given annotation
	 */
	public boolean canProcess(Object annotation);

	/**
	 * @param annotation
	 *          the annotation to process
	 * @param argumentNumber
	 *          the parameter number (0 based)
	 * @param behaviour
	 *          the code to process
	 * @throws CannotCompileException
	 */
	public void process(Object annotation, int argumentNumber,
		CtBehavior behaviour) throws CannotCompileException;

}
