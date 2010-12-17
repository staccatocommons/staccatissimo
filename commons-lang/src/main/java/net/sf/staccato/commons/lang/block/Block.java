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
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.SoftException;

/**
 * An abstract, one argument code block, that implements {@link Executable}
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public abstract class Block<T> implements Executable<T>, Applicable<T, Void> {

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

	public Void apply(T arg) {
		exec(arg);
		return null;
	}

	/**
	 * Executes this block, potentially throwing a checked excpetion
	 * 
	 * @see #exec(Object)
	 * 
	 * @param argument
	 * @throws Exception
	 */
	protected void softExec(T argument) throws Exception {
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
		return new Block<T>() {
			public void exec(T argument) {
				Block.this.exec(argument);
				other.exec(argument);
			}
		};
	}

	public String toString() {
		return "Block";
	}

}
