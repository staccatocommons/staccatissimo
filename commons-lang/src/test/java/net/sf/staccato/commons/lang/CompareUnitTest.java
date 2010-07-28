package net.sf.staccato.commons.lang;

import static net.sf.staccato.commons.lang.Compare.between;
import static net.sf.staccato.commons.lang.Compare.in;
import static net.sf.staccato.commons.lang.Compare.max;
import static net.sf.staccato.commons.lang.Compare.min;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CompareUnitTest {

	@Test
	public void testBetweenComparable() {

		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();

		calendar.add(Calendar.MONTH, 1);
		Date date2 = calendar.getTime();

		calendar.add(Calendar.MONTH, 1);
		Date date3 = calendar.getTime();

		assertTrue(between(date2, date1, date3));
		assertTrue(between(date3, date1, date3));
		assertTrue(between(date1, date1, date3));
		assertFalse(between(date1, date2, date3));
	}

	@Test
	public void testBetweenLong() {
		assertTrue(between(90L, 60, 156));
	}

	@Test
	public void testBetweenIntIntInt() {
		assertTrue(between(90, 60, 156));
		assertTrue(between(156, 60, 156));
		assertTrue(between(60, 60, 156));
		assertFalse(between(190, 60, 156));
	}

	@Test
	public void testInIntValues() {
		assertTrue(in(5, 1, 2, 3, 5, 9, 60));
		assertFalse(in(6, 1, 2, 3, 5, 9, 60));
	}

	@Test
	public void testInLongValues() {
		assertTrue(in(5, new int[] { 1, 2, 3, 5, 9, 60 }));
		assertFalse(in(6, new int[] { 1, 2, 3, 5, 9, 60 }));
	}

	@Test
	public void testIn() {
		assertTrue(in(5, new long[] { 1, 2, 3, 5, 9, 60 }));
		assertFalse(in(6, new long[] { 1, 2, 3, 5, 9, 60 }));
	}

	@Test
	public void testMax() {
		assertEquals((Integer) 5, max(5, 2));
	}

	@Test
	public void testMin() {
		assertEquals("ABC", min("FGH", "ABC"));
	}

}
