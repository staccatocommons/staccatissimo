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
package net.sf.staccatocommons.lang.provider;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.Handle;
import net.sf.staccatocommons.lang.SoftException;

import org.apache.commons.lang.ObjectUtils;

/**
 * {@link Provider} is an abstract, rich implementation of {@link Thunk}
 * 
 * Although it does define any abstract method, implementors
 * <strong>must</strong> redefine either {@link #call()} or {@link #value()}.
 * They <strong>should not</strong> redefine implement both, as {@link Provider}
 * implements each one in terms of the other in a consistent way.
 * 
 * @author flbulgarelli
 * 
 */
public abstract class Provider<A> implements Thunk<A>, ContainsAware<A>, Callable<A>, Runnable {

	/* Minimal definition */

	/**
	 * Sends the {@link #value()} message, hardening any runtime exception that
	 * may occur
	 * 
	 * @see SoftException#valueOrHarden(Thunk)
	 */
	@Override
	public A call() throws Exception {
		return SoftException.valueOrHarden(this);
	}

	/**
	 * Sends the {@link #call()} message, softening any exception that may occur.
	 * 
	 * @see SoftException#callOrSoften(Callable)
	 */
	@Override
	public A value() {
		return SoftException.callOrSoften(this);
	}

	@Override
	public boolean contains(A element) {
		return ObjectUtils.equals(value(), element);
	}

	/** Send {@link #value()} and discards its return value */
	@Override
	public final void run() {
		value();
	}

	/* Running time */

	/**
	 * Runs this {@link Provider} by sending {@link #run()}, and returns the time
	 * it took.
	 * 
	 * @return the duration in milliseconds of running this {@link Provider}
	 */
	public final long runTime() {
		long initialMillis = System.currentTimeMillis();
		run();
		long finalMillis = System.currentTimeMillis();
		return finalMillis - initialMillis;
	}

	/* Exception handling */

	/**
	 * Handles exceptions of type <code>exceptionClass</code> that may occur when
	 * sending {@link #call()}.
	 * 
	 * This method is just a shortcut for
	 * <code>Handle.throwing(this, exceptionClass)</code>
	 * 
	 * @see {@link Handle#throwing(Callable, Class)}
	 */
	public final <E extends Exception> A throwing(Class<E> exceptionClass) throws E {
		return Handle.throwing(this, exceptionClass);
	}

	/**
	 * Handles exceptions of type <code>exceptionClass1</code> and
	 * <code>exceptionClass2</code> that may occur when sending {@link #call()}.
	 * 
	 * This method is just a shortcut for
	 * <code>Handle.throwing(this, exceptionClass1, exceptionClass2)</code>
	 * 
	 * @see {@link Handle#throwing(Callable, Class,Class)}
	 */
	public final <E1 extends Exception, E2 extends Exception> A throwing(Class<E1> exceptionClass1,
		Class<E2> exceptionClass2) throws E1, E2 {
		return Handle.throwing(this, exceptionClass1, exceptionClass2);
	}

}
