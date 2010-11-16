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

import static net.sf.staccato.commons.lang.Compare.between;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.staccato.commons.lang.collection.ContainsAware;
import net.sf.staccato.commons.lang.collection.EmptyAware;
import net.sf.staccato.commons.lang.collection.SizeAware;
import net.sf.staccato.commons.lang.format.Var;

import org.apache.commons.lang.Validate;

/**
 * 
 * A {@link Check} is an object designed to validate conditions. It is heavily
 * inspired on {@link Validate}, but is designed to be more flexible and
 * easy-to-use, so, it should be seen as higher lever replacement.
 * 
 * The four main differences are the following
 * <ul>
 * <li>
 * This class is abstract and offers instance methods, and the exact behavior
 * when checks fail depends on subclasses. Two concrete implementation of
 * {@link Check}, with static methods like the original {@link Validate} class,
 * are {@link Ensure} and {@link Assert}</li>
 * <li> {@link Check} offers a rich interface, instead of the thin interface
 * offered by {@link Validate}. Not only method for checking nulls or boolean
 * values, but also, {@link Collection}s sizes, regular expresion and so on are
 * exposed</li>
 * <li>Messages are partially automatically generated, so most user code do not
 * need to provide string that tell what it is checking</li>
 * <li>Exception type thrown by implementors is generic, so they can throw
 * either checked or unchecked exceptions at no cost for client code</li>
 * </ul>
 * 
 * All check methods take a paremeterName as first argument. This is a String
 * identifier of the variable being check, that allows to find it in context.
 * For example, if what is being check is a method variable or local variable,
 * its variable name should be used, if is its an an attribute or property is
 * being used, its property name should be used.
 * 
 * 
 * @author flbulgarelli
 * 
 * @see Validate
 * @see Ensure
 * @see Assert
 * 
 * @param <ExceptionType>
 *          The kind of exception that this check will thrown on validation
 *          failures
 * 
 */
public abstract class Check<ExceptionType extends Throwable> {

	/**
	 * Throws an exception of type ExceptionType
	 * 
	 * @param variableName
	 *          An identifier of the variable being check. non null
	 * @param variable
	 *          non null the object being check
	 * @param message
	 *          non null check failure message.
	 */
	protected abstract void checkFailedImpl(String variableName, Object variable,
		String message) throws ExceptionType;

	private void checkFailed(String variableName, Object variable,
		String message, Object... args) throws ExceptionType {
		checkFailedImpl(variableName, variable, String.format(message, args));
	}

	public void checkIsTrue(String variableName, boolean variableValue,
		String message, Object... messageArgs) throws ExceptionType {
		if (!variableValue) {
			checkFailed(variableName, variableName, //
				message,
				messageArgs);
		}
	}

	/**
	 * Checks the variable is true
	 * 
	 * @param variableName
	 * @param variableValue
	 * @throws ExceptionType
	 */
	public void checkIsTrue(String variableName, boolean variableValue)
		throws ExceptionType {
		if (!variableValue) {
			checkFailed(variableName, variableName, //
				"%s should be true", //
				Var.format(variableName, variableValue));
		}
	}

	/**
	 * Checks that the string variable matches the given regular expression
	 * 
	 * @param variableName
	 * @param value
	 * @param regex
	 * @throws ExceptionType
	 */
	public void checkMatches(String variableName, String value, String regex)
		throws ExceptionType {
		checkNonNull(variableName, value);
		checkNonNull("regex", regex);
		if (!value.matches(regex)) {
			checkFailed(variableName, value,//
				"%s must match the %s", //
				Var.format(variableName, value),
				Var.format("regex", regex));
		}
	}

	/**
	 * Checks that the string variable matches the given regular expression
	 * 
	 * @param variableName
	 * @param value
	 * @param regex
	 * @throws ExceptionType
	 */
	public void checkMatches(String variableName, String value, Pattern pattern)
		throws ExceptionType {
		checkNonNull(variableName, value);
		checkNonNull("pattern", pattern);
		if (!pattern.matcher(value).matches()) {
			checkFailed(variableName, value, //
				"%s must match the %s", //
				Var.format(variableName, value),
				Var.format("regex", pattern.pattern()));
		}
	}

	/**
	 * Checks the variable is &gt;= 0
	 * 
	 * @param variableName
	 * @param number
	 * @throws ExceptionType
	 */
	public void checkNonNegative(String variableName, long number)
		throws ExceptionType {
		if (number < 0)
			checkNonNegativeFailed(variableName, number);
	}

