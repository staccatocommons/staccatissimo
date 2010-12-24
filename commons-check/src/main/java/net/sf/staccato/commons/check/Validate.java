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
package net.sf.staccato.commons.check;

/**
 * {@link Validate} is a generic {@link Check} that on failure will throw a and
 * exception of a parameterized class.
 * 
 * TODO add code samples
 * 
 * @author flbulgarelli
 * @param <ExceptionType>
 *          the type of the exception this {@link Validate} will throw on
 *          failure
 * 
 */
public final class Validate<ExceptionType extends Throwable> extends Check<ExceptionType> {

	private final Class<ExceptionType> exceptionType;

	/**
	 * Creates a new {@link Validate}
	 * 
	 * @param exceptionType
	 *          the class of the exception to throw on failure
	 */
	private Validate(Class<ExceptionType> exceptionType) {
		this.exceptionType = exceptionType;
	}

	/**
	 * Created a new {@link Validate} that will throw an exception of the given
	 * class on failure
	 * 
	 * @param <E>
	 * @param exceptionType
	 *          the class of exception to throw on check failure
	 * @return a new {@link Validate}
	 */
	public static <E extends Throwable> Validate<E> throwing(Class<E> exceptionType) {
		return new Validate<E>(exceptionType);
	}

	protected ExceptionType createException(Failure failure) {
		try {
			return exceptionType.getConstructor(String.class).newInstance(failure.createMessage());
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

}
