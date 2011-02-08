package net.sf.staccatocommons.applicables.internal;

import java.io.Serializable;

import net.sf.staccatocommons.applicables.function.Function;
import net.sf.staccatocommons.applicables.impl.AbstractFunction;

/**
 * @author flbulgarelli
 * 
 * @param <I>
 * @param <O>
 */
public final class ConstantFunction<I, O> extends AbstractFunction<I, O> implements Serializable {
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

	public Function<I, O> nullSafe() {
		return this;
	}

}