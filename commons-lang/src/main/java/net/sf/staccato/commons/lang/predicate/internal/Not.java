package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Not<T> extends Predicate<T> {
	private final Evaluable<T> predicate;

	/**
	 * Creates a new {@link Not}
	 */
	public Not(Evaluable<T> predicate) {
		this.predicate = predicate;
	}

	public boolean eval(T argument) {
		return !predicate.eval(argument);
	}
}