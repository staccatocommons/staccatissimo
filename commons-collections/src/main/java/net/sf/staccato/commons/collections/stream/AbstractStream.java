/*
 Copyright (c) 2010, The Staccato-Commons Team   

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.collections.stream;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.staccato.commons.collections.iterable.Iterables;
import net.sf.staccato.commons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccato.commons.collections.iterable.internal.IterablesInternal;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.Provider;

/**
 * An abstract implementation of a {@link Stream}. Only it {@link Iterator}
 * method is abstract
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public abstract class AbstractStream<T> implements Stream<T> {


	@Override
	public int size() {
		int size = 0;
		for (@SuppressWarnings("unused")
		T element : this)
			size++;
		return size;
	}

	@Override
	public boolean isEmpty() {
		return IterablesInternal.isEmptyInternal(this);
	}

	@Override
	public boolean contains(T element) {
		return IterablesInternal.containsInternal(this, element);
	}

	@Override
	public T value() {
		return any();
	}

	@Override
	public Stream<T> filter(final Evaluable<? super T> predicate) {
		return new AbstractStream<T>() {
			public Iterator<T> iterator() {
				final Iterator<T> iter = AbstractStream.this.iterator();
				return new AbstractUnmodifiableIterator<T>() {
					private T next;
					public boolean hasNext() {
						while (iter.hasNext())
							if (predicate.eval((next = iter.next())))
								return true;
						return false;
					}
					public T next() {
						return next;
					}
				};
			}
		};
	}

	@Override
	public Stream<T> takeWhile(final Evaluable<? super T> predicate) {
		return new AbstractStream<T>() {
			public Iterator<T> iterator() {
				final Iterator<T> iter = AbstractStream.this.iterator();
				return new AbstractUnmodifiableIterator<T>() {
					private T next;
					public boolean hasNext() {
						return iter.hasNext() && predicate.eval((next = iter.next()));
					}
					public T next() {
						return next;
					}
				};
			}
		};
	}

	@Override
	public Stream<T> take(final int amountOfElements) {
		return new AbstractStream<T>() {
			public Iterator<T> iterator() {
				final Iterator<T> iter = AbstractStream.this.iterator();
				return new AbstractUnmodifiableIterator<T>() {
					private int i = 0;
					public boolean hasNext() {
						return i < amountOfElements && iter.hasNext();
					}
					public T next() {
						i++;
						return iter.next();
					}
				};
			}
		};
	}

	@Override
	public T reduce(Applicable2<? super T, ? super T, ? extends T> applicable) {
		return IterablesInternal.reduceInternal(this, applicable);
	}

	@Override
	public <O> O fold(O initial,
		Applicable2<? super O, ? super T, ? extends O> applicable) {
		return IterablesInternal.foldInternal(this, initial, applicable);
	}

	@Override
	public T apply(Integer n) {
		return get(n);
	}

	@Override
	public T any() {
		return IterablesInternal.anyInternal(this);
	}

	@Override
	public Option<T> anyOrNone() {
		return IterablesInternal.anyOrNoneInternal(this);
	}

	@Override
	public T anyOrNull() {
		return anyOrNone().valueOrNull();
	}

	@Override
	public T anyOrElse(Provider<T> provider) {
		return anyOrNone().valueOrElse(provider);
	}

	@Override
	public T anyOrElse(T value) {
		return anyOrNone().valueOrElse(value);
	}

	@Override
	public T find(Evaluable<? super T> predicate) {
		return Iterables.find(this, predicate);
	}

	@Override
	public Option<T> findOrNone(Evaluable<? super T> predicate) {
		return Iterables.findOrNone(this, predicate);
	}

	@Override
	public T findOrNull(Evaluable<? super T> predicate) {
		return findOrNone(predicate).valueOrNull();
	}

	@Override
	public T findOrElse(Evaluable<? super T> predicate,
		Provider<? extends T> provider) {
		return findOrNone(predicate).valueOrElse(provider);
	}

	@Override
	public boolean all(Evaluable<? super T> predicate) {
		return Iterables.all(this, predicate);
	}

	@Override
	public boolean any(Evaluable<? super T> predicate) {
		return Iterables.any(this, predicate);
	}

	@Override
	public <O> Stream<O> map(final Applicable<? super T, ? extends O> applicable) {
		return new AbstractStream<O>() {
			public Iterator<O> iterator() {
				final Iterator<T> iter = AbstractStream.this.iterator();
				return new AbstractUnmodifiableIterator<O>() {
					public boolean hasNext() {
						return iter.hasNext();
					}
					public O next() {
						return applicable.apply(iter.next());
					}
				};
			}
		};
	}

	@Override
	public <O, I extends Iterable<? extends O>> Stream<O> flatMap(
		final Applicable<? super T, I> applicable) {
		return new AbstractStream<O>() {
			public Iterator<O> iterator() {
				final Iterator<T> iter = AbstractStream.this.iterator();
				return new AbstractUnmodifiableIterator<O>() {
					private Iterator<? extends O> subIter;
					public boolean hasNext() {
						if (subIter != null && subIter.hasNext())
							return true;
						if (iter.hasNext()) {
							subIter = applicable.apply(iter.next()).iterator();
							if(subIter.hasNext())
								return true;
						}
						return false;
					}
					public O next() {
						return subIter.next();
					}
				};
			}
		};
	}

	@Override
	public T first() {
		return get(0);
	}

	@Override
	public T second() {
		return get(1);
	}

	@Override
	public T third() {
		return get(2);
	}

	@Override
	public T last() {
		return get(size() - 1);
	}

	@Override
	public T get(int n) {
		return Iterables.get(this, n);
	}

	@Override
	public Set<T> toSet() {
		return Iterables.asSet(this);
	}

	@Override
	public List<T> toList() {
		return Iterables.asList(this);
	}

}