	/**
	 * Checks the variable is &gt;= 0
	 * 
	 * @param variableName
	 * @param number
	 * @throws ExceptionType
	 */
	public void checkNonNegative(String variableName, int number)
		throws ExceptionType {
		if (number < 0)
			checkNonNegativeFailed(variableName, number);
	}

	/**
	 * Checks the variable is &gt;= 0
	 * 
	 * @param variableName
	 * @param number
	 * @throws ExceptionType
	 */
	public void checkNonNegative(String variableName, double number)
		throws ExceptionType {
		if (number < 0)
			checkNonNegativeFailed(variableName, number);
	}

	/**
	 * Checks the variable is &gt;= 0
	 * 
	 * @param variableName
	 * @param number
	 * @throws ExceptionType
	 */
	public void checkNonNegative(String variableName, float number)
		throws ExceptionType {
		if (number < 0)
			checkNonNegativeFailed(variableName, number);
	}

	/**
	 * Checks the variable is &gt;= 0
	 * 
	 * @param variableName
	 * @param number
	 *          non null.
	 * @throws ExceptionType
	 */
	public void checkNonNegative(String variableName, BigDecimal number)
		throws ExceptionType {
		checkNonNull(variableName, number);
		if (number.compareTo(BigDecimal.ZERO) < 0)
			checkNonNegativeFailed(variableName, number);
	}

	/**
	 * Checks the variable is &gt;= 0
	 * 
	 * @param variableName
	 * @param number
	 *          non null.
	 * @throws ExceptionType
	 */
	public void checkNonNegative(String variableName, BigInteger number)
		throws ExceptionType {
		checkNonNull(variableName, number);
		if (number.compareTo(BigInteger.ZERO) < 0)
			checkNonNegativeFailed(variableName, number);
	}

	private final void checkNonNegativeFailed(String variableName, Object number)
		throws ExceptionType {
		checkFailed(variableName, number,//
			"%s must be non negative", //
			Var.format(variableName, number));
	}

