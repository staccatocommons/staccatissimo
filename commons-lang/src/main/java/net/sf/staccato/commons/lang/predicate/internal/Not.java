package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Not<T> extends Predicate<T> {
	private final Predicate<T> predicate;

	/**
	 * Creates a new {@link Not}
	 * 
	 * @param predicate
	 */
	public Not(Predicate<T> predicate) {
		this.predicate = predicate;
	}

	public boolean eval(T argument) {
		return !predicate.eval(argument);
	}

	@Override
	public Predicate<T> not() {
		return predicate;
	}

}