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
package net.sf.staccatocommons.lang.tuple.internal;

import static net.sf.staccatocommons.lang.tuple.Tuple.*;
import static org.junit.Assert.*;

import java.util.Date;

import net.sf.staccatocommons.lang.tuple.Triple;
import net.sf.staccatocommons.lang.tuple.internal.TupleValue;
import net.sf.staccatocommons.lang.tuple.internal.TupleValue.Criteria;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class TupleValueUnitTest {

	private TupleValue<Triple> val = new TupleValue<Triple>(3) {
		protected void significant(Triple o, Criteria b) {
			b.with(o._1()).with(o._2()).with(o._3());
		}
	};

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.value.Val#hashCode(java.lang.Object)}.
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

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.value.Val#equals(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testHashCode() {
		assertEquals(val.hashCode(_(1, 2, 6)), val.hashCode(_(1, 2, 6)));
		assertEquals(val.hashCode(_("foo", 5L, '5')), val.hashCode(_("foo", 5L, '5')));

		assertTrue(val.hashCode(_("foo", 5L, '5')) != val.hashCode(_("fii", 5L, '5')));
		assertTrue(val.hashCode(_("foo", 5L, '5')) != val.hashCode(_("foo", 619L, '5')));
		assertTrue(val.hashCode(_("foo", 5L, '5')) != val.hashCode(_("foo", 5L, 'a')));
	}

}
