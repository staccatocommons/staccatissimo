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
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Executable2;
import net.sf.staccato.commons.lang.SoftException;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 */
public abstract class Block2<T1, T2> implements Executable2<T1, T2>, Applicable2<T1, T2, Void>,
	Applicable<T1, Block<T2>> {

	public void exec(T1 argument1, T2 argument2) {
		try {
			softExec(argument1, argument2);
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

	protected void softExec(T1 argument1, T2 argument2) throws Exception {
	}

	public Void apply(T1 arg1, T2 arg2) {
		exec(arg1, arg2);
		return null;
	};

	@NonNull
	public Block<T2> apply(final T1 argument1) {
		return new Block<T2>() {
			public void exec(T2 argument2) {
				Block2.this.exec(argument1, argument2);
			}
		};
	}

	@NonNull
	public final Block2<T1, T2> then(@NonNull final Block2<T1, T2> other) {
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
