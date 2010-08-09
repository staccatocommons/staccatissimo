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

public interface Filterable<T> {

	@Projection
	Stream<T> filter(Evaluable<? super T> predicate);

	// Stream<T> reject(Predicate<? super T> predicate);

	@Projection
	Stream<T> take(int amountOfElements);

}