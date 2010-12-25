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
package net.sf.staccato.commons.lang.provider;

import java.util.concurrent.Callable;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.provider.internal.CallableProvider;
import net.sf.staccato.commons.lang.provider.internal.Constant;
import net.sf.staccato.commons.lang.provider.internal.NullProvider;

/**
 * Class factory methods for some common {@link Provider}s
 * 
 * @author flbulgarelli
 * 
 */
public class Providers {

	private Providers() {
	}

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
	public static <A> Provider<A> constant(A value) {
		return new Constant<A>(value);
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
	public static <A> Provider<A> callable(@NonNull Callable<A> callable) {
		return new CallableProvider<A>(callable);
	}

	/**
	 * Returns a constant {@link Provider} that always provides *
	 * <code>null</code>
	 * 
	 * @param <A>
	 * @return a singleton provider of nulls
	 */
	@NonNull
	public static <A> Provider<A> null_() {
		return NullProvider.getInstance();
	}

}