	public void checkNotEmpty(String variableName, EmptyAware variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.isEmpty())
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, Collection<?> variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.isEmpty())
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, Iterable<?> variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.iterator().hasNext())
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, Map<?, ?> variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.isEmpty())
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, CharSequence variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length() == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, Object[] variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, int[] variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, long[] variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, byte[] variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, double[] variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	public void checkNotEmpty(String variableName, float[] variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.length == 0)
			checkNotEmptyFailed(variableName, variable);
	}

	private void checkNotEmptyFailed(String variableName, Object variable)
		throws ExceptionType {
		checkFailed(variableName, variable, //
			"%s must not be empty", //
			Var.format(variableName, variable));
	}

	public void checkEmpty(String variableName, Collection<?> variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (!variable.isEmpty()) {
			checkEmptyFailed(variableName, variable);
		}
	}

	public void checkEmpty(String variableName, Iterable<?> variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (!variable.iterator().hasNext())
			checkEmptyFailed(variableName, variable);
	}

	public void checkEmpty(String variableName, Map<?, ?> variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (!variable.isEmpty())
			checkEmptyFailed(variableName, variable);
	}

	public void checkEmpty(String variableName, EmptyAware variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (!variable.isEmpty())
			checkEmptyFailed(variableName, variable);
	}

	private final void checkEmptyFailed(String variableName, Object variable)
		throws ExceptionType {
		checkFailed(variableName, variable, //
			"%s must be empty", //
			Var.format(variableName, variable));
	}

	/**
	 * Checks the variable object is not null, and raises an
	 * {@link IllegalArgumentException} if fails.
	 * 
	 * @param variableName
	 * @param variable
	 * @throws ExceptionType
	 */
	public void checkNonNull(String variableName, Object variable)
		throws ExceptionType {
		if (variable == null) {
			checkFailed(variableName, null, //
				"%s must not be null",
				variableName);
		}
	}

	/**
	 * Checks the variable is &lt;= 0
	 * 
	 * @param variableName
	 * @param variable
	 * @throws ExceptionType
	 */
	public void checkPositive(String variableName, long variable)
		throws ExceptionType {
		if (variable <= 0)
			checkSizeFailed(variableName, variable);
	}

	/**
	 * Checks the variable is &lt;= 0
	 * 
	 * @param variableName
	 * @param variable
	 * @throws ExceptionType
	 */
	public void checkPositive(String variableName, int variable)
		throws ExceptionType {
		if (variable <= 0)
			checkSizeFailed(variableName, variable);
	}

	/**
	 * Checks the variable is &lt;= 0
	 * 
	 * @param variableName
	 * @param variable
	 * @throws ExceptionType
	 */
	public void checkPositive(String variableName, double variable)
		throws ExceptionType {
		if (variable <= 0)
			checkSizeFailed(variableName, variable);
	}

	/**
	 * Checks the variable is &lt;= 0
	 * 
	 * @param variableName
	 * @param variable
	 * @throws ExceptionType
	 */
	public void checkPositive(String variableName, float variable)
		throws ExceptionType {
		if (variable <= 0)
			checkSizeFailed(variableName, variable);
	}

	/**
	 * Checks the variable is &lt;= 0
	 * 
	 * @param variableName
	 * @param variable
	 *          non null
	 * @throws ExceptionType
	 */
	public void checkPositive(String variableName, BigDecimal variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.compareTo(BigDecimal.ZERO) <= 0)
			checkSizeFailed(variableName, variable);
	}

	/**
	 * Checks the variable is &lt;= 0
	 * 
	 * @param variableName
	 * @param variable
	 *          non null.
	 * @throws ExceptionType
	 */
	public void checkPositive(String variableName, BigInteger variable)
		throws ExceptionType {
		checkNonNull(variableName, variable);
		if (variable.compareTo(BigInteger.ZERO) <= 0)
			checkSizeFailed(variableName, variable);
	}

	private void checkSizeFailed(String variableName, Object variable)
		throws ExceptionType {
		checkFailed(variableName, variable, //
			"%s must be positive",
			Var.format(variableName, variable));
	}

	/**
	 * Checks the variable is instance of the given class
	 * 
	 * @param variableName
	 * @param variable
	 *          non null
	 * @param expectedClass
	 *          non null.
	 * @throws ExceptionType
	 */
	public void checkIsInstanceOf(String variableName, Object variable,
		Class<?> expectedClass) throws ExceptionType {
		checkNonNull(variableName, variable);
		if (!expectedClass.isInstance(variable)) {
			checkFailed(variableName, variable, //
				"%s must be instance of class %s, but it was of class %d",
				Var.format(variableName, variable),
				expectedClass,
				variable.getClass());
		}
	}

	public void checkSize(String variableName, Collection<?> variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.size());
	}

	public void checkSize(String variableName, CharSequence variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length());
	}

	public void checkSize(String variableName, Object[] variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length);
	}

	public void checkSize(String variableName, int[] variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length);
	}

	public void checkSize(String variableName, long[] variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length);
	}

	public void checkSize(String variableName, byte[] variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length);
	}

	public void checkSize(String variableName, double[] variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length);
	}

	public void checkSize(String variableName, float[] variable, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, variable, size, variable.length);
	}

	public void checkSize(String variableName, SizeAware value, int size)
		throws ExceptionType {
		checkSizeIntenal(variableName, value, size, value.size());
	}

	private final void checkSizeIntenal(String variableName, Object variable,
		int expectedSize, int actualSize) throws ExceptionType {
		if (actualSize != expectedSize)
			checkFailed(variableName, variable, //
				"%s must be of size %d, but it was %s", //
				Var.format(variableName, variable),
				expectedSize,
				actualSize);
	}

	public <T extends Comparable<T>> void checkBetween(String variableName,
		T variable, T min, T max) throws ExceptionType {
		if (!between(variable, min, max))
			checkFailed(variableName, variable,//
				"%s must be between %s and %s",
				Var.format(variableName, variable),
				min,
				max);
	}

	public <T> void checkContains(String variableName, ContainsAware<T> variable,
		T element) throws ExceptionType {
		if (!variable.contains(element))
			checkFailed(variableName, variable,//
				"%s must contain %s",
				Var.format(variableName, variable),
				element);
	}

	public <T extends Comparable<T>> void checkGreatherThan(String variableName,
		T variable, T other) throws ExceptionType {
		if (!(variable.compareTo(other) > 0))
			checkFailed(variableName, variable,//
				"%s must be greather than %s",
				Var.format(variableName, variable),
				other);
	}

	public <T extends Comparable<T>> void checkLowerThan(String variableName,
		T variable, T other) throws ExceptionType {
		if (!(variable.compareTo(other) < 0))
			checkFailed(variableName, variable,//
				"%s must be lower than %s",
				Var.format(variableName, variable),
				other);
	}

	// TODO something in something
	// TODO something contains something

}
