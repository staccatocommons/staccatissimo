package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.defs.Evaluable;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class All<T> extends NonAnnonymousPredicate<T> {
	private static final long serialVersionUID = 2032230415038798930L;
	private final Iterable<Evaluable<T>> predicates;

	/**
	 * Creates a new {@link All}
	 * 
	 * @param predicates
	 */
	public All(Iterable<Evaluable<T>> predicates) {
		this.predicates = predicates;
	}

	public boolean eval(T argument) {
		for (Evaluable<T> predicate : predicates)
			if (!predicate.eval(argument))
				return false;
		return true;
	}
}