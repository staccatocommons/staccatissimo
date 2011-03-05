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
package net.sf.staccatocommons.collections.stream.impl;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.thunk.Thunks;

import org.apache.commons.lang.StringUtils;

/**
 * @author flbulgarelli
 * 
 */
public class SingleLinkedDelayedQueue<A> implements Iterable<A>, EmptyAware {

	private final Cell<A> head = new Cell<A>(Thunks.<A> undefined());
	private Cell<A> last = head;

	public void add(Thunk<A> element) {
		Cell<A> prev = last;
		last = new Cell<A>(element);
		prev.next = last;
	}

	public Thriterator<A> iterator() {
		return new Iter<A>(head);
	}

	private static final class Cell<A> {
		final Thunk<A> element;
		Cell<A> next;

		public Cell(Thunk<A> element) {
			this.element = element;
		}

		boolean hasNext() {
			return next != null;
		}
	}

	@Override
	public boolean isEmpty() {
		return !head.hasNext();
	}

	public String toString() {
		return "[" + StringUtils.join(iterator(), ",") + "]";
	}

	private static final class Iter<A> extends AdvanceThriterator<A> {

		private Cell<A> current;

		public Iter(Cell<A> current) {
			this.current = current;
		}

		public boolean hasNext() {
			return current.hasNext();
		}

		public void advanceNext() throws NoSuchElementException {
			current = current.next;
		}

		public A current() {
			return delayedCurrent().value();
		}

		public Thunk<A> delayedCurrent() {
			return current.element;
		}

	}

}
