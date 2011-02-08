package net.sf.staccatocommons.applicables.internal;

import java.io.Serializable;

import net.sf.staccatocommons.applicables.function.Function;
import net.sf.staccatocommons.applicables.impl.AbstractFunction;
import net.sf.staccatocommons.defs.restriction.Constant;

/**
 * @author flbulgarelli
 * 
 * @param <I>
 */
public final class IdentityFunction<I> extends AbstractFunction<I, I> implements Serializable {

	private static final long serialVersionUID = -9042770205177366369L;

	private static final Function INSTANCE = new IdentityFunction();

	@Override
	public I apply(I argument) {
		return argument;
	}

	/**
	 * @param <I>
	 * @return a constant instance
	 */
	@Constant
	public static <I> Function<I, I> getInstance() {
		return INSTANCE;
	}

	public Function<I, I> nullSafe() {
		return this;
	}
}