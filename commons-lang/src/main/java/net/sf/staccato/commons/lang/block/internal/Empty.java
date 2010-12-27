package net.sf.staccato.commons.lang.block.internal;

import java.io.Serializable;

import net.sf.staccato.commons.lang.block.Block;

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