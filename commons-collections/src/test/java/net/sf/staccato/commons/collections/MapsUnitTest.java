package net.sf.staccato.commons.collections;

import static net.sf.staccato.commons.testing.junit.Assert.assertContainsEntry;

import java.util.Map;

import org.junit.Test;

public class MapsUnitTest {

	@Test
	public void testWith() {
		Map<String, Integer> map = Maps //
			.with("one", 1)
			.with("two", 2)
			.with("three", 3)
			.build();
		assertContainsEntry(map, "one", 1);
		assertContainsEntry(map, "two", 2);
		assertContainsEntry(map, "three", 3);
	}
}
