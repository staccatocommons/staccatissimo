package net.sf.staccatocommons.lang.block.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.block.Block;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Empty<T> extends Block<T> implements Serializable {

	private static final long serialVersionUID = -8524759590808189374L;

	private static final Block INSTANCE = new Empty();

	public void exec(T argument) {
	}

	/**
	 * @param <T>
	 * @return an instance of an empty block
	 */
	public static <T> Block<T> getInstance() {
		return INSTANCE;
	}
}