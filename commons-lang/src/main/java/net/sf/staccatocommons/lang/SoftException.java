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
package net.sf.staccatocommons.lang;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;

import org.apache.commons.lang.UnhandledException;

/**
 * A {@link SoftException} is a {@link RuntimeException} that wraps another with
 * only solely purpose of making it unchecked.
 * 
 * @author flbulgarelli
 */
public class SoftException extends UnhandledException {

	private static final long serialVersionUID = 4364656280386270636L;

	/**
	 * Creates a new {@link SoftException} that wraps the given one.
	 * 
	 * Please note that the preferred way of wrapping the cause exception is using
	 * the {@link #soften(Exception)} class method, which will not wrap
	 * {@link RuntimeException} unnecessarily, when catching generic exceptions.
	 * 
	 * @param cause
	 *          the wrapped exception.
	 */
	public SoftException(@NonNull Exception cause) {
		super(cause);
	}

	/**
	 * Converts an exception into a {@link RuntimeException}, either by casting
	 * it, if possible, or wrapping it into a {@link SoftException}
	 * 
	 * @param exception
	 *          the target exception to soften.
	 * @return The given <code>exception</code>, casted to
	 *         {@link RuntimeException}, if possible, or a new
	 *         {@link SoftException} that wrapps it, otherwise.
	 */
	@NonNull
	public static RuntimeException soften(@NonNull Exception exception) {
		if (exception instanceof RuntimeException)
			return (RuntimeException) exception;
		return new SoftException(exception);
	}

	@NonNull
	public static Exception harden(@NonNull SoftException e) {
		return (Exception) e.getCause();
	}

	@NonNull
	public static Exception harden(@NonNull RuntimeException e) {
		if (e.getCause() == null)
			return e;
		if (e.getCause() instanceof Exception)
			return (Exception) e.getCause();
		if (e.getCause() instanceof RuntimeException)
			return harden((RuntimeException) e.getCause());
		return e;
	}

	/**
	 * Tries to call the given {@link Callable} and return its result, and
	 * rethrows a soften exception if {@link Callable#call()} threw an
	 * {@link Exception}
	 * 
	 * @param <T>
	 *          the callable return type
	 * @param callable
	 *          the callable to execute. Non null.
	 * @return the result of the callable, if {@link Callable#call()} succeed
	 * @throws RuntimeException
	 *           if the call failed
	 */
	public static <T> T callOrSoften(@NonNull Callable<T> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			throw soften(e);
		}
	}

	public static <T> T valueOrHarden(@NonNull Thunk<T> thunk) throws Exception {
		try {
			return thunk.value();
		} catch (SoftException e) {
			throw harden(e);
		} catch (RuntimeException e) {
			throw harden(e);
		}
	}

}
