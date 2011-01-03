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
package net.sf.staccato.commons.lang.tuple;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static net.sf.staccato.commons.lang.tuple.Tuple._;

import java.util.Date;

import org.junit.Test;

/**
 * Test for {@link Triple}
 * 
 * @author flbulgarelli
 */
public class TripleUnitTest extends TupleAbstractUnitTest {

	@Override
	public void testComponents() throws Exception {
		Triple<Integer, String, String> triple = _(9, "Hello", "World");
		assertEquals((Integer) 9, triple.getFirst());
		assertSame(triple._1(), triple.getFirst());

		assertEquals("Hello", triple.getSecond());
		assertSame(triple._2(), triple.getSecond());

		assertEquals("World", triple.getThird());
		assertSame(triple._3(), triple.getThird());
	}

	@Override
	public void testComparability() throws Exception {
		assertTrue(_(50, 2, 3).compareTo(_(50, 2, 3)) == 0);
		assertTrue(_(50, 2, 3).compareTo(_(50, 2, 4)) < 0);
		assertTrue(_(50, 2, 3).compareTo(_(50, 3, 1)) < 0);
		assertTrue(_(50, 2, 3).compareTo(_(51, 1, 1)) < 0);
		assertTrue(_(50, 2, 3).compareTo(_(50, 2, 2)) > 0);
		assertTrue(_(50, 2, 3).compareTo(_(50, 1, 3)) > 0);
		assertTrue(_(50, 2, 3).compareTo(_(49, 2, 3)) > 0);
	}

	@Override
	public void testEqualty() throws Exception {
		assertEquals(_(5, 90L, "Bye"), _(5, 90L, "Bye"));
		assertFalse(_("Hello", 0, 0).equals(_("World", 0, 1)));
		Date date = new Date();
		assertEquals(_(40, date, "hello").hashCode(), _(40, date.clone(), "hello").hashCode());
	}

	@Test
	public void testRotate() throws Exception {
		assertEquals(_(2, 3, 1), _(1, 2, 3).rotateLeft());
		assertEquals(_(3, 1, 2), _(1, 2, 3).rotateRight());
	}

	@Override
	public void testToString() throws Exception {
		assertEquals("(90,6,8)", _(90, 6, 8).toString());

	}

	@Override
	public void testToArray() throws Exception {
		Triple<Integer, Integer, Integer> triple = _(90, 6, 5);
		Object[] a = triple.toArray();
		assertEquals(triple, _(a[0], a[1], a[2]));
	}

	@Override
	protected Tuple sampleTuple() {
		return _(new Object(), 9, 5, 5);
	}

}
