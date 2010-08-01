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
package net.sf.staccato.commons.lang;

/**
 * {@link Executable}s are computations that take one argument and whose result
 * is a side effect, instead of a return value.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of the argument of the computation
 */
public interface Executable<T> {

	/**
	 * Executes this {@link Executable}, performing a side-effect computation.
	 * 
	 * {@link Executable} argument is non nullable - implementors are not required
	 * to handle <code>null</code> arguments. Thus, client code should never pass
	 * null, although clearly stated that a nullable argument {@link Executable}
	 * needs to be provided.
	 * 
	 * @param argument
	 *          the argument of the computation. non null
	 */
	void exec(T argument);

}