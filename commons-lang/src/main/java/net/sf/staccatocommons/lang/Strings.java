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

import java.util.regex.Pattern;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.lang.predicate.internal.ContainsSubstringPredicate;
import net.sf.staccatocommons.lang.predicate.internal.EqualsIgnoreCase;
import net.sf.staccatocommons.lang.predicate.internal.Matches;

/**
 * @author flbulgarelli
 * 
 */
public class Strings {
	/**
	 * Returns a new {@link Predicate} that tests
	 * <code>argument.equalsIgnoreCase(value)</code>
	 * 
	 * @param value
	 * @return a new predicate
	 */
	@NonNull
	public static Predicate<String> equalsIgnoreCase(@NonNull String value) {
		return new EqualsIgnoreCase(value);
	}

	/**
	 * Returns a new {@link Predicate} that tests
	 * <code>argument.matches(value)</code>
	 * 
	 * @param regexp
	 * @return a new predicate
	 */
	@NonNull
	public static Predicate<String> matches(@NonNull String regexp) {
		return new Matches(regexp);
	}

	/**
	 * Returns a new {@link Predicate} that tests
	 * <code>pattern.matcher(value).matches()</code>
	 * 
	 * @param pattern
	 * @return a new predicate
	 */
	@NonNull
	public static Predicate<String> matches(@NonNull Pattern pattern) {
		return new Matches(pattern);
	}

	/**
	 * Returns a new {@link Predicate} that tests
	 * <code>argument.contains(substring)</code>
	 * 
	 * @param substring
	 *          the substring to test if it is contained
	 * @return a new predicate
	 */
	@NonNull
	public static Predicate<String> contains(@NonNull String substring) {
		return new ContainsSubstringPredicate(substring);
	}

	/**
	 * Returns a function that returns the result of sending
	 * {@link Object#toString()} to its argument
	 * 
	 * @param <A>
	 * @return a function that returns <code>arg.toString()</code>
	 */
	@NonNull
	@Constant
	/* FIXME */
	public static <A> Function<A, String> toString_() {
		return new AbstractFunction<A, String>() {
			public String apply(A arg) {
				return arg.toString();
			}
		};
	}
}
