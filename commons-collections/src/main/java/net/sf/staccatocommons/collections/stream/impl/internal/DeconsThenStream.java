package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <A>
 */
public final class DeconsThenStream<B, A> extends AbstractThenStream<A, B> {
	private final DeconsApplicable<A, B> function;

	/**
	 * Creates a new {@link DeconsThenStream}
	 */
	public DeconsThenStream(Stream<A> stream, DeconsApplicable<A, B> function) {
		super(stream);
		this.function = function;
	}

	@Override
	protected Stream<B> apply() {
		if (getStream().isEmpty())
			return function.emptyApply();
		Pair<A, Stream<A>> decons = getStream().decons();
		return function.apply(decons._1(), decons._2());
	}
}