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
 * {@link Applicable}s are computations that take one argument and whose result
 * is a return value. {@link Applicable}s should not have side effects
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          argument type
 * @param <R>
 *          return type
 */
public interface Applicable<T, R> {

	/**
	 * Performs a transformation on the given element, and returns its result.
	 * This method should have no side effect.
	 * 
	 * @param arg
	 * @return the transformation result
	 */
	R apply(T arg);

}