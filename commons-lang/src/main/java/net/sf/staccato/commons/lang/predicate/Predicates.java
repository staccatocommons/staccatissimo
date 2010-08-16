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
package net.sf.staccato.commons.lang.predicate;

import java.util.Arrays;
import java.util.regex.Pattern;

import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.predicate.internal.All;
import net.sf.staccato.commons.lang.predicate.internal.Any;
import net.sf.staccato.commons.lang.predicate.internal.ContainsSubstringPredicate;
import net.sf.staccato.commons.lang.predicate.internal.Equals;
import net.sf.staccato.commons.lang.predicate.internal.EqualsIgnoreCase;
import net.sf.staccato.commons.lang.predicate.internal.False;
import net.sf.staccato.commons.lang.predicate.internal.GreaterThan;
import net.sf.staccato.commons.lang.predicate.internal.LowerThan;
import net.sf.staccato.commons.lang.predicate.internal.Matches;
import net.sf.staccato.commons.lang.predicate.internal.Not;
import net.sf.staccato.commons.lang.predicate.internal.NotNull;
import net.sf.staccato.commons.lang.predicate.internal.Same;
import net.sf.staccato.commons.lang.predicate.internal.True;

/**
 * @author flbulgarelli
 */
public class Predicates {

	/**
	 * @param <T>
	 * @return A {@link Predicate} that always returns <code>true</code>
	 */
	public static <T> Predicate<T> true_() {
		return True.getInstance();
	}

	/**
	 * @param <T>
	 * @return A {@link Predicate} that always returns <code>false</code>
	 */
	public static <T> Predicate<T> false_() {
		return False.getInstance();
	}

	/*
	 * Object predicates
	 */

	/**
	 * @param <T>
	 * @return A {@link Predicate} that tests if its argument is not null
	 */
	public static <T> Predicate<T> notNull() {
		return NotNull.getInstance();
	}

	/**
	 * 
	 * @param <T>
	 * @param value
	 * @return a {@link Predicate} that tests if its argument is equal to the
	 *         given value
	 */
	public static <T> Predicate<T> equal(T value) {
		return new Equals<T>(value);
	}

	/**
	 * 
	 * @param <T>
	 * @param value
	 * @return a {@link Predicate} that tests if its argument is the same that the
	 *         given value
	 */
	public static <T> Predicate<T> same(T value) {
		return new Same<T>(value);
	}

	/*
	 * String predicates
	 */

	public static Predicate<String> equalsIgnoreCase(String value) {
		return new EqualsIgnoreCase(value);
	}

	public static Predicate<String> matchesRegexp(String regexp) {
		return new Matches(regexp);
	}

	public static Predicate<String> matchesPattern(Pattern pattern) {
		return new Matches(pattern);
	}

	public static Predicate<String> contains(String substring) {
		return new ContainsSubstringPredicate(substring);
	}

	/*
	 * Logical predicates
	 */

	public static <T> Predicate<T> all(Evaluable<T>... predicates) {
		return all(Arrays.asList(predicates));
	}

	public static <T> Predicate<T> all(Iterable<Evaluable<T>> predicates) {
		return new All<T>(predicates);
	}

	public static <T> Predicate<T> any(Evaluable<T>... predicates) {
		return any(Arrays.asList(predicates));
	}

	public static <T> Predicate<T> any(Iterable<Evaluable<T>> predicates) {
		return new Any<T>(predicates);
	}

	public static <T> Predicate<T> not(Evaluable<T> predicate) {
		return new Not<T>(predicate);
	}

	/*
	 * Comparable predicates
	 */

	public static <T extends Comparable<T>> Predicate<T> lowerThan(T value) {
		return new LowerThan<T>(value);
	}

	public static <T extends Comparable<T>> Predicate<T> greaterThan(final T value) {
		return new GreaterThan<T>(value);
	}

}
