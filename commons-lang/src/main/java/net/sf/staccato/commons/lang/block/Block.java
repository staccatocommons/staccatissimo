/*
 Copyright (c) 2010, The Staccato-Commons Team   
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.block;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.SoftException;
import net.sf.staccato.commons.lang.block.internal.Then;

/**
 * An abstract, one argument code block, that implements {@link Executable}
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public abstract class Block<T> implements Executable<T> {

	/**
	 * Executes this block. This implementation just invokes
	 * {@link #softExec(Object)}, and softens any exception it may throw.
	 * Subclasses may override this behavior.
	 * 
	 */
	@Override
	public void exec(@NonNull T argument) {
		try {
			softExec(argument);
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

	/**
	 * Executes this block, potentially throwing a checked excpetion
	 * 
	 * @see #exec(Object)
	 * 
	 * @param argument
	 * @throws Exception
	 */
	protected void softExec(@NonNull T argument) throws Exception {
	}

	/**
	 * Creates a new {@link Block} that executes this one and then another one
	 * provided.
	 * 
	 * @param other
	 * @return a new block
	 */
	@NonNull
	public Block<T> then(@NonNull final Executable<? super T> other) {
		return new Then<T>(this, other);
	}

	public Block<T> andCatch(final Block2<? super RuntimeException, T> catchBlock) {
		return andCatch(RuntimeException.class, catchBlock);
	}

	// TODO reflection aware? soft exception ware?
	public <E extends RuntimeException> Block<T> andCatch(
		final Class<E> exceptionType, final Block2<? super E, T> catchBlock) {
		return new Catch<E, T>(this, exceptionType, catchBlock);
	}

	public <E extends Throwable> Block<T> andFinally(
		final Executable<T> finallyBlock) {
		return new Block<T>() {
			public void exec(T arg) {
				try {
					Block.this.exec(arg);
				} finally {
					finallyBlock.exec(arg);
				}
			}
		};
	}

}
