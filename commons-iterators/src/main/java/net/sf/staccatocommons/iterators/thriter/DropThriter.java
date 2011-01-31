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
public class DropThriter<A> extends AdvanceThriter<A> {

	private int n;
	private final Thriter<A> thriter;

	public DropThriter(int n, Thriter<A> thriter) {
		this.n = n;
		this.thriter = thriter;
	}

	public boolean hasNext() {
		while (n > 0) {
			if (!thriter.hasNext())
				return false;
			thriter.advance();
			n--;
		}
		return thriter.hasNext();
	}

	public void advance() {
		if (!hasNext())
			throw new NoSuchElementException();
		thriter.advance();
	}

	public A current() {
		return thriter.current();
	}

}
