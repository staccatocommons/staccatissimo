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
 * {@link Applicable2}s are transformations that take two arguments and whose
 * result is a return value. {@link Applicable2}s <strong>should not</strong>
 * have side effects
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          first argument type
 * @param <T2>
 *          second argument type
 * @param <R>
 *          return type
 */
public interface Applicable2<T1, T2, R> {

	/**
	 * Performs a transformation on the given element, and returns its result.
	 * This method <strong>should not</strong> have side effects
	 * 
	 * @param arg1
	 *          the first computation argument
	 * @param arg2
	 *          the second computation argument
	 * @return the transformation result
	 */
	R apply(T1 arg1, T2 arg2);

}