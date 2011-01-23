package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public final class ConcatStream<A> extends AbstractStream<A> {
	private final Stream<A> stream;
	private final Iterable<A> other;

	/**
	 * Creates a new {@link ConcatStream}
	 */
	public ConcatStream(@NonNull Stream<A> stream, @NonNull Iterable<A> other) {
		this.stream = stream;
		this.other = other;
	}

	public Iterator<A> iterator() {
		return new AbstractUnmodifiableIterator<A>() {
			private Iterator<A> iter = stream.iterator();
			private boolean second = false;

			public boolean hasNext() {
				if (iter.hasNext())
					return true;

				if (second)
					return false;

				iter = other.iterator();
				second = true;
				return iter.hasNext();
			}

			public A next() {
				return iter.next();
			}

		};
	}
}