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
package net.sf.staccato.commons.instrument.processor;

import javassist.CtMethod;

/**
 * @author flbulgarelli
 * 
 */
public interface AnnotatedMethodContext extends AnnotatedContext {

	/**
	 * @return the annoted method
	 */
	CtMethod getMethod();

	/**
	 * @return The name of the speudo-variable that represents the return value
	 */
	String getReturnName();

	/**
	 * @return if the annotated method is void
	 */
	boolean isVoid();

}
