package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class ThenStream<A, B> extends AbstractThenStream<A, B> {
	/**
	 * 
	 */
	private final Applicable<Stream<A>, ? extends Stream<B>> function;

	/**
	 * Creates a new {@link ThenStream}
	 */
	public ThenStream(AbstractStream<A> stream, Applicable<Stream<A>, ? extends Stream<B>> function) {
		super(stream);
		this.function = function;
	}

	protected Stream<B> apply() {
		return function.apply(getStream());
	}

}