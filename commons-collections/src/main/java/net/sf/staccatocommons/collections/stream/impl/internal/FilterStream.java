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
import net.sf.staccatocommons.collections.internal.iterator.NextGetIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.predicate.Predicates;

/**
 * @author flbulgarelli
 * 
 */
public final class FilterStream<A> extends AbstractStream<A> {
	private final Stream<A> stream;
	private final Evaluable<? super A> predicate;

	/**
	 * Creates a new {@link FilterStream}
	 */
	public FilterStream(@NonNull Stream<A> stream, @NonNull Evaluable<? super A> predicate) {
		this.stream = stream;
		this.predicate = predicate;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new NextGetIterator<A>() {
			protected Boolean updateNext() {
				while (iter.hasNext())
					if (predicate.eval(setNext(iter.next())))
						return true;
				return false;
			}
		};
	}

	public Stream<A> filter(Evaluable<? super A> predicate) {
		return new FilterStream<A>(stream, Predicates.from(this.predicate).and(predicate));
	}
}