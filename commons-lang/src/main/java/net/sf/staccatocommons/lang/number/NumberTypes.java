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

import net.sf.staccatocommons.applicables.function.Function;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.defs.restriction.SideEffectFree;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.number.internal.BigDecimalType;
import net.sf.staccatocommons.lang.number.internal.BigIntegerType;
import net.sf.staccatocommons.lang.number.internal.DoubleType;
import net.sf.staccatocommons.lang.number.internal.FloatType;
import net.sf.staccatocommons.lang.number.internal.IntegerType;
import net.sf.staccatocommons.lang.number.internal.LongType;

/**
 * 
 * A hub for various {@link NumberType}s standard number classes
 * <p>
 * All the methods from number types exposed here are {@link SideEffectFree}
 * </p>
 * 
 * @author flbulgarelli
 * 
 */
public class NumberTypes {

	/**
	 * Creates a new {@link NumberTypes}
	 */
	private NumberTypes() {}

	/**
	 * Answers a {@link NumberType} that works with {@link BigDecimal}s
	 * <p>
	 * The returned type implements {@link NumberType#one()} and
	 * {@link NumberType#zero()} so that it returns {@link BigDecimal#ONE} and
	 * {@link BigDecimal#ZERO}, respectively
	 * </p>
	 * 
	 * @return a {@link BigDecimal} numberType
	 */
	@Constant
	@NonNull
	public static NumberType<BigDecimal> bigDecimal() {
		return BigDecimalType.TYPE;
	}

	/**
	 * Answers a {@link NumberType} that works with {@link BigInteger}s.
	 * 
	 * <p>
	 * The returned type implements {@link NumberType#one()} and
	 * {@link NumberType#zero()} so that it returns {@link BigInteger#ONE} and
	 * {@link BigInteger#ZERO}, respectively
	 * </p>
	 * 
	 * @return a {@link BigInteger} numberType
	 */
	@Constant
	@NonNull
	public static NumberType<BigInteger> bigInteger() {
		return BigIntegerType.TYPE;
	}

	/**
	 * Answers a {@link NumberType} that works with {@link Double}s.
	 * 
	 * @return a {@link Double} numberType
	 */
	@Constant
	@NonNull
	public static NumberType<Double> double_() {
		return DoubleType.TYPE;
	}

	/**
	 * Answers a {@link NumberType} that works with {@link Float}s.
	 * 
	 * @return a {@link Float} numberType
	 */
	@Constant
	@NonNull
	public static NumberType<Float> float_() {
		return FloatType.TYPE;
	}

	/**
	 * Answers a {@link NumberType} that works with {@link BigInteger}s.
	 * 
	 * @return a {@link BigInteger} numberType
	 */
	@Constant
	@NonNull
	public static NumberType<Integer> integer() {
		return IntegerType.TYPE;
	}

	/**
	 * Answers a {@link NumberType} that works with {@link Long}s.
	 * 
	 * @return a {@link Long} numberType
	 */
	@Constant
	@NonNull
	public static NumberType<Long> long_() {
		return LongType.TYPE;
	}

	/**
	 * Shortcut for <code>(Function&lt;BigInteger&gt;) bigInteger().add(n)</code>
	 */
	@NonNull
	public static Function<BigInteger, BigInteger> add(@NonNull BigInteger n) {
		return BigIntegerType.TYPE.add(n);
	}

	/**
	 * Shortcut for <code>(Function&lt;BigDecimal&gt;) bigDecimal().add(n)</code>
	 */
	@NonNull
	public static Function<BigDecimal, BigDecimal> add(@NonNull BigDecimal n) {
		return BigDecimalType.TYPE.add(n);
	}

	/**
	 * Shortcut for <code>(Function&lt;Integer&gt;) integer().add(n)</code>
	 */
	@NonNull
	public static Function<Integer, Integer> add(@NonNull Integer n) {
		return IntegerType.TYPE.add(n);
	}

}
