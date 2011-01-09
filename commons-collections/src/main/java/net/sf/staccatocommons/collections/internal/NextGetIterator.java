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

package net.sf.staccatocommons.collections.internal;

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

	public boolean hasNext() {
		if (hasNext == null)
			hasNext = updateNext();
		return hasNext;
	}

	public A next() {
		if (!hasNext())
			throw new NoSuchElementException();
		hasNext = null;
		return next;
	}

	/**
	 * @param next
	 *          the next element to retrieve
	 * @return
	 */
	public A setNext(A next) {
		this.next = next;
		return next;
	}

	protected abstract Boolean updateNext();
}