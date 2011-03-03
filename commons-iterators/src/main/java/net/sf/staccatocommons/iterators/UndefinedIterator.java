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

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;

/**
 * @author flbulgarelli
 * 
 */
public class UndefinedIterator<A> extends AdvanceThriterator<A> {

	private boolean advanced;

	public boolean hasNext() {
		return !advanced;
	}

	public void advanceNext() throws NoSuchElementException {
		if (advanced) {
			throw new NoSuchElementException();
		}
		advanced = true;
	}

	public A current() throws NoSuchElementException {
		throw new RuntimeException("Undefined");
	}

	public Thunk<A> delayedCurrent() {
		return new Thunk<A>() {
			public A value() {
				return current();
			}
		};
	}
}
