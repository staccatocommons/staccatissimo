package net.sf.staccato.commons.lang.block.internal;

import net.sf.staccato.commons.lang.block.Block;
import net.sf.staccato.commons.lang.block.Block2;

/**
 * @author flbulgarelli
 * 
 * @param <E>
 */
final class Catch<E, T> extends Block<T> {

	private final Block<T> block;
	private final Class<E> exceptionType;
	private final Block2<? super E, T> catchBlock;

	/**
	 * Creates a new {@link Catch}
	 */
	public Catch(Block<T> block, Class<E> exceptionType,
		Block2<? super E, T> catchBlock) {
		this.block = block;
		this.exceptionType = exceptionType;
		this.catchBlock = catchBlock;
	}

	public void exec(T arg) {
		try {
			this.block.exec(arg);
		} catch (RuntimeException e) {
			if (exceptionType.isInstance(e))
				catchBlock.exec((E) e, arg);
			else
				throw e;
		}
	}
}