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
import net.sf.staccato.commons.lang.check.annotation.NonNull;
import net.sf.staccato.commons.lang.predicate.internal.All;
import net.sf.staccato.commons.lang.predicate.internal.And;
import net.sf.staccato.commons.lang.predicate.internal.Any;
import net.sf.staccato.commons.lang.predicate.internal.ContainsSubstringPredicate;
import net.sf.staccato.commons.lang.predicate.internal.Equals;
import net.sf.staccato.commons.lang.predicate.internal.EqualsIgnoreCase;
import net.sf.staccato.commons.lang.predicate.internal.EvaluablePredicate;
import net.sf.staccato.commons.lang.predicate.internal.False;
import net.sf.staccato.commons.lang.predicate.internal.GreaterThan;
import net.sf.staccato.commons.lang.predicate.internal.LowerThan;
import net.sf.staccato.commons.lang.predicate.internal.Matches;
import net.sf.staccato.commons.lang.predicate.internal.Not;
import net.sf.staccato.commons.lang.predicate.internal.NotNull;
import net.sf.staccato.commons.lang.predicate.internal.Or;
import net.sf.staccato.commons.lang.predicate.internal.Same;
import net.sf.staccato.commons.lang.predicate.internal.True;

/**
 * Factory methods for common predicates
 * 
 * @author flbulgarelli
 */
public class Predicates {

	/**
	 * @param <T>
	 * @return A {@link Predicate} that always returns <code>true</code>
	 */
	@NonNull
	public static <T> Predicate<T> true_() {
		return True.getInstance();
	}

	/**
	 * @param <T>
	 * @return A {@link Predicate} that always returns <code>false</code>
	 */
	@NonNull
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
	@NonNull
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
	@NonNull
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
	@NonNull
	public static <T> Predicate<T> same(T value) {
		return new Same<T>(value);
	}

	/*
	 * String predicates
	 */

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
	public static Predicate<String> matchesRegexp(@NonNull String regexp) {
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
	public static Predicate<String> matchesPattern(@NonNull Pattern pattern) {
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

	public static <T> Predicate<T> or(Evaluable<T> predicate, Evaluable<T> other) {
		return new Or<T>(predicate, other);
	}

	public static <T> Predicate<T> and(Evaluable<T> predicate, Evaluable<T> other) {
		return new And<T>(predicate, other);
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

	/*
	 * Conversion
	 */

	public static <T> Predicate<T> from(Evaluable<T> evaluable) {
		if (evaluable instanceof Predicate)
			return (Predicate) evaluable;
		return new EvaluablePredicate<T>(evaluable);
	}

}
