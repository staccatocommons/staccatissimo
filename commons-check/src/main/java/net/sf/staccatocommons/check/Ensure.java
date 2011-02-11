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
package net.sf.staccatocommons.check;

/**
 * {@link Ensure} is a utility class for performing precondition checks. This
 * class is a singleton, and offers static utility methods to perform
 * validations directly through it single instance
 * 
 * @author fbulgarelli
 * 
 */
public final class Ensure {

	private static final Check<IllegalArgumentException> check = new Check<IllegalArgumentException>() {

		protected IllegalArgumentException createException(Failure failure) {
			return new IllegalArgumentException(failure.createMessage());
		}
	};

	private Ensure() {}

	/**
	 * Shortcut to <code>Ensure.that().fail(varName,var,message,args)</code>
	 * 
	 * @see Check#fail(String, Object, String, Object...)
	 */
	public static <A> A fail(String varName, Object var, String message, Object... args) {
		return that().fail(varName, var, message, args);
	}

	/**
	 * Shortcut to
	 * <code>Ensure.that().is(varName,var,condition,message,args)</code>
	 * 
	 * @see Check#that(String, Object, boolean, String, Object...)
	 */
	public static Check<IllegalArgumentException> that(String varName, Object var, boolean condition,
		String message, Object... messageArgs) {
		return that().that(varName, var, condition, message, messageArgs);
	}

	/**
	 * Shortcut to <code>Ensure.that().is(condition,message,args)</code>
	 * 
	 * @see Check#that(boolean, String, Object...)
	 */
	public static Check<IllegalArgumentException> that(boolean condition, String message,
		Object... messageArgs) {
		return that().that(condition, message, messageArgs);
	}

	/**
	 * Shortcut to <code>Ensure.that().isNotNull(variableName, variable)</code>
	 * 
	 * @see Check#isNotNull(String, Object)
	 */
	public static Check<IllegalArgumentException> isNotNull(String variableName, Object variable) {
		return that().isNotNull(variableName, variable);
	}

	/**
	 * Shortcut to <code>Ensure.that().isNull(variableName, variable)</code>
	 * 
	 * @see Check#isNull(String, Object)
	 */
	public static Check<IllegalArgumentException> isNull(String variableName, Object variable) {
		return that().isNull(variableName, variable);
	}

	/**
	 * @return a singleton {@link Check} that throws
	 *         {@link IllegalArgumentException}s on check failure
	 */
	public static Check<IllegalArgumentException> that() {
		return check;
	}

}
