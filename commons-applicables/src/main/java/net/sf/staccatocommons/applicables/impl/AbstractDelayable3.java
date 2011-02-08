/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.applicables.impl;

import net.sf.staccatocommons.applicables.Delayable3;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public abstract class AbstractDelayable3<A, B, C, D> implements Applicable3<A, B, C, D>,
	Delayable3<A, B, C, D> {

	/**
	 * Creates a new {@link AbstractDelayable3}
	 */
	public AbstractDelayable3() {
		super();
	}

	/**
	 * Delays execution of this block by returning a void provider that will
	 * evaluate <code>exec(arg1, arg2, arg3)</code> each time its value is
	 * required
	 * 
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return a new void {@link Provider}
	 */
	public Thunk<D> delayed(final A arg1, final B arg2, final C arg3) {
		return new Thunk<D>() {
			public D value() {
				return apply(arg1, arg2, arg3);
			}
		};
	}

}