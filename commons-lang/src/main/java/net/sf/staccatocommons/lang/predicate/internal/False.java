package net.sf.staccatocommons.lang.predicate.internal;

import static net.sf.staccatocommons.lang.predicate.Predicates.*;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class False<T> extends NonAnnonymousPredicate<T> {
	private static final long serialVersionUID = 7804525181528599615L;

	public boolean eval(T argument) {
		return false;
	}

	/**
	 * @return the instance
	 */
	@Constant
	public static Predicate getInstance() {
		return new False();
	}

	@Override
	public Predicate<T> and(Evaluable<? super T> other) {
		return this;
	}

	@Override
	public Predicate<T> or(Evaluable<? super T> other) {
		return from(other);
	}

	@Override
	public Predicate<T> not() {
		return true_();
	}
}