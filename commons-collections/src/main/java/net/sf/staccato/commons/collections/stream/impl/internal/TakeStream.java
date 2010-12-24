package net.sf.staccato.commons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccato.commons.collections.stream.AbstractStream;
import net.sf.staccato.commons.collections.stream.Stream;

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
	public TakeStream(@NonNull Stream<A> stream, @NonNull int amountOfElements) {
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