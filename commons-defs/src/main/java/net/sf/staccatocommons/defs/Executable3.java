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
package net.sf.staccatocommons.defs;

/**
 * {@link Executable3}s are computations that take three arguments and whose
 * result is a side effect, instead of a return value.
 * <p>
 * {@link Executable3} has the same semantics that an {@link Applicable3} of
 * {@link Void} return type, but is provided for ease of coding. Concrete
 * implementors <strong>should</strong> implement {@link Applicable3} as well.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          first computation argument type
 * @param <T2>
 *          second computation argument type
 * @param <T3>
 *          third computation argument type
 * @see Applicative Recomendations for implementing
 */
@Applicative
public interface Executable3<T1, T2, T3> {

	/**
	 * Performing a side-effect computation.
	 * 
	 * @see Executable#exec(Object)
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	void exec(T1 arg0, T2 arg1, T3 arg2);

}