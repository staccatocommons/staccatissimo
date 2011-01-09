package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <I>
 */
public final class FlatMapStream<A, B> extends AbstractStream<B> {
	private final Stream<A> stream;
	private final Applicable<? super A, ? extends Iterable<? extends B>> function;

	/**
	 * Creates a new {@link FlatMapStream}
	 */
	public FlatMapStream(@NonNull Stream<A> stream,
		@NonNull Applicable<? super A, ? extends Iterable<? extends B>> function) {
		this.stream = stream;
		this.function = function;
	}

	public Iterator<B> iterator() {

		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<B>() {
			private Iterator<? extends B> subIter = null;

			public boolean hasNext() {
				if (subIter != null && subIter.hasNext())
					return true;
				while (iter.hasNext()) {
					subIter = function.apply(iter.next()).iterator();
					if (subIter.hasNext())
						return true;
				}
				return false;
			}

			public B next() {
				if (subIter == null)
					throw new NoSuchElementException();
				return subIter.next();
			}
		};
	}
}