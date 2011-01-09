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

/**
 * @author flbulgarelli
 * 
 */
public class NumberTypes {

	public static final NumberType<BigDecimal> BIG_DECIMAL = new AbstractNumberType<BigDecimal>() {

		public BigDecimal add(BigDecimal n0, BigDecimal n1) {
			return n0.add(n1);
		}

		public BigDecimal multiply(BigDecimal n0, BigDecimal n1) {
			return n0.multiply(n1);
		}

		public BigDecimal divide(BigDecimal n0, BigDecimal n1) {
			return n0.divide(n1);
		}

		public BigDecimal negate(BigDecimal n) {
			return n.negate();
		}

		public BigDecimal zero() {
			return BigDecimal.ZERO;
		}

		public BigDecimal one() {
			return BigDecimal.ONE;
		}
	};

	public static NumberType<BigInteger> BIG_INTEGER = new AbstractNumberType<BigInteger>() {

		public BigInteger add(BigInteger n0, BigInteger n1) {
			return n0.add(n1);
		}

		public BigInteger multiply(BigInteger n0, BigInteger n1) {
			return n0.multiply(n1);
		}

		public BigInteger divide(BigInteger n0, BigInteger n1) {
			return n0.divide(n1);
		}

		public BigInteger negate(BigInteger n) {
			return n.negate();
		}

		public BigInteger zero() {
			return BigInteger.ZERO;
		}

		public BigInteger one() {
			return BigInteger.ONE;
		}
	};

	public static NumberType<Integer> INTEGER = new AbstractNumberType<Integer>() {

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
			return ++n;
		}

		public Integer decrement(Integer n) {
			return --n;
		}

	};

	public static NumberType<Long> LONG = new AbstractNumberType<Long>() {

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
			return ++n;
		}

		public Long decrement(Long n) {
			return --n;
		}

	};

	public static NumberType<Double> DOUBLE = new AbstractNumberType<Double>() {

		public Double add(Double n0, Double n1) {
			return n0 + n1;
		}

		public Double multiply(Double n0, Double n1) {
			return n0 * n1;
		}

		public Double divide(Double n0, Double n1) {
			return n0 / n1;
		}

		public Double negate(Double n) {
			return -n;
		}

		public Double zero() {
			return 0.0;
		}

		public Double one() {
			return 1.0;
		}

		public Double increment(Double n) {
			return ++n;
		}

		public Double decrement(Double n) {
			return --n;
		}

	};

	public static NumberType<Float> FLOAT = new AbstractNumberType<Float>() {

		public Float add(Float n0, Float n1) {
			return n0 + n1;
		}

		public Float multiply(Float n0, Float n1) {
			return n0 * n1;
		}

		public Float divide(Float n0, Float n1) {
			return n0 / n1;
		}

		public Float negate(Float n) {
			return -n;
		}

		public Float zero() {
			return 0f;
		}

		public Float one() {
			return 1f;
		}

		public Float increment(Float n) {
			return ++n;
		}

		public Float decrement(Float n) {
			return --n;
		}

	};

}
