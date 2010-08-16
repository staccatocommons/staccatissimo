package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;


/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Same<T> extends Predicate<T> {
	private final T value;

	/**
	 * Creates a new {@link Same}
	 */
	public Same(T value) {
		this.value = value;
	}

	public boolean eval(T argument) {
		return value == argument;
	}
}