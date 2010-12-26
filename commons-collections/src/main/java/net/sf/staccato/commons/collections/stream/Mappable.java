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

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Applicable;

/**
 * {@link Stream} interface for mapping - aka collecting - elements.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface Mappable<A> {

	/**
	 * Transforms each element using the given function
	 * 
	 * @param <B>
	 * @param function
	 *          the mapper used to transform each element, applying it
	 * @return a new {@link Stream} projection that will retrieve the result of
	 *         applying the given function to each element
	 */
	@NonNull
	@Projection
	<B> Stream<B> map(@NonNull Applicable<? super A, ? extends B> function);

	/**
	 * Transformes each element using the given function, and concatenates
	 * ("flatterns") the result
	 * 
	 * @param <B>
	 * @param function
	 * @return a new {@link Stream} that will retrieve the result of transforming
	 *         each element and concatenating those transformations
	 */
	@Projection
	<B> Stream<B> flatMap(@NonNull Applicable<? super A, ? extends Iterable<? extends B>> function);
}