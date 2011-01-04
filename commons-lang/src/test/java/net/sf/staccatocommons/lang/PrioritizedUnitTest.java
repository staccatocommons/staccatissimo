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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Test for {@link Prioritized}
 * 
 * @author flbulgarelli
 */
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

	/**
	 * Test for {@link Prioritized#equals(Object)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEquals() throws Exception {
		assertEquals(Prioritized.from(50, 90), Prioritized.from(20, 90));
	}
}
