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

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class LongType extends AbstractNumberType<Long> {

	private static final long serialVersionUID = -8990585054249024230L;
	/**
	 * An instance
	 */
	public static final NumberType<Long> TYPE = new LongType();

	public Long add(Long n0, Long n1) {
		return n0 + n1;
	}

	public Long multiply(Long n0, Long n1) {
		return n0 * n1;
	}

	public Long divide(Long n0, Long n1) {
		return n0 / n1;
	}

	public Long negate(Long n) {
		return -n;
	}

	public Long zero() {
		return 0L;
	}

	public Long one() {
		return 1L;
	}

	public Long increment(Long n) {
		return n + 1;
	}

	public Long decrement(Long n) {
		return n - 1;
	}
}