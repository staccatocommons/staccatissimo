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
import net.sf.staccato.commons.lang.Applicable2;

/**
 * {@link Stream} interface for folding -aka injecting - elements - producing a
 * single aggregated result from all the Stream elements.
 * 
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Fold_(higher-order_function)">Folds</a>
 * @author flbulgarelli
 */
public interface Foldable<A> {

	/**
	 * (Left)folds this {@link Stream} using an initial value and the given
	 * two-arg function
	 * 
	 * @param <B>
	 * @param initial
	 * @param function
	 * @return
	 */
	<B> B fold(B initial, @NonNull Applicable2<? super B, ? super A, ? extends B> function);

	A reduce(@NonNull Applicable2<? super A, ? super A, ? extends A> function);

	/**
	 * (Left)folds this {@link Stream} concatenating each elements toString with a
	 * separator
	 * 
	 * @param separator
	 * @return
	 */
	@NonNull
	String joinStrings(@NonNull String separator);
}