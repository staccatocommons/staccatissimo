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
package net.sf.staccatocommons.lang.internal;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ToStringUnitTest {

	static class Foo {
		int x = 10;
		int y = 90;
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.internal.ToString#toString(java.lang.Object)}
	 */
	@Test
	public void testToStringObject() {
		assertEquals("ToStringUnitTest.Foo(10,90)", ToString.toString(new Foo()));
	}

}
