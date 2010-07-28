package net.sf.staccato.commons.lang;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class PrioritizedUnitTest {

	@Test
	public void testFrom() {

		List<String> value = Collections.emptyList();
		int priority = 5;

		Prioritized<List<String>, Integer> p1 = Prioritized.from(value, priority);

		assertSame(value, p1.value());
		assertSame(priority, p1.getPriority());
	}

	@Test
	public void testCompare() {

		List<String> value1 = Collections.emptyList();
		int priority1 = 5;

		List<String> value2 = Arrays.asList("Hello", "World", "Fooe");
		int priority2 = 1;

		Prioritized<List<String>, Integer> p1 = Prioritized.from(value1, priority1);
		Prioritized<List<String>, Integer> p2 = Prioritized.from(value2, priority2);

		assertTrue(p2.compareTo(p1) <= 0);

	}
}
