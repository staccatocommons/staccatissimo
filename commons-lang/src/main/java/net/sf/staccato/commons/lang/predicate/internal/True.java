package net.sf.staccato.commons.lang.predicate.internal;

import static net.sf.staccato.commons.lang.predicate.Predicates.false_;
import static net.sf.staccato.commons.lang.predicate.Predicates.from;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class True<T> extends Predicate<T> {
	private static final Predicate INSTANCE = new True();

	public boolean eval(T argument) {
		return true;
	}

	/**
	 * @return the instance
	 */
	public static Predicate getInstance() {
		return INSTANCE;
	}

	@Override
	public Predicate<T> or(Evaluable<T> other) {
		return this;
	}

	@Override
	public Predicate<T> and(Evaluable<T> other) {
		return from(other);
	}

	@Override
	public Predicate<T> not() {
		return false_();
	}

	@Override
	public Applicable<T, T> ifTrue(Applicable<? super T, ? extends T> aFunction) {
		return (Applicable<T, T>) aFunction;
	}
}