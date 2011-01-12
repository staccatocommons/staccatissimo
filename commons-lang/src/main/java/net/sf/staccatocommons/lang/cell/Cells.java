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
package net.sf.staccatocommons.lang.cell;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.lang.cell.internal.CallableCell;
import net.sf.staccatocommons.lang.cell.internal.Constant;
import net.sf.staccatocommons.lang.cell.internal.NullCell;

/**
 * Class factory methods for some common {@link Cell}s
 * 
 * @author flbulgarelli
 * 
 */
public class Cells {

	private Cells() {}

	/**
	 * Returns a constant provider, that is, a {@link Provider} that provides the
	 * given value
	 * 
	 * @param <A>
	 * @param value
	 *          the value the constant provider will return as when invoking
	 *          {@link Provider#value()}
	 * @return a new constant provider
	 */
	@NonNull
	public static <A> Cell<A> constant(A value) {
		return new Constant<A>(value);

	}

	/**
	 * Returns a constant {@link Provider} that always provides *
	 * <code>null</code>
	 * 
	 * @param <A>
	 * @return a singleton provider of nulls
	 */
	@NonNull
	public static <A> Cell<A> null_() {
		return NullCell.getInstance();
	}

	/**
	 * Returns a provider whose value is retrieved sending {@link Callable#call()}
	 * to the given {@link Callable}
	 * 
	 * @param <A>
	 * @param callable
	 * @return a new {@link Provider} that wraps the given callable
	 */
	@NonNull
	public static <A> Cell<A> from(@NonNull Callable<A> callable) {
		return new CallableCell<A>(callable);
	}

	public static Cell<Void> from(final Runnable runnable) {
		return new Cell<Void>() {
			public Void value() {
				runnable.run();
				return null;
			}
		};
	}

	public static <T> Cell<T> from(final Provider<T> provider) {
		return new Cell<T>() {
			public T value() {
				return provider.value();
			}
		};
	}

}
