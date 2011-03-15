package net.sf.staccatocommons.collections.stream.impl.internal.delayed;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.internal.AbstractTransformStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class DelayedDeconsTransformStream<A, B> extends AbstractTransformStream<A, B> {
	private final DelayedDeconsApplicable<A, B> function;

	/**
	 * Creates a new {@link DelayedDeconsTransformStream}
	 */
	public DelayedDeconsTransformStream(Stream<A> stream, DelayedDeconsApplicable<A, B> function) {
		super(stream);
		this.function = function;
	}

	protected Stream<B> apply() {
		if (getStream().isEmpty())
			return function.emptyApply();
		Pair<Thunk<A>, Stream<A>> decons = getStream().delayedDecons();
		return function.apply(decons._0(), decons._1());
	}
}