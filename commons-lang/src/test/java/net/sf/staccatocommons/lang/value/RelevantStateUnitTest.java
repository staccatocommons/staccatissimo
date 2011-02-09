/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.value;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;

import java.util.Date;

import net.sf.staccatocommons.lang.tuple.Triple;
import net.sf.staccatocommons.lang.value.RelevantState.StateCollector;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class RelevantStateUnitTest {

	private RelevantState<Triple> val = new RelevantState<Triple>(
		3,
		NamedTupleToStringStyle.getInstance()) {
		protected void collectState(Triple o, StateCollector b) {
			b.add(o._1()).add(o._2()).add(o._3());
		}
	};

	/**
	 * Test method for {@link RelevantState#hashCode(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Date date = new Date();
		assertTrue(val.equals(_(date, "hello", 'a'), _(date.clone(), "hello", 'a')));
		assertTrue(val.equals(_(1, 2, 3), _(1, 2, 3)));
		assertFalse(val.equals(_(1, 2, 3), _(1, 2, 4)));
		assertFalse(val.equals(_(1, 2, 3), _("", 2, 3)));
		assertFalse(val.equals(_(1, 2, 3), _(1, 4)));
		assertFalse(val.equals(_(1, 2, 3), null));
	}

	/** Test method for {@link RelevantState#eval(Object, Object)} */
	@Test
	public void testEval() throws Exception {
		assertFalse(val.eval(_(1, 2, 3), _(1, 4, 6)));
		assertFalse(val.eval(_(1, 2, 3), null));
		assertFalse(val.eval(null, _(1, 1, 1)));
		assertTrue(val.eval(null, null));
		assertTrue(val.eval(_(1, 2, 3), _(1, 2, 3)));
	}

	/**
	 * Test method for
	 * {@link RelevantState#equals(java.lang.Object, java.lang.Object)} .
	 */
	@Test
	public void testHashCode() {
		assertEquals(val.hashCode(_(1, 2, 6)), val.hashCode(_(1, 2, 6)));
		assertEquals(val.hashCode(_("foo", 5L, '5')), val.hashCode(_("foo", 5L, '5')));

		assertTrue(val.hashCode(_("foo", 5L, '5')) != val.hashCode(_("fii", 5L, '5')));
		assertTrue(val.hashCode(_("foo", 5L, '5')) != val.hashCode(_("foo", 619L, '5')));
		assertTrue(val.hashCode(_("foo", 5L, '5')) != val.hashCode(_("foo", 5L, 'a')));
	}

	/** Test for {@link RelevantState#compareTo(Object, Object)} **/
	@Test
	public void testCompareTo() throws Exception {

		Triple<Integer, Integer, Integer> t = _(4, 5, 6);
		assertEquals(0, val.compareTo(t, t));
		assertEquals(0, val.compareTo(_(4, 5, 6), _(4, 5, 6)));
		assertTrue(val.compareTo(_(4, 10, 6), _(4, 5, 6)) > 0);

	}

	/** Test for {@link RelevantState#toString(Object)} **/
	@Test
	public void testToString() throws Exception {
		assertEquals("Triple(1,2,hello)", val.toString(_(1, 2, "hello")));
	}

	/** Test for primitive variants of {@link StateCollector#add(Object)} **/
	@Test
	public void testPrimitiveTypes() throws Exception {
		RelevantState<Triple<Integer, Boolean, Long>> rs = //
		new RelevantState<Triple<Integer, Boolean, Long>>(3) {
			protected void collectState(Triple<Integer, Boolean, Long> object, StateCollector s) {
				s.add((int) object._1()).add((boolean) object._2()).add((long) object._3());
			}
		};
		assertTrue(rs.eval(_(10, true, 5L), _(10, true, 5L)));
		assertTrue(rs.compareTo(_(10, true, 5L), _(10, true, 5L)) == 0);
		assertEquals(rs.hashCode(_(10, true, 5L)), rs.hashCode(_(10, true, 5L)));
	}
}
