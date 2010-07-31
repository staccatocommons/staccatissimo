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

import net.sf.staccato.commons.lang.Executable;

public abstract class Block<T> implements Executable<T> {

	@Override
	public abstract void exec(T argument);

	public Block<T> then(final Executable<? super T> other) {
		return new Block<T>() {
			public void exec(T argument) {
				Block.this.exec(argument);
				other.exec(argument);
			}
		};
	}

	public Block<T> andCatch(final Block2<? super RuntimeException, T> catchBlock) {
		return andCatch(RuntimeException.class, catchBlock);
	}

	// TODO reflection aware? soft exception ware?
	public <E extends RuntimeException> Block<T> andCatch(
		final Class<E> exceptionType, final Block2<? super E, T> catchBlock) {
		return new Block<T>() {
			public void exec(T arg) {
				try {
					Block.this.exec(arg);
				} catch (RuntimeException e) {
					if (exceptionType.isInstance(e))
						catchBlock.exec((E) e, arg);
					else
						throw e;
				}
			}
		};
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
