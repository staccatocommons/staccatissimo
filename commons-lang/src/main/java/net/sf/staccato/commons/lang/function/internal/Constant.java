package net.sf.staccato.commons.lang.function.internal;

import net.sf.staccato.commons.lang.function.Function;


/**
 * @author flbulgarelli
 * 
 * @param <I>
 * @param <O>
 */
public final class Constant<I, O> extends Function<I, O> {
	/**
	 * 
	 */
	private final O value;

	/**
	 * Creates a new {@link Constant}
	 */
	public Constant(O value) {
		this.value = value;
	}

	public O apply(I argument) {
		return value;
	}
}