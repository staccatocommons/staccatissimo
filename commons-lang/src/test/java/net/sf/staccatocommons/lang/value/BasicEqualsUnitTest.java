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
package net.sf.staccatocommons.lang.value;

import static org.junit.Assert.*;

import java.util.Date;

import net.sf.staccatocommons.lang.Option;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class BasicEqualsUnitTest {

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.value.BasicEquals#from(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testFrom() {
		String foo = "hello";
		String bar = foo;
		assertSame(BasicEquals.MAYBE, BasicEquals.from(10, new Integer(10)));
		assertSame(BasicEquals.ALWAYS, BasicEquals.from(foo, bar));
		assertSame(BasicEquals.NEVER, BasicEquals.from(new Date(), Option.some(5)));
		assertSame(BasicEquals.NEVER, BasicEquals.from(10, null));
		assertSame(BasicEquals.NEVER, BasicEquals.from(new Integer(10), new Long(10L)));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.value.BasicEquals#toEquals()}.
	 */
	@Test
	public void testToEquals() {
		assertTrue(BasicEquals.ALWAYS.toEquals());
		assertFalse(BasicEquals.NEVER.toEquals());
	}

	/**
	 * Test for {@link BasicEquals#toEquals()} on {@link BasicEquals#MAYBE}
	 */
	@Test(expected = IllegalStateException.class)
	public void testToEquals_Maybe() {
		BasicEquals.MAYBE.toEquals();
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.value.BasicEquals#isEqualsDone()}.
	 */
	@Test
	public void testIsEqualsDone() {
		assertTrue(BasicEquals.ALWAYS.isEqualsDone());
		assertTrue(BasicEquals.NEVER.isEqualsDone());
		assertFalse(BasicEquals.MAYBE.isEqualsDone());
	}

}
