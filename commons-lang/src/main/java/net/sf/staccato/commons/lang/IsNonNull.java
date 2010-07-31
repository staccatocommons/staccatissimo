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
package net.sf.staccato.commons.lang;

/**
 * Class methods to operate with nulls
 * 
 * @author flbulgarelli
 * 
 */
public class IsNonNull {

	/**
	 * Returns the given value, if non null, or other, otherwise.
	 * 
	 * @param <T>
	 * @param value
	 *          nullable.
	 * @param other
	 *          nullable. //TODO has little sense to be be null
	 * @return <code> value != null ? value : other</code>
	 */
	public static <T> T orElse(T value, T other) {
		return value != null ? value : other;
	}

	/**
	 * Returns the given value, if non null, or the value provided by the given
	 * {@link Provider}, otherwise.
	 * 
	 * @param <T>
	 * @param value
	 *          nullable.
	 * @param provider
	 *          non null.
	 * @return <code>value != null ? value : provider.value()</code>
	 */
	public static <T> T orElse(T value, Provider<T> provider) {
		return value != null ? value : provider.value();
	}

	/**
	 * Returns <code>Option.some(value)</code>, if non null, or
	 * <code>Option.none()</code> otherwise
	 * 
	 * @param <T>
	 * @param value
	 *          nullable.
	 * @return <code>value != null ? Option.some(value) : Option.none()</code>
	 */
	public static <T> Option<T> orNone(T value) {
		return Option.nullToNone(value);
	}
}
