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
 * {@link Applicable3}s are computations that take three arguments and whose
 * result is a return value.{@link Applicable3}s should not have side effects
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          first argument type
 * @param <T2>
 *          second argument type
 * @param <T3>
 *          third argument type
 * @param <R>
 *          return type
 */
public interface Applicable3<T1, T2, T3, R> {

	/**
	 * Performs a transformation on the given element, and returns its result.
	 * This method should have no side effect.
	 * 
	 * @param arg1
	 *          the first computation argument
	 * @param arg2
	 *          the second computation argument
	 * @param arg3
	 *          the third computation argument
	 * @return the transformation result
	 */
	R apply(T1 arg1, T2 arg2, T3 arg3);

}