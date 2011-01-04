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
package net.sf.staccatocommons.lang.predicate.internal;

/**
 * @author flbulgarelli
 * @param <T>
 */
public class LessThan<T extends Comparable<T>> extends NonAnnonymousPredicate<T> {

	private static final long serialVersionUID = 459305478306868635L;
	private final T value;

	/**
	 * Creates a new {@link LessThan}
	 * 
	 * @param value
	 */
	public LessThan(T value) {
		this.value = value;
	}

	public boolean eval(T argument) {
		return argument.compareTo(value) < 0;
	}

}
