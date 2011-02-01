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

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;

/**
 * @author flbulgarelli
 * 
 */
public class TakeIterator<A> extends AdvanceThriterator<A> {

	private int remaining;
	private final Thriter<A> thriter;

	/**
	 * Creates a new {@link TakeIterator} that takes up to {@code n} elements from
	 * the given {@code thritter}
	 */
	public TakeIterator(int n, @NonNull Thriter<A> thriter) {
		this.thriter = thriter;
		this.remaining = n;
	}

	public boolean hasNext() {
		return remaining > 0 && thriter.hasNext();
	}

	public void advanceNext() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();
		thriter.advanceNext();
		remaining--;
	}

	public A current() throws NoSuchElementException {
		return thriter.current();
	}

	public Thunk<A> delayedCurrent() {
		return thriter.delayedCurrent();
	}

}
