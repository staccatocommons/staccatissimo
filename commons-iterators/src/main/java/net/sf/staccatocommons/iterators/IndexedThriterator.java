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

import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;

/**
 * @author flbulgarelli
 * 
 */
public abstract class IndexedThriterator<A> extends AdvanceThriterator<A> {

	private int pos = 0;

	public final boolean hasNext() {
		return pos < length();
	}

	public final void advanceNext() throws NoSuchElementException {
		if (pos == length())
			throw new NoSuchElementException();
		pos++;
	}

	public final A current() throws NoSuchElementException {
		return elementAt(pos - 1);
	}

	protected abstract A elementAt(int position);

	protected abstract int length();
}