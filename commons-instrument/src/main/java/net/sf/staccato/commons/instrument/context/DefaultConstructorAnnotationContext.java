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
package net.sf.staccato.commons.instrument.context;

import javassist.CtConstructor;

import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultConstructorAnnotationContext extends AbstractAnnotationContext implements
	ConstructorAnnotationContext {

	private CtConstructor constructor;

	public DefaultConstructorAnnotationContext(Logger logger) {
		super(logger);
	}

	/**
	 * @return the constructor
	 */
	public CtConstructor getConstructor() {
		return constructor;
	}

	/**
	 * @param constructor
	 *          the constructor to set
	 */
	public void setConstructor(CtConstructor constructor) {
		this.constructor = constructor;
	}

}
