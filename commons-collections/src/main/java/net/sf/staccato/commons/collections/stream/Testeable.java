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
package net.sf.staccato.commons.collections.stream;

import net.sf.staccato.commons.lang.Evaluable;

/**
 * {@link Stream} interface for testing conditions on elements
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public interface Testeable<T> {

	/**
	 * Tests if all elements satisfy the given {@link Evaluable}
	 * 
	 * @param predicate
	 *          an {@link Evaluable} to evaluate each element
	 * @return if all the elements evaluate to true
	 */
	@Reduction
	boolean all(Evaluable<? super T> predicate);

	/**
	 * Tests if at least one element satisfies the given {@link Evaluable}
	 * 
	 * @param predicate
	 *          an {@link Evaluable} to evaluate each element
	 * @return if any element evaluate to true
	 */
	@Reduction
	boolean any(Evaluable<? super T> predicate);

}