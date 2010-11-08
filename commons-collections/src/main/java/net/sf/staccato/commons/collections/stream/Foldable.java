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

public interface Foldable<A> {

	@Reduction
	A reduce(Applicable2<? super A, ? super A, ? extends A> function);

	@Reduction
	<B> B fold(B initial, Applicable2<? super B, ? super A, ? extends B> function);

	@Reduction
	String joinStrings(String separator);
}