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
package net.sf.staccatocommons.lang;

import java.util.Comparator;

import net.sf.staccatocommons.check.annotation.ForceChecks;
import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * Class methods that implement comparisons for {@link Comparable}s, like
 * between(max, min) and in(collection)
 * 
 * @author flbulgarelli
 * 
 */
public class Compare {

	/**
	 * Tests that given three {@link Comparable}s <code>element</code>,
	 * <code>min</code> and <code>max</code>, is true that:
	 * 
	 * <pre>
	 * min &lt;= element &lt;= max
	 * </pre>
	 * 
	 * @param <T>
	 *          the type of comparable element
	 * @param element
	 *          non null
	 * @param min
	 *          non null
	 * @param max
	 *          non null
	 * @return if element is between min inclusive, and max, inclusive
	 */
	public static <T extends Comparable<T>> boolean between(@NonNull T element, @NonNull T min,
		@NonNull T max) {
		return element.compareTo(max) <= 0 && element.compareTo(min) >= 0;
	}

	/**
	 * Tests that given three {@link Comparable}s <code>element</code>,
	 * <code>min</code> and <code>max</code>, and a {@link Comparator}, using that
	 * comparator is true that:
	 * 
	 * <pre>
	 * min &lt;= element &lt;= max
	 * </pre>
	 * 
	 * @param <T>
	 *          the type of comparable element
	 * @param element
	 *          non null
	 * @param min
	 *          non null
	 * @param max
	 *          non null
	 * @param comparator
	 *          non null
	 * @return if element is between min inclusive, and max, inclusive, using the
	 *         given {@link Comparator} as ordering criteria
	 */
	public static <T> boolean between(@NonNull T element, @NonNull T min, @NonNull T max,
		@NonNull Comparator<T> comparator) {
		return comparator.compare(element, max) <= 0 && comparator.compare(element, min) >= 0;
	}

	/**
	 * Tests that given three <code>long</code>s <code>element</code>,
	 * <code>min</code> and <code>max</code>, is true that:
	 * 
	 * <pre>
	 * min &lt;= element &lt;= max
	 * </pre>
	 * 
	 * @param element
	 *          non null
	 * @param min
	 *          non null
	 * @param max
	 *          non null
	 * @return if element is between min inclusive, and max, inclusive
	 */
	public static boolean between(long element, long min, long max) {
		return element <= max && element >= min;
	}

	/**
	 * Tests that given three <code>int</code>s <code>element</code>,
	 * <code>min</code> and <code>max</code>, is true that:
	 * 
	 * <pre>
	 * min &lt;= element &lt;= max
	 * </pre>
	 * 
	 * @param element
	 *          non null
	 * @param min
	 *          non null
	 * @param max
	 *          non null
	 * @return if element is between min inclusive, and max, inclusive
	 */
	public static boolean between(int element, int min, int max) {
		return element <= max && element >= min;
	}

	/**
	 * Tests if a given array contains an element
	 * 
	 * @param element
	 * @param values
	 * @return if the array contains the given element
	 */
	public static boolean in(int element, @NonNull int[] values) {
		for (int value : values)
			if (element == value)
				return true;
		return false;
	}

	/**
	 * Tests if a given array contains an element
	 * 
	 * @param element
	 * @param values
	 * @return if the array contains the given element
	 */
	public static boolean in(long element, @NonNull long[] values) {
		for (long value : values)
			if (element == value)
				return true;
		return false;
	}

	/**
	 * Tests if a given array contains an element
	 * 
	 * @param <T>
	 * 
	 * @param element
	 * @param values
	 * @return if the array contains the given element, using equals comparison
	 */
	public static <T> boolean in(@NonNull T element, @NonNull T... values) {
		for (T value : values)
			if (element.equals(value))
				return true;
		return false;
	}

	/**
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @return c1 if it is lower than or equal to c2, c2 otherwise.
	 */
	@NonNull
	@ForceChecks
	public static <T extends Comparable<T>> T min(@NonNull T c1, @NonNull T c2) {
		return c1.compareTo(c2) < 1 ? c1 : c2;
	}

	/**
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @return c1 if it is greater than or equal to c2, c2 otherwise.
	 */
	@NonNull
	@ForceChecks
	public static <T extends Comparable<T>> T max(@NonNull T c1, @NonNull T c2) {
		return c1.compareTo(c2) >= 0 ? c1 : c2;
	}

	/**
	 * Answers the min element between <code>c1</code> and <code>c2</code>, using
	 * the given {@link Comparator}
	 * 
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @param comparator
	 * @return <code>comparator.compare(c1, c2) >= 0 ? c1 : c2</code>
	 */
	@NonNull
	@ForceChecks
	public static <T> T max(@NonNull T c1, @NonNull T c2, @NonNull Comparator<T> comparator) {
		return comparator.compare(c1, c2) >= 0 ? c1 : c2;
	}

	/**
	 * Answers the max element between <code>c1</code> and <code>c2</code>, using
	 * the given {@link Comparator}
	 * 
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @param comparator
	 * @return <code> comparator.compare(c1, c2) <= 0 ? c1 : c2</code>
	 */
	@NonNull
	@ForceChecks
	public static <T> T min(@NonNull T c1, @NonNull T c2, Comparator<T> comparator) {
		return comparator.compare(c1, c2) <= 0 ? c1 : c2;
	}

}
