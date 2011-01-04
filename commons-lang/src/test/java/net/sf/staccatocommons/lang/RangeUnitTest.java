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
package net.sf.staccatocommons.lang;

import static net.sf.staccatocommons.lang.Range.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.staccatocommons.lang.Range;
import net.sf.staccatocommons.testing.junit.SerializationAssert;

import org.junit.Test;

/**
 * @author flbulgarelli
 */
public class RangeUnitTest {

	@Test
	public void testContains() {
		assertTrue(from("Hello", "World").contains("Java"));
		assertFalse(from(5, 9).contains(10));
	}

	@Test
	public void testOverlaps() {
		assertTrue(from(10, 400).overlaps(from(60, 80)));
		assertTrue(from(10, 400).overlaps(from(5, 80)));
		assertTrue(from(10, 400).overlaps(from(5, 900)));
		assertFalse(from(10, 400).overlaps(from(500, 900)));
		assertFalse(from(500, 650).overlaps(from(50, 90)));
	}

	@Test
	public void testIncludes() {
		assertTrue((from(5, 900)).includes(from(9, 65)));
		assertFalse((from(9, 65)).includes(from(5, 900)));
		assertFalse(from(10, 400).includes(from(5, 900)));
	}

	@Test
	public void testGetMax() {
		assertEquals((Integer) 9, from(5, 9).getMax());
	}

	@Test
	public void testGetMin() {
		assertEquals((Integer) 9, from(9, 98).getMin());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(from(8, 9).isEmpty());
		assertTrue(from(10, 10).isEmpty());
	}

	@Test
	public void testSerialization() throws Exception {
		SerializationAssert.assertCanSerialize(Range.from(50, 90));
	}
}
