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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.lang.Lazy;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractApplicable<T, R> implements Applicable<T, R> {

	/**
	 * Lazily applies this applicable, by returning a {@link Lazy} that will send
	 * {@link #apply(Object)} when {@link Lazy#value()} is evaluated by first
	 * time.
	 * 
	 * @param arg
	 * @return a new {@link Lazy}
	 */
	public Provider<R> lazy(final T arg) {
		return new Lazy<R>() {
			protected R init() {
				return apply(arg);
			}
		};
	}

}
