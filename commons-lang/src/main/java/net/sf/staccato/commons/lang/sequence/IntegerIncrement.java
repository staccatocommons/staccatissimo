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
package net.sf.staccato.commons.lang.sequence;

import net.sf.staccato.commons.lang.function.Function;
import net.sf.staccato.commons.lang.value.NamedTupleToStringStyle;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author flbulgarelli
 * 
 */
public class IntegerIncrement extends Function<Integer, Integer> {

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
		return ToStringBuilder.reflectionToString(
			this,
			NamedTupleToStringStyle.getInstance());
	}

}
