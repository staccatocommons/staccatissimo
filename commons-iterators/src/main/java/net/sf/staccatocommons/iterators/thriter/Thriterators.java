/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.iterators.thriter;

import java.util.Iterator;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class Thriterators {

	private Thriterators() {}

	/**
	 * Answers a {@link Thriterator} that wraps the given {@link Iterator}. If it
	 * is already a {@link Thriterator}, this method just returns its argument.
	 * 
	 * @param <A>
	 * @param iter
	 * @return a new {@link Thriterator} that wraps the given iterator, if it is
	 *         not already a {@link Thriterator}. The given <code>iter</code>,
	 *         otherwise
	 */
	@NonNull
	public static <A> Thriterator<A> from(@NonNull Iterator<? extends A> iter) {
		if (iter instanceof Thriterator)
			return (Thriterator<A>) iter;
		return new IteratorThriter<A>(iter);
	}

}
