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
package net.sf.staccatocommons.lang.thunk;

import java.util.Date;
import java.util.concurrent.Callable;

import net.sf.staccatocommons.check.annotation.ForceChecks;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.iterators.thriter.internal.ConstantThunk;
import net.sf.staccatocommons.lang.thunk.internal.CallableThunk;
import net.sf.staccatocommons.lang.thunk.internal.DateThunk;
import net.sf.staccatocommons.lang.thunk.internal.NullThunk;

/**
 * Class factory methods for some common {@link Thunk}s
 * 
 * @author flbulgarelli
 * 
 */
public class Thunks {

	private Thunks() {}

	/**
	 * Returns a thunk that returns always the given value
	 * 
	 * @param <A>
	 * @param value
	 *          the value the constant thunk will return as when invoking
	 *          {@link Thunk#value()}
	 * @return a new thunk
	 */
	@NonNull
	public static <A> Thunk<A> constant(A value) {
		return new ConstantThunk<A>(value);

	}

	/**
	 * Returns a constant {@link Thunk} that always provides <code>null</code>
	 * 
	 * @param <A>
	 * @return a constant thunk of nulls
	 */
	@NonNull
	@Constant
	public static <A> Thunk<A> null_() {
		return NullThunk.getInstance();
	}

	/**
	 * Returns a {@link Thunk} that provides the current date
	 * 
	 * @return a constant thunk that provides <code>new Date()</code>
	 */
	@NonNull
	@Constant
	public static Thunk<Date> currentDate() {
		return DateThunk.PROVIDER;
	}

	/**
	 * Returns a thunk whose value is retrieved sending {@link Callable#call()}
	 * to the given {@link Callable}
	 * 
	 * @param <A>
	 * @param callable
	 * @return a new {@link Thunk} that wraps the given callable
	 */
	@NonNull
	public static <A> Thunk<A> from(@NonNull Callable<A> callable) {
		return new CallableThunk<A>(callable);
	}

	/**
	 * Returns a cell that provides not actual value, but a side effect instead,
	 * by sending {@link Runnable#run()} to the given <code>runnable</code>
	 * 
	 * @param runnable
	 * @return a new {@link Thunk} that wraps the given {@link Runnable}
	 */
	@NonNull
	@ForceChecks
	public static Thunk<Void> from(@NonNull final Runnable runnable) {
		return new Thunk<Void>() {
			public Void value() {
				runnable.run();
				return null;
			}
		};
	}
}