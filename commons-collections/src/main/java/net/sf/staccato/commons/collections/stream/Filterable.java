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
import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * {@link Stream} interface for filtering elements
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public interface Filterable<T> {

	/**
	 * Preserves elements that evaluate to true
	 * 
	 * @param predicate
	 * @return a new {@link Stream} projection that will retrieve only elements
	 *         that evaluate to true
	 */
	@NonNull
	@Projection
	Stream<T> filter(@NonNull Evaluable<? super T> predicate);

	/**
	 * Preserves all elements while they evalute to true
	 * 
	 * @param predicate
	 * @return a new {@link Stream} projection that will retrieve all elements, as
	 *         long as none of them evaluates to false.
	 */
	@NonNull
	@Projection
	Stream<T> takeWhile(@NonNull Evaluable<? super T> predicate);

	/**
	 * Preserves up to N elements
	 * 
	 * @param amountOfElements
	 * @return a new {@link Stream} projection that will retrieve up to N elements
	 */
	@NonNull
	@Projection
	Stream<T> take(int amountOfElements);

}