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
package net.sf.staccato.commons.lang.sequence.internal;

import java.io.Serializable;
import java.math.BigInteger;

import net.sf.staccato.commons.lang.function.Function;
import net.sf.staccato.commons.lang.internal.ToString;

/**
 * @author flbulgarelli
 * 
 */
public class BigIntegerIncrement extends Function<BigInteger, BigInteger> implements Serializable {

	private static final long serialVersionUID = 6665424536233331645L;

	private final BigInteger delta;

	/**
	 * Creates a new {@link BigIntegerIncrement}
	 * 
	 * @param delta
	 *          the increment. May be negative.
	 */
	public BigIntegerIncrement(BigInteger delta) {
		this.delta = delta;
	}

	@Override
	public BigInteger apply(BigInteger arg) {
		return arg.add(delta);
	}

	@Override
	public String toString() {
		return ToString.toString(this);
	}

}
