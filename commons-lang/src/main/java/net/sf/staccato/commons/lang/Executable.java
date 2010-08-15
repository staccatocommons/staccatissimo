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

import java.util.Iterator;

import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * <p>
 * {@link Executable}s are computations that take one argument and whose result
 * is a side effect, instead of a return value.
 * </p>
 * <p>
 * {@link Executable}s are slightly modeled against a minimal, parameterized,
 * generic, command object interface in the context of the Command Design
 * Pattern. In those cases, the exact meaning of the argument vary and should be
 * specified by implementors and client code - it may be for example the
 * execution context or the the actual command receiver.
 * </p>
 * <p>
 * Possible - sometimes overlapped - usage scenarios are:
 * <ul>
 * <li>Implementing callbacks</li>
 * <li>Enqueuing requests</li>
 * <li>Asyncronous operations</li>
 * <li>Traversing mechanism alternative to the {@link Iterable} and
 * {@link Iterator} interfaces</li>
 * </ul>
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of the argument of the computation
 */
public interface Executable<T> {

	/**
	 * Performs a side-effect computation.
	 * 
	 * @param argument
	 *          the argument of the computation.
	 */
	void exec(@NonNull T argument);

}