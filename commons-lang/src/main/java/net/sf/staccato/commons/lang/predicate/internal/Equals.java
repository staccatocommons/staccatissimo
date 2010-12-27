package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Equals<T> extends NonAnnonymousPredicate<T> {
	private static final long serialVersionUID = -7587113479123370400L;
	private final T value;

	/**
	 * Creates a new {@link Equals}
	 * 
	 * @param value
	 *          the value to test equality
	 */
	public Equals(T value) {
		this.value = value;
	}

	public boolean eval(@NonNull T argument) {
		return argument.equals(value);
	}
}