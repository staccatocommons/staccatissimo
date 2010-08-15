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

/**
 * @author flbulgarelli
 * @param <T>
 */
public class LowerThan<T extends Comparable<T>> extends Predicate<T> {

	private final T value;

	public LowerThan(T value) {
		this.value = value;
	}

	public boolean eval(T argument) {
		return argument.compareTo(value) < 0;
	};

}
