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
package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Delayable2;
import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 */
public abstract class AbstractDelayable2<A, B, C> implements Applicable2<A, B, C>,
	Delayable2<A, B, C> {

	/**
	 * Creates a new {@link AbstractDelayable2}
	 */
	public AbstractDelayable2() {
		super();
	}

	/**
	 * Delays execution of this block by returning a void provider that will
	 * evaluate <code>exec(arg1, arg2)</code> each time its value is required
	 * 
	 * @param arg1
	 * @param arg2
	 * @return a new void {@link Provider}
	 */
	public Thunk<C> delayed(final A arg1, final B arg2) {
		return new Thunk<C>() {
			public C value() {
				return apply(arg1, arg2);
			}
		};
	}

}