package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeStream<A> extends AbstractStream<A> {

	private final Stream<A> stream;
	private final int amountOfElements;

	/**
	 * Creates a new {@link TakeStream}
	 */
	public TakeStream(@NonNull Stream<A> stream, int amountOfElements) {
		this.stream = stream;
		this.amountOfElements = amountOfElements;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<A>() {
			private int i = 0;

			public boolean hasNext() {
				return i < amountOfElements && iter.hasNext();
			}

			public A next() {
				i++;
				return iter.next();
			}
		};
	}
}