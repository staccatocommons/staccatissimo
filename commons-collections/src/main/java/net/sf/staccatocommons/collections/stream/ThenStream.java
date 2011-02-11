package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class ThenStream<A, B> extends AbstractStream<B> {
	/**
	 * 
	 */
	private final AbstractStream<A> stream;
	/**
	 * 
	 */
	private final Applicable<Stream<A>, ? extends Stream<B>> function;

	/**
	 * Creates a new {@link ThenStream}
	 */
	public ThenStream(AbstractStream<A> abstractStream,
		Applicable<Stream<A>, ? extends Stream<B>> function) {
		stream = abstractStream;
		this.function = function;
	}

	public Thriterator<B> iterator() {
		return function.apply(stream).iterator();
	}
}