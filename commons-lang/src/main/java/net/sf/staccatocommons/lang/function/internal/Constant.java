package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.function.Function;

/**
 * @author flbulgarelli
 * 
 * @param <I>
 * @param <O>
 */
public final class Constant<I, O> extends Function<I, O> implements Serializable {
	private static final long serialVersionUID = 5134677209037542760L;

	private final O value;

	/**
	 * Creates a new {@link Constant}
	 * 
	 * @param value
	 */
	public Constant(O value) {
		this.value = value;
	}

	public O apply(I argument) {
		return value;
	}

}