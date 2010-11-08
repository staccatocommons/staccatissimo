package net.sf.staccato.commons.collections.stream.internal;

import java.util.Iterator;

import net.sf.staccato.commons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccato.commons.collections.stream.AbstractStream;
import net.sf.staccato.commons.collections.stream.Stream;
import net.sf.staccato.commons.lang.Evaluable;

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
	public FilterStream(Stream<A> stream,
		Evaluable<? super A> predicate) {
		this.stream = stream;
		this.predicate = predicate;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<A>() {
			private A next;

			public boolean hasNext() {
				while (iter.hasNext())
					if (predicate.eval((next = iter.next())))
						return true;
				return false;
			}

			public A next() {
				return next;
			}
		};
	}
}