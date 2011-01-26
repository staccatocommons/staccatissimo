package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.lang.function.Function;

/**
 * @author flbulgarelli
 * 
 * @param <I>
 */
public final class Identity<I> extends Function<I, I> implements Serializable {

	private static final long serialVersionUID = -9042770205177366369L;

	private static final Function INSTANCE = new Identity();

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
}