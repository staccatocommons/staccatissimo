package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Or<T> extends Predicate<T> {
	private final Evaluable<T> predicate;
	private final Evaluable<T> other;

	/**
	 * Creates a new {@link Or}
	 * 
	 * @param predicate
	 * @param other
	 */
	public Or(Evaluable<T> predicate, Evaluable<T> other) {
		this.other = other;
		this.predicate = predicate;
	}

	public boolean eval(T argument) {
		return predicate.eval(argument) || other.eval(argument);
	}
}