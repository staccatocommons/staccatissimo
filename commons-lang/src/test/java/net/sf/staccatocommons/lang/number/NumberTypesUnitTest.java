/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.number;

import static net.sf.staccatocommons.lang.number.NumberTypes.*;
import static net.sf.staccatocommons.lang.number.Numbers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class NumberTypesUnitTest {

	/**
	 * Test method for {@link NumberTypes#bigDecimal()}.
	 */
	@Test
	public void testBigDecimal() {
		assertEquals(e(1000, -1), bigDecimal().add(e(15, -1), e(985, -1)));
		assertEquals(d(700), bigDecimal().subtract(d(800), d(100)));
		assertEquals(d(1600), bigDecimal().multiply(d(800), d(2)));
		assertEquals(d(400), bigDecimal().divide(d(800), d(2)));
		assertEquals(d(1), bigDecimal().one());
		assertEquals(d(0), bigDecimal().zero());
	}

	/**
	 * Test method for {@link NumberTypes#bigInteger()}.
	 */
	@Test
	public void testBigInteger() {
		assertEquals(i(900), bigInteger().add(i(800), i(100)));
		assertEquals(i(700), bigInteger().subtract(i(800), i(100)));
		assertEquals(i(1600), bigInteger().multiply(i(800), i(2)));
		assertEquals(i(400), bigInteger().divide(i(800), i(2)));
		assertEquals(i(1), bigInteger().one());
		assertEquals(i(0), bigInteger().zero());
	}

	/**
	 * Test method for {@link NumberTypes#double_()}.
	 */
	@Test
	public void testDouble_() {
		assertEquals((Double) 192.0, double_().add(64.0, 128.0));
		assertEquals((Double) 700.0, double_().subtract(800.0, 100.0));
		assertEquals((Double) 1600.0, double_().multiply(800.0, 2.0));
		assertEquals((Double) 400.0, double_().divide(800.0, 2.0));
		assertEquals((Double) 1.0, double_().one());
		assertEquals((Double) 0.0, double_().zero());
	}

	/**
	 * Test method for {@link NumberTypes#float_()}.
	 */
	@Test
	public void testFloat_() {
		assertEquals((Float) 192.0f, float_().add(64.0f, 128.0f));
		assertEquals((Float) 700.0f, float_().subtract(800.0f, 100.0f));
		assertEquals((Float) 1600.0f, float_().multiply(800.0f, 2.0f));
		assertEquals((Float) 400.0f, float_().divide(800.0f, 2.0f));
		assertEquals((Float) 1.0f, float_().one());
		assertEquals((Float) 0.0f, float_().zero());
	}

	/**
	 * Test method for {@link NumberTypes#integer()}.
	 */
	@Test
	public void testInteger() {
		assertEquals(900, (int) integer().add(800, 100));
		assertEquals(700, (int) integer().subtract(800, 100));
		assertEquals(1600, (int) integer().multiply(800, 2));
		assertEquals(400, (int) integer().divide(800, 2));
		assertEquals(1, (int) integer().one());
		assertEquals(0, (int) integer().zero());
	}

	/**
	 * Test method for {@link NumberTypes#long_()}.
	 */
	@Test
	public void testLong_() {
		assertEquals(900, (long) long_().add(800L, 100L));
		assertEquals(700, (long) long_().subtract(800L, 100L));
		assertEquals(1600, (long) long_().multiply(800L, 2L));
		assertEquals(400, (long) long_().divide(800L, 2L));
		assertEquals(1, (long) long_().one());
		assertEquals(0, (long) long_().zero());
	}

	/**
	 * Test method for {@link NumberTypes#add(java.math.BigInteger)}.
	 */
	@Test
	public void testAddBigInteger() {
		assertEquals(bigInteger().add(i(10), i(50)), add(i(10)).apply(i(50)));
	}

	/**
	 * Test method for {@link NumberTypes#add(java.math.BigDecimal)}.
	 */
	@Test
	public void testAddBigDecimal() {
		assertEquals(bigDecimal().add(d(10), e(50, -6)), add(d(10)).apply(e(50, -6)));
	}

	/**
	 * Test method for {@link NumberTypes#add(java.lang.Integer)}.
	 */
	@Test
	public void testAddInteger() {
		assertEquals(integer().add(10, 50), add(10).apply(50));
	}

}
