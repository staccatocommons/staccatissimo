package net.sf.staccato.commons.lang.interval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import net.sf.staccato.commons.lang.range.Interval;

import org.junit.Test;

public class IntervalUnitTest {

	@Test
	public void testFromTo_Asc() {
		Interval<Integer> range = Interval.fromTo(1, 5);

		assertNotNull(range);
		assertEquals((Integer) 1, range.getFrom());
		assertEquals((Integer) 5, range.getTo());

		assertEquals(Arrays.asList(1, 2, 3, 4, 5), range.asList());

	}

	@Test
	public void testFromTo_Asc_WithStep() {
		Interval<Integer> range = Interval.fromToBy(1, 10, 2);

		assertNotNull(range);
		assertEquals((Integer) 1, range.getFrom());
		assertEquals((Integer) 10, range.getTo());

		assertEquals(Arrays.asList(1, 3, 5, 7, 9), range.asList());

	}

	@Test
	public void testFromTo_Desc() {
		Interval<Integer> range = Interval.fromToBy(5, 1, -1);

		assertNotNull(range);
		assertEquals((Integer) 5, range.getFrom());
		assertEquals((Integer) 1, range.getTo());

		assertEquals(Arrays.asList(5, 4, 3, 2, 1), range.asList());

	}

	@Test
	public void testFromTo_Desc_WithStep() {
		Interval<Integer> range = Interval.fromToBy(10, 3, -2);

		assertNotNull(range);
		assertEquals((Integer) 10, range.getFrom());
		assertEquals((Integer) 3, range.getTo());

		assertEquals(Arrays.asList(10, 8, 6, 4), range.asList());

	}

}
