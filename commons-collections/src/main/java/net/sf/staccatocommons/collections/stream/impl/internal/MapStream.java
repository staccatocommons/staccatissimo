package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.lang.function.Functions;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class MapStream<A, B> extends AbstractStream<B> {
	private final Stream<A> stream;
	private final Applicable<? super A, ? extends B> function;

	/**
	 * Creates a new {@link MapStream}
	 */
	public MapStream(@NonNull Stream<A> stream, @NonNull Applicable<? super A, ? extends B> function) {
		this.stream = stream;
		this.function = function;
	}

	public Iterator<B> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<B>() {
			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			public B next() {
				return function.apply(iter.next());
			}
		};
	}

	@Override
	public <C> Stream<C> map(final Applicable<? super B, ? extends C> function) {
		return new MapStream<A, C>(stream, Functions.from(function).of(this.function));
	}

}