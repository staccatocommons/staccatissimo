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

import java.util.List;

import net.sf.staccato.commons.lang.Applicable;

public interface Mappable<T> {

	/**
	 * Maps the the given collection into a new list, using the gicen applyer
	 * 
	 * @param <T>
	 * @param <O>
	 * @param collection
	 *          the source of the mapping. Non null. May be empty.
	 * @param applyer
	 *          the mapper used to apply each element of the source collection.
	 *          Non null.
	 * @return a new, non null {@link List}, which contains an element for the
	 *         result of the applyation of each element of the given collection.
	 *         May be empty
	 */
	@Projection
	<O> Stream<O> map(Applicable<? super T, ? extends O> applyer);
}