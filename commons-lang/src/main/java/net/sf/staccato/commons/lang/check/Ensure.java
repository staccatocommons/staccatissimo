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

	private Ensure() {
	}

	public static void fail(String varName, Object var, String message) {
		that().fail(varName, var, message);
	}

	public static void is(String varName, Object var, boolean condition, String message,
		Object... messageArgs) {
		that().is(varName, var, condition, message, messageArgs);
	}

	public static void is(boolean condition, String message, Object... messageArgs) {
		that().is(condition, message, messageArgs);
	}

	public static void isNotNull(String variableName, Object variable) {
		that().isNotNull(variableName, variable);
	}

	public static void isNull(String variableName, Object variable) {
		that().isNull(variableName, variable);
	}

	/**
	 * @return a singleton {@link Check} that throws
	 *         {@link IllegalArgumentException}s on check failure
	 */
	public static Check<IllegalArgumentException> that() {
		return check;
	}

	// public static Check<IllegalArgumentException> not() {
	// return check.not();
	// }

}
