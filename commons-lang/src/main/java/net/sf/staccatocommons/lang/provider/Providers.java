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
package net.sf.staccatocommons.lang.provider;

import java.util.Date;
import java.util.concurrent.Callable;

import net.sf.staccatocommons.check.annotation.ForceChecks;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.provider.internal.CallableProvider;
import net.sf.staccatocommons.lang.provider.internal.Constant;
import net.sf.staccatocommons.lang.provider.internal.DateProvider;
import net.sf.staccatocommons.lang.provider.internal.NullProvider;

/**
 * Class factory methods for some common {@link Provider}s
 * 
 * @author flbulgarelli
 * 
 */
public class Providers {

	private Providers() {}

	/**
	 * Returns a constant provider, that is, a {@link Thunk} that provides the
	 * given value
	 * 
	 * @param <A>
	 * @param value
	 *          the value the constant provider will return as when invoking
	 *          {@link Thunk#value()}
	 * @return a new constant provider
	 */
	@NonNull
	public static <A> Provider<A> constant(A value) {
		return new Constant<A>(value);

	}

	/**
	 * Returns a constant {@link Thunk} that always provides * <code>null</code>
	 * 
	 * @param <A>
	 * @return a singleton provider of nulls
	 */
	@NonNull
	public static <A> Provider<A> null_() {
		return NullProvider.getInstance();
	}

	@NonNull
	public static Provider<Date> currentDate() {
		return DateProvider.PROVIDER;
	}

	/**
	 * Returns a provider whose value is retrieved sending {@link Callable#call()}
	 * to the given {@link Callable}
	 * 
	 * @param <A>
	 * @param callable
	 * @return a new {@link Thunk} that wraps the given callable
	 */
	@NonNull
	public static <A> Provider<A> from(@NonNull Callable<A> callable) {
		return new CallableProvider<A>(callable);
	}

	/**
	 * Returns a cell that provides not actual value, but a side effect instead,
	 * by sending {@link Runnable#run()} to the given <code>runnable</code>
	 * 
	 * @param runnable
	 * @return a new {@link Provider} that wraps the given {@link Runnable}
	 */
	@NonNull
	@ForceChecks
	public static Provider<Void> from(@NonNull final Runnable runnable) {
		return new Provider<Void>() {
			public Void value() {
				runnable.run();
				return null;
			}
		};
	}

	@NonNull
	@ForceChecks
	public static <T> Provider<T> from(@NonNull final Thunk<T> provider) {
		if (provider instanceof Provider)
			return (Provider<T>) provider;
		return new Provider<T>() {
			public T value() {
				return provider.value();
			}
		};
	}

}
