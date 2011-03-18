package net.sf.staccatocommons.lang.predicate.internal;

import static net.sf.staccatocommons.lang.predicate.Predicates.*;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class True<T> extends NonAnnonymousPredicate<T> {
	private static final long serialVersionUID = 4329617085573720583L;

	public boolean eval(T argument) {
		return true;
	}

	/**
	 * @return the instance
	 */
	@Constant
	public static Predicate getInstance() {
		return new True();
	}

	@Override
	public Predicate<T> or(Evaluable<? super T> other) {
		return this;
	}

	@Override
	public Predicate<T> and(Evaluable<? super T> other) {
		return from(other);
	}

	@Override
	public Predicate<T> not() {
		return false_();
	}

}