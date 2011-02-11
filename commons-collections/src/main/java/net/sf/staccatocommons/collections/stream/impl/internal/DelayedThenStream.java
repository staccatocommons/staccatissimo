package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class DelayedThenStream<A, B> extends AbstractStream<B> {
	private final Stream stream;
	private final DeconsApplicable<A, B> function;

	/**
	 * Creates a new {@link DelayedThenStream}
	 */
	public DelayedThenStream(Stream<A> stream, DeconsApplicable<A, B> function) {
		this.stream = stream;
		this.function = function;
	}

	public Thriterator<B> iterator() {
		return new DynamicIterator<B>() {
			protected Thriterator<B> createIter() {
				if (stream.isEmpty())
					return function.emptyApply().iterator();
				Pair<Thunk<A>, Stream<A>> decons = stream.delayedDecons();
				return function.delayedApply(decons._1(), decons._2()).iterator();
			}
		};
	}
}