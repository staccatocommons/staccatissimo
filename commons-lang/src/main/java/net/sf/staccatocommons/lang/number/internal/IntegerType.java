/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class IntegerType extends AbstractNumberType<Integer> {

	private static final long serialVersionUID = 6962808802837682697L;
	/**
	 * An instance
	 */
	public static final IntegerType TYPE = new IntegerType();

	public Integer add(Integer n0, Integer n1) {
		return n0 + n1;
	}

	public Integer multiply(Integer n0, Integer n1) {
		return n0 * n1;
	}

	public Integer divide(Integer n0, Integer n1) {
		return n0 / n1;
	}

	public Integer negate(Integer n) {
		return -n;
	}

	public Integer zero() {
		return 0;
	}

	public Integer one() {
		return 1;
	}

	public Integer increment(Integer n) {
		return n + 1;
	}

	public Integer decrement(Integer n) {
		return n - 1;
	}
}