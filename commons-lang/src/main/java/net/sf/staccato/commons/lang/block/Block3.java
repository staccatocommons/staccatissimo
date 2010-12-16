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

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Executable3;
import net.sf.staccato.commons.lang.SoftException;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public abstract class Block3<T1, T2, T3> implements Executable3<T1, T2, T3>,
	Applicable<T1, Block2<T2, T3>>, Applicable2<T1, T2, Block<T3>> {

	public void exec(T1 argument1, T2 argument2, T3 argument3) {
		try {
			softExec(argument1, argument2, argument3);
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

	protected void softExec(T1 argument1, T2 argument2, T3 argument3) throws Exception {
	}

	public Block2<T2, T3> apply(final T1 arg) {
		return new Block2<T2, T3>() {
			public void exec(T2 argument1, T3 argument2) {
				Block3.this.exec(arg, argument1, argument2);
			}
		};
	}

	public Block<T3> apply(final T1 arg1, final T2 arg2) {
		return new Block<T3>() {
			public void exec(T3 argument) {
				Block3.this.exec(arg1, arg2, argument);
			}
		};
	}

	public Block3<T1, T2, T3> then(final Block3<T1, T2, T3> other) {
		return new Block3<T1, T2, T3>() {
			public void exec(T1 argument1, T2 argument2, T3 argument3) {
				Block3.this.exec(argument1, argument2, argument3);
				other.exec(argument1, argument2, argument3);
			}
		};
	}

	public String toString() {
		return "Block3";
	}

}
