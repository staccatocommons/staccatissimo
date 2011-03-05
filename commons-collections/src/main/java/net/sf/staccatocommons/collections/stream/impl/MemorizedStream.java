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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.internal.WrapperStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 */
public class MemorizedStream<A> extends WrapperStream<A> {

	private List<Thunk<A>> previous = new LinkedList<Thunk<A>>(); // FIXME not
																																// totally lazy
	private Thriterator<A> remaining;

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
		final ListIterator<Thunk<A>> previousIter = previous.listIterator();
		return new AdvanceThriterator<A>() {
			private Thunk<A> current;
			private Thriterator<A> iter;
			private boolean remaningIterationStarted = false;
			private boolean iterUpdated = false;

			public boolean hasNext() {
				updateIter();
				return iter.hasNext();
			}

			private void updateIter() {
				if (iterUpdated || remaningIterationStarted)
					return;
				iterUpdated = true;
				if (!previousIter.hasNext()) {
					remaningIterationStarted = true;
					iter = newRemaningIterator();
				} else if (iter == null) {
					iter = newPreviousIerIterator();
				}
			}

			public void advanceNext() throws NoSuchElementException {
				updateIter();
				iterUpdated = false;
				iter.advanceNext();
			}

			public A current() {
				return iter.current();
			}

			public Thunk<A> delayedCurrent() {
				return iter.delayedCurrent();
			}

			public AdvanceThriterator<A> newPreviousIerIterator() {
				return new AdvanceThriterator<A>() {

					public boolean hasNext() {
						return previousIter.hasNext();
					}

					public void advanceNext() throws NoSuchElementException {
						current = previousIter.next();
					}

					public A current() {
						return delayedCurrent().value();
					}

					public Thunk<A> delayedCurrent() {
						return current;
					}

				};
			}

			public Thriterator<A> newRemaningIterator() {
				return new AdvanceThriterator<A>() {
					public boolean hasNext() {
						return remaining.hasNext();
					}

					public void advanceNext() throws NoSuchElementException {
						remaining.advanceNext();
						current = remaining.delayedCurrent();
						previousIter.add(current);
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
