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
package net.sf.staccatocommons.lang;

import java.util.concurrent.Callable;

/**
 * Utility class for handling exception in {@link Callable}s
 * 
 * @author flbulgarelli
 */
public class Handle {

	/**
	 * Creates a new {@link Handle}
	 */
	private Handle() {}

	/**
	 * Sends {@link Callable#call()} to the given <code>callable</code>, throwing
	 * any exception of type <code>exceptionClass</code> - including subtypes - ,
	 * and softening any exception of any other type.
	 * 
	 * 
	 * @param <ExceptionType>
	 * @param exceptionClass
	 *          the type of exception that will be thrown without being soften.
	 *          This class <strong>should</strong> correspond to a checked
	 *          exception type. Otherwise, this method would not provide any
	 *          benefit over sending <code>call()</code> directly.
	 * @throws RuntimeException
	 *           if any exception of type different from
	 *           <code>exceptionClass</code> is thrown while evaluating
	 *           {@link Callable#call()}.
	 * @return the result of evaluating {@link Callable#call()}
	 */
	public static <ExceptionType extends Exception, R> R throwing(Callable<R> provider,
		Class<ExceptionType> exceptionClass) throws ExceptionType {
		try {
			return provider.call();
		} catch (Exception e) {
			if (shouldCatch(exceptionClass, e)) {
				throw (ExceptionType) e;
			}
			throw SoftException.soften(e);
		}
	}

	/**
	 * Sends {@link Callable#call()} to the given <code>callable</code>, throwing
	 * any exception of type <code>exceptionClass1</code> or
	 * <code>exceptionClass2</code> - including subtypes - , and softening any
	 * exception of any other type.
	 * 
	 * @param <ExceptionType1>
	 * @param <ExceptionType2>
	 * @param exceptionClass1
	 *          one of the two types of exceptions that will be thrown without
	 *          being soften. This class <strong>should</strong> correspond to a
	 *          checked exception type. Otherwise, this method would not provide
	 *          any benefit over sending <code>call()</code> directly.
	 * @param exceptionClass2
	 *          the second type of exception that will be thrown without being
	 *          soften. Same restrictions of <code>exceptionClass1</code> apply
	 * @throws RuntimeException
	 *           if any exception of type different from
	 *           <code>exceptionClass1</code> or <code>exceptionClass2</code> is
	 *           thrown while evaluating {@link Callable#call()}.
	 * @return the result of evaluating {@link Callable#call()}
	 */
	public static <ExceptionType1 extends Exception, ExceptionType2 extends Exception, R> R throwing(
		Callable<R> provider, Class<ExceptionType1> exceptionClass1,
		Class<ExceptionType2> exceptionClass2) throws ExceptionType1, ExceptionType2 {
		try {
			return provider.call();
		} catch (Exception e) {
			if (shouldCatch(exceptionClass1, e)) {
				throw (ExceptionType1) e;
			}
			if (shouldCatch(exceptionClass2, e)) {
				throw (ExceptionType2) e;
			}
			throw SoftException.soften(e);
		}
	}

	private static <ExceptionType1> boolean shouldCatch(Class<ExceptionType1> exceptionClass,
		Exception e) {
		return exceptionClass.isAssignableFrom(e.getClass());
	}
}
