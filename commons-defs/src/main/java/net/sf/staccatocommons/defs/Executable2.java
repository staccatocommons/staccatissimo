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

package net.sf.staccatocommons.defs;

/**
 * {@link Executable2}s are computations that take two arguments and whose
 * result is a side effect, instead of a return value.
 * <p>
 * {@link Executable2} has the same semantics that an {@link Applicable2} of
 * {@link Void} return type, but is provided for ease of coding. Concrete
 * implementors <strong>should</strong> implement {@link Applicable2} as well.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          first computation argument type
 * @param <B>
 *          second computation argument type
 * @see Applicative Recomendations for implementing
 */
@Applicative
public interface Executable2<A, B> {

	/**
	 * Performs a side-effect computation.
	 * 
	 * @see Executable#exec(Object)
	 * @param arg0
	 * @param arg1
	 */
	void exec(A arg0, B arg1);

}