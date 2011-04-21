/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.check;

import net.sf.staccatocommons.restrictions.effect.Transparent;

/**
 * {@link Validate} is a generic {@link Check} that on failure will throw a and
 * exception of a parameterized class.
 * 
 * Typical usage is the following:
 * 
 * <pre>
 * 	Validate
 * 		.throwing(MyExceptionType.class)
 *    .that(condition1, message1)
 *    .that(condition2, message2)
 *    ..etc..
 * </pre>
 * 
 * Since {@link Check}s are {@link Transparent}, and thus thread safe, it is
 * possible to cache {@link Validate} instances as class atributes and reuse
 * them across different validations. Example:
 * 
 * <pre>
 *   private static Validate&lt;IllegalStateException&gt; validate 
 *   	= Validate.throwing(IllegalStateException.class);
 *   
 *   public void foo() {
 *      validate.that(...);
 *   }
 *   
 *   public void bar(){
 *      validate.that(...);
 *   }
 * 
 * </pre>
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
