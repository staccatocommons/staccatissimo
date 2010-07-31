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

import java.util.regex.Pattern;

import org.apache.commons.lang.NotImplementedException;

/**
 * 
 * This class extends {@link org.apache.commons.collections15.PredicateUtils}
 * functionality, adding some extra predicates, and shorter synonyms for them
 * 
 * @author flbulgarelli
 * 
 */
public class Predicates {

	public static <T> Predicate<T> true_() {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return true;
			}
		};
	}

	public static <T> Predicate<T> false_() {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return false;
			}
		};
	}

	/*
	 * Object predicates
	 */

	public static <T> Predicate<T> notNull() {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return argument != null;
			}
		};
	}

	public static <T> Predicate<T> equal(final T value) {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return value.equals(argument);
			}
		};
	}

	public static <T> Predicate<T> same(final T value) {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return value == argument;
			}
		};
	}

	/*
	 * String predicates
	 */

	public static Predicate<String> equalIgnoreCase(final String value) {
		return new Predicate<String>() {
			@Override
			public boolean eval(String arg0) {
				return value.equalsIgnoreCase(arg0);
			}
		};
	}

	public static Predicate<String> matchesRegexp(String regexp) {
		return new PatternPredicate(regexp);
	}

	public static Predicate<String> matchesPattern(Pattern pattern) {
		return new PatternPredicate(pattern);
	}

	public static Predicate<String> contains(String substring) {
		return new ContainsSubstringPredicate(substring);
	}

	/*
	 * Comparable predicates
	 */

	public static <T extends Comparable<T>> Predicate<T> lowerThan(T value) {
		throw new NotImplementedException();
	}

	public static <T extends Comparable<T>> Predicate<T> greaterThan(final T value) {
		return new GreaterThanPredicate<T>(value);
	}

}
