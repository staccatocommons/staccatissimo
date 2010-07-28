package net.sf.staccato.commons.lang.range;

import static net.sf.staccato.commons.lang.range.Range.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RangeUnitTest {

	@Test
	public void testContains() {
		assertTrue(range("Hello", "World").contains("Java"));
		assertFalse(range(5, 9).contains(10));
	}

	@Test
	public void testOverlaps() {
		assertTrue(range(10, 400).overlaps(range(60, 80)));
		assertTrue(range(10, 400).overlaps(range(5, 80)));
		assertTrue(range(10, 400).overlaps(range(5, 900)));
		assertFalse(range(10, 400).overlaps(range(500, 900)));
		assertFalse(range(500, 650).overlaps(range(50, 90)));
	}

	@Test
	public void testIncludes() {
		assertTrue((range(5, 900)).includes(range(9, 65)));
		assertFalse((range(9, 65)).includes(range(5, 900)));
		assertFalse(range(10, 400).includes(range(5, 900)));
	}

	@Test
	public void testGetMax() {
		assertEquals((Integer) 9, range(5, 9).getMax());
	}

	@Test
	public void testGetMin() {
		assertEquals((Integer) 9, range(9, 98).getMin());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(range(8, 9).isEmpty());
		assertTrue(range(10, 10).isEmpty());
	}

	public void testEquality() throws Exception {
		assertTrue(range(9, 50).clone().equals(range(9, 50)));
	}

}
