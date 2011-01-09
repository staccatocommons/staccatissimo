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

import org.junit.Ignore;
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
	}

	/**
	 * Test method for {@link NumberTypes#double_()}.
	 */
	@Ignore
	@Test
	public void testDouble_() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link NumberTypes#float_()}.
	 */
	@Ignore
	@Test
	public void testFloat_() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link NumberTypes#integer()}.
	 */
	@Ignore
	@Test
	public void testInteger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link NumberTypes#long_()}.
	 */
	@Ignore
	@Test
	public void testLong_() {
		fail("Not yet implemented");
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
