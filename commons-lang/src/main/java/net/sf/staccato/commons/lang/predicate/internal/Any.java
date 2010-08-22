package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Any<T> extends Predicate<T> {
	private final Iterable<Evaluable<T>> predicates;

	/**
	 * Creates a new {@link All}
	 * 
	 * @param predicates
	 * 
	 */
	public Any(Iterable<Evaluable<T>> predicates) {
		this.predicates = predicates;
	}

	public boolean eval(T argument) {
		for (Evaluable<T> predicate : predicates)
			if (predicate.eval(argument))
				return true;
		return false;
	}
}