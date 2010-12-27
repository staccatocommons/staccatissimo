package net.sf.staccato.commons.lang.predicate.internal;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Same<T> extends NonAnnonymousPredicate<T> {
	private static final long serialVersionUID = 3404033724148091585L;
	private final T value;

	/**
	 * Creates a new {@link Same}
	 * 
	 * @param value
	 */
	public Same(T value) {
		this.value = value;
	}

	public boolean eval(T argument) {
		return value == argument;
	}
}