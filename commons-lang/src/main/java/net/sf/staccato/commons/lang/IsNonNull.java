/*
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

	public static <T> T orElse(T value, T ifNull) {
		return value != null ? value : ifNull;
	}

	public static <T> T orElse(T value, Provider<T> ifNull) {
		return value != null ? value : ifNull.value();
	}

	public static <T> Option<T> orNone(T value) {
		return Option.nullToNone(value);
	}
}
