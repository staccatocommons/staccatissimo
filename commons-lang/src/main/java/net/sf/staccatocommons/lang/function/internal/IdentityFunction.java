package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.lang.function.AbstractFunction;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class IdentityFunction<A> extends AbstractFunction<A, A> implements Serializable {

	private static final long serialVersionUID = -9042770205177366369L;

	private static final Function INSTANCE = new IdentityFunction();

	@Override
	public A apply(A argument) {
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

	public Function<A, A> nullSafe() {
		return this;
	}
}