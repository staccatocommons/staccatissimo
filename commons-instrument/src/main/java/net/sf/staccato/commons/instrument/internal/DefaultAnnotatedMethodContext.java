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
package net.sf.staccato.commons.instrument.internal;

import javassist.CtMethod;
import javassist.NotFoundException;
import net.sf.staccato.commons.instrument.processor.AnnotatedMethodContext;

import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultAnnotatedMethodContext extends AbstractProcessingContext
	implements AnnotatedMethodContext {

	private CtMethod method;

	public DefaultAnnotatedMethodContext(Logger logger) {
		super(logger);
	}

	/**
	 * @return the method
	 */
	public CtMethod getMethod() {
		return method;
	}

	/**
	 * @param method
	 *          the method to set
	 */
	public void setMethod(CtMethod method) {
		this.method = method;
	}

	@Override
	public String getReturnName() {
		return "$_";
	}

	@Override
	public boolean isVoid() {
		try {
			return ((CtMethod) getMethod()).getReturnType().getName().equals("void");
		} catch (NotFoundException e) {
			return false;
		}
	}

}
