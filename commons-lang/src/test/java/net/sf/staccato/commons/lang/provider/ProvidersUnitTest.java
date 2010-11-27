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
package net.sf.staccato.commons.lang.provider;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ProvidersUnitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.provider.Providers#constant(java.lang.Object)}
	 * .
	 */
	@Test
	public void testConstant() {
		Object value = new Object();
		assertSame(value, Providers.constant(value).value());
	}

}
