package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <A>
 */
public final class DeconsTransformStream<B, A> extends AbstractTransformStream<A, B> {
	private final DeconsApplicable<A, B> function;

	/**
	 * Creates a new {@link DeconsTransformStream}
	 */
	public DeconsTransformStream(Stream<A> stream, DeconsApplicable<A, B> function) {
		super(stream);
		this.function = function;
	}

	@Override
	protected Stream<B> apply() {
		if (getStream().isEmpty())
			return function.emptyApply();
		Pair<A, Stream<A>> decons = getStream().decons();
		return function.apply(decons._0(), decons._1());
	}
}