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

	public static Predicate<String> equalsIgnoreCase(final String value) {
		return new Predicate<String>() {
			@Override
			public boolean eval(String arg0) {
				return value.equalsIgnoreCase(arg0);
			}
		};
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
	 * Comparable predicates
	 */

	public static <T extends Comparable<T>> Predicate<T> lowerThan(T value) {
		return new LowerThan<T>(value);
	}

	public static <T extends Comparable<T>> Predicate<T> greaterThan(final T value) {
		return new GreaterThan<T>(value);
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <T>
	 */
	public static final class Same<T> extends Predicate<T> {
		/**
		 * 
		 */
		private final T value;

		/**
		 * Creates a new {@link Same}
		 */
		public Same(T value) {
			this.value = value;
		}

		public boolean eval(T argument) {
			return value == argument;
		}
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <T>
	 */
	public static final class Equals<T> extends Predicate<T> {
		private final T value;

		/**
		 * Creates a new {@link Equals}
		 * 
		 * @param value
		 *          the value to test equality
		 */
		public Equals(T value) {
			this.value = value;
		}

		public boolean eval(T argument) {
			return value.equals(argument);
		}
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <T>
	 */
	public static final class NotNull<T> extends Predicate<T> {
		private static Predicate instance = new NotNull();

		public boolean eval(T argument) {
			return argument != null;
		}

		/**
		 * @return the instance
		 */
		public static Predicate getInstance() {
			return instance;
		}
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <T>
	 */
	public static final class False<T> extends Predicate<T> {
		private static Predicate instance = new False();

		public boolean eval(T argument) {
			return false;
		}

		/**
		 * @return the instance
		 */
		public static Predicate getInstance() {
			return instance;
		}
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <T>
	 */
	public static final class True<T> extends Predicate<T> {
		private static Predicate instance = new True();

		public boolean eval(T argument) {
			return true;
		}

		/**
		 * @return the instance
		 */
		public static Predicate getInstance() {
			return instance;
		}
	}
}
