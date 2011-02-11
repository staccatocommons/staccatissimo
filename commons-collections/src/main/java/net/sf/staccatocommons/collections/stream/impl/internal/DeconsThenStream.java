package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <A>
 */
public final class DeconsThenStream<B, A> extends AbstractStream<B> {
	private final Stream<A> stream;
	private final DeconsApplicable<A, B> function;

	/**
	 * Creates a new {@link DeconsThenStream}
	 */
	public DeconsThenStream(Stream<A> stream, DeconsApplicable<A, B> function) {
		this.stream = stream;
		this.function = function;
	}

	public Thriterator<B> iterator() {
		return new DynamicIterator<B>() {
			protected Thriterator<B> createIter() {
				if (stream.isEmpty())
					return function.emptyApply().iterator();
				Pair<A, Stream<A>> decons = stream.decons();
				return function.apply(decons._1(), decons._2()).iterator();
			}
		};
	}
}