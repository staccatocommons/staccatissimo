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
import java.util.NoSuchElementException;

import net.sf.staccatocommons.iterators.ConsIterator;

/**
 * @author flbulgarelli
 * 
 */
public class ConsThriter<A> extends AdvanceThriter<A> {

	private final A head;
	private final Thriter<? extends A> tail;
	private boolean headConsumed;
	private boolean tailAdvanced;

	/**
	 * Creates a new {@link ConsThriter}
	 */
	public ConsThriter(A head, Thriter<? extends A> tail) {
		this.head = head;
		this.tail = tail;
	}

	public boolean hasNext() {
		return !headConsumed || tail.hasNext();
	}

	public void advance() throws NoSuchElementException {
		if (!headConsumed) {
			headConsumed = true;
		} else {
			tail.advance();
			tailAdvanced = true;
		}
	}

	public A current() throws NoSuchElementException {
		if (tailAdvanced)
			return tail.current();
		return head;
	}

	public static <A> Thriterator<A> from(A head, Iterator<? extends A> tail) {
		if (tail instanceof Thriter)
			return new ConsThriter<A>(head, (Thriter<? extends A>) tail);
		return new ConsIterator<A>(head, tail);
	}

}
