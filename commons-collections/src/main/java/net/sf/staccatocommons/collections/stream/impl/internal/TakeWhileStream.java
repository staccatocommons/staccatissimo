package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.defs.Evaluable;
import net.sf.staccatocommons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeWhileStream<A> extends AbstractStream<A> {
	private final Stream<A> stream;
	private final Evaluable<? super A> predicate;

	/**
	 * Creates a new {@link TakeWhileStream}
	 */
	public TakeWhileStream(@NonNull Stream<A> stream, @NonNull Evaluable<? super A> predicate) {
		this.stream = stream;
		this.predicate = predicate;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<A>() {
			private A next;
			private Boolean hasNext = null;

			public boolean hasNext() {
				if (hasNext == null)
					hasNext = iter.hasNext() && predicate.eval((next = iter.next()));
				return hasNext;
			}

			public A next() {
				if (!hasNext())
					throw new NoSuchElementException();
				hasNext = null;
				return next;
			}

		};
	}
}