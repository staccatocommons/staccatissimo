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
	private SoftException(@NonNull Exception cause) {
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

	/**
	 * Converts the given {@link SoftException} into a checked {@link Exception},
	 * by returning its cause
	 * 
	 * @param e
	 *          the soft exception to harden
	 * @return the cause of the given soft exception
	 */
	@NonNull
	public static Exception harden(@NonNull SoftException e) {
		return (Exception) e.getCause();
	}

	/**
	 * Converts a given {@link RuntimeException} into a checked {@link Exception},
	 * if possible.
	 * 
	 * This method traverses the throwable causes chain, until a non
	 * {@link RuntimeException} is found. If such throwable exists and is an
	 * {@link Exception}, it returns it. Otherwise, returns the original
	 * <code>runtimeException</code>
	 * 
	 * @param runtimeException
	 * @return The first non {@link RuntimeException} in the causes chain, if
	 *         exists and is an {@link Exception}. The given
	 *         {@link RuntimeException} otherwise.
	 */
	@NonNull
	public static Exception harden(@NonNull RuntimeException runtimeException) {
		Throwable nonRuntime = findNonRuntime(runtimeException);
		if (nonRuntime instanceof Exception)
			return (Exception) nonRuntime;
		return runtimeException;
	}

	private static Throwable findNonRuntime(RuntimeException e) {
		if (e.getCause() instanceof RuntimeException)
			return findNonRuntime((RuntimeException) e.getCause());
		return e.getCause();
	}

	/**
	 * Tries return <code>callable.call()</code>. If an exception is thrown, it is
	 * softened and rethrown.
	 * 
	 * @param <T>
	 *          the callable return type
	 * @param callable
	 *          the callable to execute. Non null.
	 * @return the result of the callable, if {@link Callable#call()} succeeds
	 * @throws RuntimeException
	 *           if the call failed
	 * @see #soften(Exception)
	 */
	public static <T> T callOrSoften(@NonNull Callable<T> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			throw soften(e);
		}
	}

	/**
	 * Tries to return <code>thunk.value()</code>. If an exception is thrown, it
	 * is hardened and rethrown
	 * 
	 * @param <T>
	 *          the thunk return type
	 * @param thunk
	 * @return the value of the {@link Thunk}, if it succeeds
	 * @throws Exception
	 *           if {@link Thunk#value()} failed
	 * @see {@link Thunk}
	 * @see #harden(RuntimeException)
	 */
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
