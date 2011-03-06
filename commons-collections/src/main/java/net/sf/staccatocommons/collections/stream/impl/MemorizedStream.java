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

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.internal.SingleLinkedDelayedQueue;
import net.sf.staccatocommons.collections.stream.impl.internal.WrapperStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.EmptyIterator;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 */
public class MemorizedStream<A> extends WrapperStream<A> {

	private SingleLinkedDelayedQueue<A> previous = new SingleLinkedDelayedQueue<A>();

	private Thriterator<A> remaining;

	/**
	 * 
	 * Creates a new {@link MemorizedStream}
	 */
	public MemorizedStream(Stream<A> source) {
		super(source);
		this.remaining = source.iterator();
	}

	public boolean isEmpty() {
		return previous.isEmpty() && !remaining.hasNext();
	}

	public Stream<A> dettach() {
		return this;
	}

	public Stream<A> memorize() {
		return this;
	}

	public Thriterator<A> iterator() {
		if (remaining.isEmpty()) {
			if (previous.isEmpty())
				return EmptyIterator.empty();
			return previous.iterator();
		}
		final Thriterator<A> previousIter = previous.iterator();
		return new AdvanceThriterator<A>() {
			private Thunk<A> current;
			private Thriterator<A> iter = previousIter;
			private boolean remainingIterationStarted = false;

			public boolean hasNext() {
				if (remainingIterationStarted)
					return iter.hasNext();
				return remaining.hasNext();
			}

			public void advanceNext() throws NoSuchElementException {
				if (!remainingIterationStarted && !iter.hasNext()) {
					iter = newRemaningIterator();
					remainingIterationStarted = true;
				}
				iter.advanceNext();
			}

			public A current() {
				return iter.current();
			}

			public Thunk<A> delayedCurrent() {
				return iter.delayedCurrent();
			}

			public Thriterator<A> newRemaningIterator() {
				return new AdvanceThriterator<A>() {
					public boolean hasNext() {
						return remaining.hasNext();
					}

					public void advanceNext() throws NoSuchElementException {
						remaining.advanceNext();
						current = remaining.delayedCurrent();
						previous.add(current);
					}

					public A current() {
						return delayedCurrent().value();
					}

					public Thunk<A> delayedCurrent() {
						return current;
					}
				};
			}
		};
	}

}
