package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class TransformStream<A, B> extends AbstractTransformStream<A, B> {
	/**
	 * 
	 */
	private final Applicable<Stream<A>, ? extends Stream<B>> function;

	/**
	 * Creates a new {@link TransformStream}
	 */
	public TransformStream(AbstractStream<A> stream, Applicable<Stream<A>, ? extends Stream<B>> function) {
		super(stream);
		this.function = function;
	}

	protected Stream<B> apply() {
		return function.apply(getStream());
	}

}