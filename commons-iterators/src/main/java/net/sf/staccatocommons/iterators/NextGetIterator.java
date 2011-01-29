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

package net.sf.staccatocommons.iterators;

import java.util.NoSuchElementException;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public abstract class NextGetIterator<A> extends AbstractUnmodifiableIterator<A> {

	private A next;
	private Boolean hasNext;

	public final boolean hasNext() {
		if (hasNext == null)
			hasNext = updateNext();
		return hasNext;
	}

	public final A next() {
		if (!hasNext())
			throw new NoSuchElementException();
		hasNext = null;
		return next;
	}

	/**
	 * Sets the next value to iterate over.
	 * 
	 * @param next
	 *          the next element to retrieve
	 * @return <code>next</code>
	 */
	public final A setNext(A next) {
		this.next = next;
		return next;
	}

	protected abstract Boolean updateNext();
}