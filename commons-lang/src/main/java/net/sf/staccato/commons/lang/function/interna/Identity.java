package net.sf.staccato.commons.lang.function.interna;

import net.sf.staccato.commons.lang.function.Function;


/**
 * @author flbulgarelli
 * 
 * @param <I>
 */
public final class Identity<I> extends Function<I, I> {
	private static final Function INSTANCE = new Identity();

	@Override
	public I apply(I argument) {
		return argument;
	}

	/**
	 * @return the singleton instance
	 */
	public static <I> Function<I, I> getInstance() {
		return INSTANCE;
	}
}