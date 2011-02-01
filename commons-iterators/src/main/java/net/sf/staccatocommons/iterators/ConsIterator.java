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

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriters;

/**
 * @author flbulgarelli
 * 
 */
public class ConsIterator<A> extends AdvanceThriterator<A> {

	private final A head;
	private final Thriter<? extends A> tail;
	private boolean headConsumed;
	private boolean tailAdvanced;

	/**
	 * Creates a new {@link ConsIterator}
	 */
	public ConsIterator(A head, Thriter<? extends A> tail) {
		this.head = head;
		this.tail = tail;
	}

	public ConsIterator(A head, Iterator<? extends A> tail) {
		this(head, Thriters.from(tail));
	}

	public ConsIterator(A head, Thriterator<? extends A> tail) {
		this(head, (Thriter<A>) tail);
	}

	public final boolean hasNext() {
		return !headConsumed || tail.hasNext();
	}

	public final void advance() throws NoSuchElementException {
		if (!headConsumed) {
			headConsumed = true;
		} else {
			tail.advance();
			tailAdvanced = true;
		}
	}

	public final A current() throws NoSuchElementException {
		if (tailAdvanced)
			return tail.current();
		if (headValue() instanceof Thunk) // XXX HACK!
			return ((Thunk<A>) headValue()).value();
		return headValue();
	}

	protected A headValue() {
		return head;
	}

}
