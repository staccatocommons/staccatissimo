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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.internal.WrapperStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;

/**
 * @author flbulgarelli
 * 
 */
public class MemorizedStream<A> extends WrapperStream<A> {

	private List<A> previous = new LinkedList<A>(); // FIXME not totally lazy
	private Thriterator<A> remaining;

	public MemorizedStream(Stream<A> source) {
		super(source);
		this.remaining = source.iterator();
	}

	public boolean isEmpty() {
		return previous.isEmpty() && !remaining.hasNext();
	}

	public Thriterator<A> iterator() {
		final Iterator<A> previouslyIter = previous.iterator();
		return new AdvanceThriterator<A>() {
			private Thriterator<A> iter;

			public boolean hasNext() {
				updateIter();
				return iter.hasNext();
			}

			private void updateIter() {
				if (previouslyIter.hasNext()) {
					iter = Thriterators.from(previouslyIter);
				} else {
					iter = new NextThriterator<A>() {
						public boolean hasNext() {
							return remaining.hasNext();
						}

						public A next() {
							A next = remaining.next();
							previous.add(next);
							return next;
						}
					};
				}
			}

			public void advanceNext() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				iter.advanceNext();
			}

			public A current() {
				return iter.current();
			}

			public Thunk<A> delayedCurrent() {
				return iter.delayedCurrent();
			}
		};
	}

}
