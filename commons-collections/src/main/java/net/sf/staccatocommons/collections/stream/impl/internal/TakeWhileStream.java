package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.NextGetIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.predicate.Predicates;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeWhileStream<A> extends AbstractStream<A> {
	private final Stream<A> stream;
	private final Evaluable<? super A> predicate;

	/**
	 * Creates a new {@link TakeWhileStream}
	 */
	public TakeWhileStream(@NonNull Stream<A> stream, @NonNull Evaluable<? super A> predicate) {
		this.stream = stream;
		this.predicate = predicate;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new NextGetIterator<A>() {
			protected Boolean updateNext() {
				return iter.hasNext() && predicate.eval(setNext(iter.next()));
			}
		};
	}

	public Stream<A> takeWhile(Evaluable<? super A> predicate) {
		return new TakeWhileStream<A>(stream, Predicates.from(this.predicate).and(predicate));
	}
}