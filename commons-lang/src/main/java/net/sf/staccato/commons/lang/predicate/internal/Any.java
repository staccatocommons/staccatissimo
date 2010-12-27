package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.Evaluable;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Any<T> extends NonAnnonymousPredicate<T> {
	private static final long serialVersionUID = -2122639961072159594L;
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