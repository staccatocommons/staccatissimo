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

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * {@link Stream} interface for mapping - aka transforming or collection -
 * elements.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public interface Mappable<T> {

	/**
	 * Transforms each element using the given function
	 * 
	 * @param <T>
	 * @param <O>
	 * @param function
	 *          the mapper used to transform each element, applying it
	 * @return a new {@link Stream} projection that will retrieve the result of
	 *         applying the given function to each element
	 */
	@NonNull
	@Projection
	<O> Stream<O> map(@NonNull Applicable<? super T, ? extends O> function);
}