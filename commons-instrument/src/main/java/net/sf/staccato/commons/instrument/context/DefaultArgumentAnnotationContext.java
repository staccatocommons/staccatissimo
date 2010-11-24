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

import javassist.CtBehavior;

import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultArgumentAnnotationContext extends AbstractAnnotationContext implements
	ArgumentAnnotationContext {

	private CtBehavior behavior;
	private int parameterNumber;

	/**
	 * Creates a new {@link DefaultArgumentAnnotationContext}
	 */
	public DefaultArgumentAnnotationContext(Logger logger) {
		super(logger);
	}

	/**
	 * @return the behavior
	 */
	public CtBehavior getArgumentBehavior() {
		return behavior;
	}

	/**
	 * @param behavior
	 *          the behavior to set
	 */
	public void setBehavior(CtBehavior behavior) {
		this.behavior = behavior;
	}

	/**
	 * @return the parameterNumber
	 */
	public int getArgumentNumber() {
		return parameterNumber;
	}

	/**
	 * @param parameterNumber
	 *          the parameterNumber to set
	 */
	public void setParameterNumber(int parameterNumber) {
		this.parameterNumber = parameterNumber;
	}

	@Override
	public String getArgumentIdentifier() {
		return "$" + (getArgumentNumber() + 1);
	}

}
