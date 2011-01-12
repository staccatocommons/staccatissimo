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
package net.sf.staccatocommons.lang.function.internal;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.lang.Lazy;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractApplicable2<T1, T2, R> implements Applicable2<T1, T2, R> {

	/**
	 * Lazily applies this applicable, by returning a {@link Lazy} that will send
	 * {@link #apply(Object, Object)} when {@link Lazy#value()} is evaluated by
	 * first time.
	 * 
	 * @param arg1
	 * @param arg2
	 * @return a new {@link Lazy}
	 */
	public Provider<R> lazy(final T1 arg1, final T2 arg2) {
		return new Lazy<R>() {
			protected R init() {
				return apply(arg1, arg2);
			}
		};
	}

}
