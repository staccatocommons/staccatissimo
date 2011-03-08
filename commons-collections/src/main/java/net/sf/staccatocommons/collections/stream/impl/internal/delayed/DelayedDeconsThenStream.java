package net.sf.staccatocommons.collections.stream.impl.internal.delayed;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.internal.AbstractThenStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class DelayedDeconsThenStream<A, B> extends AbstractThenStream<A, B> {
	private final DeconsApplicable<A, B> function;

	/**
	 * Creates a new {@link DelayedDeconsThenStream}
	 */
	public DelayedDeconsThenStream(Stream<A> stream, DeconsApplicable<A, B> function) {
		super(stream);
		this.function = function;
	}

	protected Stream<B> apply() {
		if (getStream().isEmpty())
			return function.emptyApply();
		Pair<Thunk<A>, Stream<A>> decons = getStream().delayedDecons();
		return function.delayedApply(decons._1(), decons._2());
	}
}