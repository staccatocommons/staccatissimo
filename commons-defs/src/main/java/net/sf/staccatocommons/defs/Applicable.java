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
 * {@link Applicable}s are transformations that take one argument and have a
 * return value, by implementing an {@link #apply(Object)} method.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          argument type
 * @param <R>
 *          return type
 * @see Applicative Recomendations for implementing
 */
@Applicative
public interface Applicable<T, R> {

	/**
	 * Performs a transformation on the given element, and returns its result.
	 * 
	 * @param arg
	 *          the transformation
	 * @return the transformation result
	 */
	R apply(T arg);

}