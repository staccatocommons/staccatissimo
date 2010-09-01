package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class And<T> extends Predicate<T> {
	private final Evaluable<T> evaluable;
	private final Evaluable<T> other;

	/**
	 * Creates a new {@link And}
	 * 
	 * @param evaluable
	 * @param other
	 */
	public And(Evaluable<T> evaluable, Evaluable<T> other) {
		this.evaluable = evaluable;
		this.other = other;
	}

	public boolean eval(T argument) {
		return evaluable.eval(argument) && other.eval(argument);
	}
}