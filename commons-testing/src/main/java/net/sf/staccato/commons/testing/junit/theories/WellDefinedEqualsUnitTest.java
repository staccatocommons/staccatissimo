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
package net.sf.staccato.commons.testing.junit.theories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author flbulgarelli
 * 
 */
@RunWith(Theories.class)
public abstract class WellDefinedEqualsUnitTest {

	@Theory
	public void testEqualsReflexive(Object o1) throws Exception {
		assertTrue(o1.equals(o1));
	}

	@Theory
	public void testEqualsSymetric(Object o1, Object o2) throws Exception {
		assumeTrue(o1.equals(o2));
		assertTrue(o2.equals(o1));
	}

	@Theory
	public void testEqualsTransitive(Object o1, Object o2, Object o3) throws Exception {
		assumeTrue(o1.equals(o2));
		assumeTrue(o2.equals(o3));
		assertTrue(o1.equals(o3));
	}

	/**
	 * Test that equals is consistent along multiple invocation as long as fields
	 * invoveld in test do not change (rule nº4) This test is inherently difficult
	 * of generalize, an it just test that two invocation of equals have the same
	 * result
	 */
	@Theory
	public void testEqualsConsistent(Object o1, Object o2) throws Exception {
		assertEquals(o1.equals(o2), o1.equals(o2));
	}

	/**
	 * Tests that an object is never equal to null (rule nº5)
	 */
	@Theory
	public void testNeverEqualsToNull(Object o1) throws Exception {
		assertFalse(o1.equals(null));
	}

	@Theory
	public void testHasCodeConsistent(Object o1) throws Exception {
		assertEquals(o1.hashCode(), o1.hashCode());
	}

	/**
	 * Tests that if tow objects are equal, then their hashcodes are equal, too
	 * (rule nº2)
	 */
	@Theory
	public void testHashCodeConsistentWithEquals(Object o1, Object o2) throws Exception {
		assumeTrue(o1.equals(o2));
		assertEquals(o1.hashCode(), o2.hashCode());
	}

}
