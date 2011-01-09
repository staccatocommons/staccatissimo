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
package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.type.NumberType;

/**
 * {@link Stream} interface for folding -aka injecting - elements - producing a
 * single aggregated result from all the Stream elements.
 * 
 * {@link Streams} implements lefts folds, which consist of taking an initial
 * value and a {@link Stream} element, applying a function to them, and then
 * repeating the process with this result as the next initial value and the next
 * element from the stream. The last returned value is the folding result.
 * 
 * 
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
	 * @return the folding result
	 */
	<B> B fold(B initial, @NonNull Applicable2<? super B, ? super A, ? extends B> function);

	/**
	 * (Left)folds the tail of this {@link Stream} using the first element of the
	 * stream as initial value
	 * 
	 * @param function
	 * @return the folding result
	 * @throws IllegalStateException
	 *           if the {@link Stream} is empty
	 */
	A reduce(@NonNull Applicable2<? super A, ? super A, ? extends A> function);

	/**
	 * (Left)folds this {@link Stream} concatenating each elements toString with a
	 * separator
	 * 
	 * @param separator
	 * @return the string representation of each element concatenated using a
	 *         separator
	 */
	@NonNull
	String joinStrings(@NonNull String separator);

	A sum(@NonNull NumberType<A> numberType);

	A product(@NonNull NumberType<A> numberType);
}