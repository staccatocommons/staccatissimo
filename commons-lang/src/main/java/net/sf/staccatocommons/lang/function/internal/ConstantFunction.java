package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.function.Function;

/**
 * @author flbulgarelli
 * 
 * @param <I>
 * @param <O>
 */
public final class ConstantFunction<I, O> extends Function<I, O> implements Serializable {
	private static final long serialVersionUID = 5134677209037542760L;

	private final O value;

	/**
	 * Creates a new {@link ConstantFunction}
	 * 
	 * @param value
	 */
	public ConstantFunction(O value) {
		this.value = value;
	}

	public O apply(I argument) {
		return value;
	}

}