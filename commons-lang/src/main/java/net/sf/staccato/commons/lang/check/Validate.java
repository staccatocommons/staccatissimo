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
package net.sf.staccato.commons.lang.check;

import java.lang.reflect.Constructor;

import net.sf.staccato.commons.lang.SoftException;

/**
 * 
 * @author flbulgarelli
 * 
 */
public final class Validate<ExceptionType extends Throwable> extends Check<ExceptionType> {

	private final Constructor<ExceptionType> exceptionConstructor;

	/**
	 * Creates a new {@link Validate}
	 */
	public Validate(Class<ExceptionType> exceptionType) {
		try {
			this.exceptionConstructor = exceptionType.getConstructor(String.class);
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

	public static <E extends Throwable> Validate<E> throwing(Class<E> exceptionType) {
		return new Validate<E>(exceptionType);
	}

	protected ExceptionType createException(Failure failure) {
		try {
			return exceptionConstructor.newInstance(failure.createMessage());
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

}
