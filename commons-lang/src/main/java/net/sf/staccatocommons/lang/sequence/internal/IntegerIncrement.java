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
package net.sf.staccatocommons.lang.sequence.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.internal.ToString;

/**
 * @author flbulgarelli
 * 
 */
public class IntegerIncrement extends Function<Integer, Integer> implements Serializable {

	private static final long serialVersionUID = 7781352225318655326L;
	private final int delta;

	/**
	 * Creates a new {@link IntegerIncrement}
	 * 
	 * @param delta
	 *          the increment. May be negative.
	 */
	public IntegerIncrement(int delta) {
		this.delta = delta;
	}

	@Override
	public Integer apply(Integer arg) {
		return arg + delta;
	}

	@Override
	public String toString() {
		return ToString.toString(this);
	}

}
