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
package net.sf.staccato.commons.lang.function;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class FunctionsUnitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.function.Functions#identity()}.
	 */
	@Test
	public void testIdentity() {
		Integer i = 5;
		assertSame(i, Functions.identity().apply(i));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.function.Functions#constant(java.lang.Object)}
	 * .
	 */
	@Test
	public void testConstant() {
		Integer i = 0;
		assertSame(i, Functions.constant(i).apply(1));
		assertSame(i, Functions.constant(i).apply(0));
		assertSame(i, Functions.constant(i).apply(2));
	}
}
