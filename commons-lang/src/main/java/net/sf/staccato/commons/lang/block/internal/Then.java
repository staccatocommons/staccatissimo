package net.sf.staccato.commons.lang.block.internal;

import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.block.Block;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Then<T> extends Block<T> {
	private final Executable<? super T> next;
	private final Block<T> first;

	/**
	 * Creates a new {@link Then}
	 * 
	 * @param first
	 * @param next
	 */
	public Then(Block<T> first, Executable<? super T> next) {
		this.next = next;
		this.first = first;
	}

	public void exec(T argument) {
		first.exec(argument);
		next.exec(argument);
	}
}