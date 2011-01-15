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
package net.sf.staccatocommons.check;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class AssertUnitTest {

	/**
	 * Test method for {@link Assert#fail(String, Object...)}.
	 */
	@Test
	public void testFail() {
		try {
			Assert.fail("Something %s happen", "really bad");
			fail();
		} catch (AssertionError e) {
			assertEquals("Something really bad happen", e.getMessage());
		}
	}

	/**
	 * Test method for {@link Assert#fail(String, Object, String)}.
	 */
	@Test
	public void testFailWithVar() {
		try {
			Assert.fail("foo", 5, "it should haven been 4");
			fail();
		} catch (AssertionError e) {
			assertEquals("foo=[5] : it should haven been 4", e.getMessage());
		}
	}

	/** Test for shortcuts */
	@Test
	public void testShortcuts() throws Exception {
		Assert.isNotNull("var", 5);
		Assert.isNull("var", null);
	}

}
