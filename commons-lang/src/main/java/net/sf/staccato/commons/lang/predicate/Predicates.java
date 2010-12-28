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
import java.util.Collection;
import java.util.regex.Pattern;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.defs.Evaluable;
import net.sf.staccato.commons.lang.predicate.internal.All;
import net.sf.staccato.commons.lang.predicate.internal.Any;
import net.sf.staccato.commons.lang.predicate.internal.ContainsSubstringPredicate;
import net.sf.staccato.commons.lang.predicate.internal.Equals;
import net.sf.staccato.commons.lang.predicate.internal.EqualsIgnoreCase;
import net.sf.staccato.commons.lang.predicate.internal.EvaluablePredicate;
import net.sf.staccato.commons.lang.predicate.internal.False;
import net.sf.staccato.commons.lang.predicate.internal.GreaterThan;
import net.sf.staccato.commons.lang.predicate.internal.InPredicate;
import net.sf.staccato.commons.lang.predicate.internal.LessThan;
import net.sf.staccato.commons.lang.predicate.internal.Matches;
import net.sf.staccato.commons.lang.predicate.internal.NullPredicates;
import net.sf.staccato.commons.lang.predicate.internal.Same;
import net.sf.staccato.commons.lang.predicate.internal.True;

/**
 * Factory methods for common predicates
 * 
 * @author flbulgarelli
 */
public class Predicates {

	private Predicates() {
	}

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
	 * Returns a preficate that tests if its argument is not null
	 * 
	 * @param <T>
	 * @return A singleton {@link Predicate}
	 */
	@NonNull
	public static <T> Predicate<T> notNull() {
		return NullPredicates.notNull();
	}

	/**
	 * Returns a predicate that tests if its argument is null
	 * 
	 * @param <T>
	 * @return A singleton {@link Predicate}
	 */
	@NonNull
	public static <T> Predicate<T> null_() {
		return NullPredicates.null_();
	}

	/**
	 * Returns a predicate that tests if its argument is equal to the given value:
	 * <code>argument.equals(value)</code>
	 * 
	 * @param <T>
	 * @param value
	 * @return a new {@link Predicate}
	 */
	@NonNull
	public static <T> Predicate<T> equal(T value) {
		return new Equals<T>(value);
	}

	/**
	 * Returns a predicate that tests if its argument is the same that the given
	 * value
	 * 
	 * @param <T>
	 * @param value
	 * @return a new {@link Predicate}
	 */
	@NonNull
	public static <T> Predicate<T> same(T value) {
		return new Same<T>(value);
	}

	/**
	 * Returns a predicate that tests if its argument is equal to any of the given
	 * values
	 * 
	 * @param <T>
	 * @param values
	 * @return a new {@link Predicate}
	 */
	@NonNull
	public static <T> Predicate<T> in(@NonNull T... values) {
		return new InPredicate<T>(values);
	}

	/**
	 * Returns a predicate that tests if its argument is equal to any of the
	 * values in the given collection
	 * 
	 * @param <T>
	 * @param values
	 * @return a new {@link Predicate}
	 */
	@NonNull
	public static <T> Predicate<T> in(@NonNull Collection<T> values) {
		return new InPredicate<T>(values);
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

	/**
	 * Returns a predicate that evaluates to true if and only if all the given
	 * predicates evaluate true
	 * 
	 * @param <T>
	 * @param predicates
	 * @return the all predicate
	 */
	@NonNull
	public static <T> Predicate<T> all(@NonNull Evaluable<T>... predicates) {
		return all(Arrays.asList(predicates));
	}

	/**
	 * Returns a predicate that evaluates to true if and only if all the given
	 * predicates evaluate true
	 * 
	 * @param <T>
	 * @param predicates
	 * @return the all predicate
	 */
	@NonNull
	public static <T> Predicate<T> all(@NonNull Iterable<Evaluable<T>> predicates) {
		return new All<T>(predicates);
	}

	/**
	 * Returns a predicate that evaluates to false if and only if all the given
	 * predicates evaluate false
	 * 
	 * @param <T>
	 * @param predicates
	 * @return the any predicate
	 */
	@NonNull
	public static <T> Predicate<T> any(@NonNull Evaluable<T>... predicates) {
		return any(Arrays.asList(predicates));
	}

	/**
	 * Returns a predicate that evaluates to false if and only if all the given
	 * predicates evaluate false
	 * 
	 * @param <T>
	 * @param predicates
	 * @return the any predicate
	 */
	@NonNull
	public static <T> Predicate<T> any(@NonNull Iterable<Evaluable<T>> predicates) {
		return new Any<T>(predicates);
	}

	/*
	 * Comparable predicates
	 */

	/**
	 * Returns a predicate that evaluates if its argument is less than the given
	 * value.
	 * 
	 * More formally, this method returns a new predicate that evaluates
	 * comparable argument with the statement
	 * <code>argument.compareTo(value) < 0</code>
	 * 
	 * @param <T>
	 * @param value
	 * @return a new predicate
	 */
	@NonNull
	public static <T extends Comparable<T>> Predicate<T> lessThan(@NonNull T value) {
		return new LessThan<T>(value);
	}

	/**
	 * Returns a predicate that evaluates if its argument is lower than or equal
	 * to the given value.
	 * 
	 * More formally, this method returns a new predicate that evaluates
	 * comparable argument with the statement
	 * <code>argument.compareTo(value) <= 0</code>
	 * 
	 * @param <T>
	 * @param value
	 * @return a new predicate
	 */
	@NonNull
	public static <T extends Comparable<T>> Predicate<T> lessThanOrEqualTo(@NonNull T value) {
		return greaterThan(value).not();
	}

	/**
	 * Returns a predicate that evaluates if its argument is greater than the
	 * given value.
	 * 
	 * More formally, this method returns a new predicate that evaluates
	 * comparable argument with the statement
	 * <code>argument.compareTo(value) > 0</code>
	 * 
	 * @param <T>
	 * @param value
	 * @return a new predicate
	 */
	@NonNull
	public static <T extends Comparable<T>> Predicate<T> greaterThan(@NonNull T value) {
		return new GreaterThan<T>(value);
	}

	/**
	 * Returns a predicate that evaluates if its argument is greater than or equal
	 * to the given value.
	 * 
	 * More formally, this method returns a new predicate that evaluates
	 * comparable argument with the statement
	 * <code>argument.compareTo(value) >= 0</code>
	 * 
	 * @param <T>
	 * @param value
	 * @return a new predicate
	 */
	@NonNull
	public static <T extends Comparable<T>> Predicate<T> greaterThanOrEqualTo(@NonNull T value) {
		return lessThan(value).not();
	}

	/*
	 * Predicate - Evaluable bridge
	 */

	/**
	 * Converts the given {@link Evaluable} into a {@link Predicate}. If it is
	 * already a Predicate, returns it.
	 * 
	 * @param evaluable
	 * @param <T>
	 * @return a {@link Predicate} view of the given evaluable, or the evaluable,
	 *         it is a {@link Predicate} already
	 */
	@NonNull
	public static <T> Predicate<T> from(@NonNull Evaluable<T> evaluable) {
		if (evaluable instanceof Predicate)
			return (Predicate) evaluable;
		return new EvaluablePredicate<T>(evaluable);
	}

}
