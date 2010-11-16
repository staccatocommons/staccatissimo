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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.staccato.commons.lang.collection.EmptyAware;

/**
 * {@link Ensure} is a {@link Check} that throws
 * {@link IllegalArgumentException}s when validation fails. This class is a
 * singleton, and offers static utility methods to perform validations directly
 * through it single instance
 * 
 * @author fbulgarelli
 * 
 */
public final class Ensure extends Check<IllegalArgumentException> {

	private static final Ensure instance = new Ensure();

	private Ensure() {
	}

	@Override
	protected void checkFailedImpl(String variableName, Object variable,
		String message) {
		throw new IllegalArgumentException(message);
	}

	public static void fail(String variableName, Object variable, String message) {
		instance.checkFailedImpl(variableName, variable, message);
	}

	public static void empty(String variableName, Collection<?> variable) {
		instance.checkEmpty(variableName, variable);
	}

	public static void empty(String variableName, Iterable<?> variable) {
		instance.checkEmpty(variableName, variable);
	}

	public static void empty(String variableName, Map<?, ?> variable) {
		instance.checkEmpty(variableName, variable);
	}

	public static void isInstanceOf(String variableName, Object variable,
		Class<?> expectedClass) {
		instance.checkIsInstanceOf(variableName, variable, expectedClass);
	}

	public static void isTrue(String variableName, boolean variableValue,
		String message, Object... messageArgs) {
		instance.checkIsTrue(variableName, variableValue, message, messageArgs);
	}

	public static void isTrue(String variableName, boolean variableValue) {
		instance.checkIsTrue(variableName, variableValue);
	}

	public static void matches(String variableName, String value, Pattern pattern) {
		instance.checkMatches(variableName, value, pattern);
	}

	public static void matches(String variableName, String value, String regex) {
		instance.checkMatches(variableName, value, regex);
	}

	public static void nonNegative(String variableName, BigDecimal number) {
		instance.checkNonNegative(variableName, number);
	}

	public static void nonNegative(String variableName, BigInteger number) {
		instance.checkNonNegative(variableName, number);
	}

	public static void nonNegative(String variableName, double number) {
		instance.checkNonNegative(variableName, number);
	}

	public static void nonNegative(String variableName, float number) {
		instance.checkNonNegative(variableName, number);
	}

	public static void nonNegative(String variableName, int number) {
		instance.checkNonNegative(variableName, number);
	}

	public static void nonNegative(String variableName, long number) {
		instance.checkNonNegative(variableName, number);
	}

	public static void nonNull(String variableName, Object variable) {
		instance.checkNonNull(variableName, variable);
	}

	public static void notEmpty(String variableName, byte[] variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, CharSequence variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, Collection<?> variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, EmptyAware variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, double[] variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, float[] variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, int[] variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, Iterable<?> variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, long[] variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, Map<?, ?> variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void notEmpty(String variableName, Object[] variable) {
		instance.checkNotEmpty(variableName, variable);
	}

	public static void positive(String variableName, BigDecimal variable) {
		instance.checkPositive(variableName, variable);
	}

	public static void positive(String variableName, BigInteger variable) {
		instance.checkPositive(variableName, variable);
	}

	public static void positive(String variableName, double variable) {
		instance.checkPositive(variableName, variable);
	}

	public static void positive(String variableName, float variable) {
		instance.checkPositive(variableName, variable);
	}

	public static void positive(String variableName, int variable) {
		instance.checkPositive(variableName, variable);
	}

	public static void positive(String variableName, long variable) {
		instance.checkPositive(variableName, variable);
	}

	public static void size(String variableName, byte[] variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, CharSequence variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, Collection<?> variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, double[] variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, float[] variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, int[] variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, long[] variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static void size(String variableName, Object[] variable, int size) {
		instance.checkSize(variableName, variable, size);
	}

	public static <T extends Comparable<T>> void between(String variableName,
		T variable, T min, T max) {
		instance.checkBetween(variableName, variable, min, max);
	}

	public static <T extends Comparable<T>> void greatherThan(
		String variableName, T variable, T other) {
		instance.checkGreatherThan(variableName, variable, other);
	}

	public static <T extends Comparable<T>> void lowerThan(String variableName,
		T variable, T other) {
		instance.checkLowerThan(variableName, variable, other);
	}

	/**
	 * @return the non null, thread safe, singleton instance of Ensure
	 */
	public static Ensure getInstance() {
		return instance;
	}

	public static Ensure that() {
		return instance;
	}

}
