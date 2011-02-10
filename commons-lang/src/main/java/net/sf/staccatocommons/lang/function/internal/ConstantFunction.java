package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.thunk.Thunks;

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

	@Override
	@NonNull
	public Function<I, O> nullSafe() {
		return this;
	}

	@NonNull
	@Override
	public Thunk<O> delayed(I arg) {
		return Thunks.constant(value);
	}

}