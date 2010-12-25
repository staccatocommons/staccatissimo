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
import net.sf.staccato.commons.lang.Executable2;
import net.sf.staccato.commons.lang.SoftException;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 */
public abstract class Block2<T1, T2> implements Executable2<T1, T2>, Applicable<T1, Block<T2>> {

	/**
	 * Executes this block. This implementation just invokes
	 * {@link #softExec(Object, Object)}, and softens any exception it may throw.
	 * Subclasses may override this behavior.
	 */
	public void exec(T1 argument1, T2 argument2) {
		try {
			softExec(argument1, argument2);
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

	/**
	 * Executes this block, potentially throwing a checked {@link Exception}
	 * 
	 * @see #exec(Object, Object)
	 * 
	 * @param argument
	 * @throws Exception
	 */
	protected void softExec(T1 argument1, T2 argument2) throws Exception {
	}

	@NonNull
	public Block<T2> apply(final T1 argument1) {
		return new Block<T2>() {
			public void exec(T2 argument2) {
				Block2.this.exec(argument1, argument2);
			}
		};
	}

	/**
	 * Chains this block with the given executable, creating a new {@link Block2}
	 * that executes this one and then the another one.
	 * 
	 * @param other
	 *          the block to execute after this
	 * @return a new block that first invokes execute on this, and then on the
	 *         {@link Executable2} provided
	 */
	@NonNull
	public final Block2<T1, T2> then(@NonNull final Executable2<T1, T2> other) {
		return new Block2<T1, T2>() {
			public void exec(T1 argument1, T2 argument2) {
				Block2.this.exec(argument1, argument2);
				other.exec(argument1, argument2);
			}
		};
	}

	public String toString() {
		return "Block2";
	}

}
