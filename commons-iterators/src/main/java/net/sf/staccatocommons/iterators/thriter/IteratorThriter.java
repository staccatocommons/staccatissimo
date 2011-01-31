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

import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class IteratorThriter<A> extends NextThriter<A> implements Thriterator<A> {

	private final Iterator<? extends A> iter;

	/**
	 * Creates a new {@link IteratorThriter}
	 */
	public IteratorThriter(@NonNull Iterator<? extends A> iter) {
		this.iter = iter;
	}

	public boolean hasNext() {
		return iter.hasNext();
	}

	public A next() {
		return iter.next();
	}

	@NonNull
	public static <A> Thriterator<A> from(@NonNull Iterator<? extends A> iter) {
		if (iter instanceof Thriterator)
			return (Thriterator<A>) iter;
		return new IteratorThriter<A>(iter);
	}
}
