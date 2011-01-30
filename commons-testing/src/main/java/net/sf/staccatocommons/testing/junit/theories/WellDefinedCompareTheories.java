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
package net.sf.staccatocommons.testing.junit.theories;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.experimental.theories.Theory;

/**
 * @author flbulgarelli
 * 
 */
public class WellDefinedCompareTheories extends WellDefinedEqualsTheories {

	private boolean consistentWithEquals;

	/**
	 * Creates a new {@link WellDefinedCompareTheories}
	 * 
	 * @param consistentWithEquals
	 *          if this theories must assume that the comparables under test are
	 *          consistent with equals
	 */
	public WellDefinedCompareTheories(boolean consistentWithEquals) {
		this.consistentWithEquals = consistentWithEquals;
	}

	/**
	 * Test that sign of comparing x and y is the opposite of comparing y and x
	 * (rule nº1)
	 */
	@Theory
	public void testRule1(Comparable o1, Comparable o2) throws Exception {
		assertEquals(signum(o1.compareTo(o2)), -signum(o2.compareTo(o1)));
	}

	/**
	 * Test that comparison is transitive (rule nº2)
	 */
	@Theory
	public void testTransitive(Comparable o1, Comparable o2, Comparable o3) throws Exception {
		assumeTrue(o1.compareTo(o2) > 0);
		assumeTrue(o2.compareTo(o3) > 0);
		assertTrue(o1.compareTo(o3) > 0);
	}

	/**
	 * Test rule nº3
	 */
	@Theory
	public void testRule3(Comparable o1, Comparable o2, Comparable o3) throws Exception {
		assumeTrue(o1.compareTo(o2) == 0);
		assertEquals(signum(o1.compareTo(o3)), signum(o2.compareTo(o3)));
	}

	/**
	 * Theory that tests consistency of compareTo with equals.
	 * 
	 * @param o1
	 * @param o2
	 * @throws Exception
	 */
	@Theory
	public void testConsistentWithEquals(Comparable o1, Comparable o2) throws Exception {
		assumeTrue(consistentWithEquals);
		assertEquals(o1.equals(o2), o1.compareTo(o2) == 0);
	}

	private static int signum(int x) {
		if (x < 0)
			return -1;
		if (x > 0)
			return 1;
		return 0;
	}
}
