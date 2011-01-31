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

import java.util.NoSuchElementException;

/**
 * @author flbulgarelli
 * 
 */
public class TakeThriter<A> extends AdvanceThriter<A> {

	private int remaining;
	private final Thriter<A> thriter;

	public TakeThriter(int n, Thriter<A> thriter) {
		this.thriter = thriter;
		this.remaining = n;
	}

	public boolean hasNext() {
		return remaining > 0 && thriter.hasNext();
	}

	public void advance() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();
		thriter.advance();
		remaining--;
	}

	public A current() throws NoSuchElementException {
		return thriter.current();
	}

}
