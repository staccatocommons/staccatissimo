/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.instrument.context.internal;

import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;
import net.sf.staccatocommons.instrument.context.ArgumentAnnotationContext;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
	 * 
	 * Creates a new {@link DefaultArgumentAnnotationContext}
	 */
	public DefaultArgumentAnnotationContext(@NonNull ClassPool pool, @NonNull Logger logger) {
		super(pool, logger);
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

	public int getArgumentNumber() {
		return parameterNumber;
	}

	public boolean isConstructorArgument() {
		return behavior instanceof CtConstructor;
	}

	/**
	 * Sets the zero-base argument number
	 * 
	 * @param parameterNumber
	 *          the parameterNumber to set
	 */
	public void setParameterNumber(int parameterNumber) {
		this.parameterNumber = parameterNumber;
	}

	public CtClass getDeclaringClass() {
		return getArgumentBehavior().getDeclaringClass();
	}

	@Override
	public String getArgumentIdentifier() {
		return "$" + (getArgumentNumber() + 1);
	}

	public CtClass getElementType() throws NotFoundException {
		return getArgumentBehavior().getParameterTypes()[getArgumentNumber()];
	}
}
