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
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.iterators.NextGetIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.lang.predicate.Predicates;

/**
 * @author flbulgarelli
 * 
 */
public final class FilterStream<A> extends WrapperStream<A> {
	private final Evaluable<? super A> predicate;

	/**
	 * Creates a new {@link FilterStream}
	 */
	public FilterStream(@NonNull Stream<A> stream, @NonNull Evaluable<? super A> predicate) {
		super(stream);
		this.predicate = predicate;
	}

	public Thriterator<A> iterator() {
		final Iterator<A> iter = getSource().iterator();
		return Thriterators.from(new NextGetIterator<A>() {
			protected Boolean updateNext() {
				while (iter.hasNext())
					if (predicate.eval(setNext(iter.next())))
						return true;
				return false;
			}
		});
	}

	public Stream<A> filter(Evaluable<? super A> predicate) {
		return new FilterStream<A>(getSource(), Predicates.from(this.predicate).and(predicate));
	}
}