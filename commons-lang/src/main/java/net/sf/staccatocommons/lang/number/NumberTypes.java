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
package net.sf.staccatocommons.lang.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.number.internal.BigDecimalType;
import net.sf.staccatocommons.lang.number.internal.BigIntegerType;
import net.sf.staccatocommons.lang.number.internal.DoubleType;
import net.sf.staccatocommons.lang.number.internal.FloatType;
import net.sf.staccatocommons.lang.number.internal.IntegerType;
import net.sf.staccatocommons.lang.number.internal.LongType;

/**
 * @author flbulgarelli
 * 
 */
public class NumberTypes {

	/**
	 * Creates a new {@link NumberTypes}
	 */
	private NumberTypes() {
	}

	/**
	 * @return the bigDecimal
	 */
	public static NumberType<BigDecimal> bigDecimal() {
		return BigDecimalType.TYPE;
	}

	/**
	 * @return the bigInteger
	 */
	public static NumberType<BigInteger> bigInteger() {
		return BigIntegerType.TYPE;
	}

	/**
	 * @return the double
	 */
	public static NumberType<Double> double_() {
		return DoubleType.TYPE;
	}

	/**
	 * @return the float
	 */
	public static NumberType<Float> float_() {
		return FloatType.TYPE;
	}

	/**
	 * @return the integer
	 */
	public static NumberType<Integer> integer() {
		return IntegerType.TYPE;
	}

	/**
	 * @return the long
	 */
	public static NumberType<Long> long_() {
		return LongType.TYPE;
	}

	public static Function<BigInteger, BigInteger> add(BigInteger n) {
		return BigIntegerType.TYPE.add(n);
	}

	public static Function<BigDecimal, BigDecimal> add(BigDecimal n) {
		return BigDecimalType.TYPE.add(n);
	}

	public static Function<Integer, Integer> add(Integer n) {
		return IntegerType.TYPE.add(n);
	}

}
