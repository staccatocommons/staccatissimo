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

import net.sf.staccato.commons.lang.Applicable2;

public interface Foldable<T> {

	@Reduction
	T reduce(Applicable2<? super T, ? super T, ? extends T> applicable);

	@Reduction
	<O> O fold(O initial,
		Applicable2<? super O, ? super T, ? extends O> applicable);
}