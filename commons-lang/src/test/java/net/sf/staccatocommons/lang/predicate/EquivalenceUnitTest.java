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
package net.sf.staccatocommons.lang.predicate;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;
import net.sf.staccatocommons.defs.Applicable;

import org.junit.Test;

/**
 * 
 * Test for {@link Equiv}
 * 
 * @author flbulgarelli
 * 
 */
public class EquivalenceUnitTest {

	/** Test method for {@link Predicates#equalOrNull()} */
	@Test
	public void testEquality() throws Exception {
		assertFalse(Equiv.<Integer> equalOrNull().eval(4, 9));
		assertTrue(Equiv.<String> equalOrNull().eval("Foo", "Foo"));
		assertTrue(Equiv.<String> equalOrNull().eval(null, null));
		assertFalse(Equiv.<String> equalOrNull().eval(null, "foo"));
		assertSame(Equiv.equalOrNull(), Equiv.equalOrNull());
	}

	/** Test method for {@link Predicates#compareOrNull()} */
	@Test
	public void testCompareOrNull() throws Exception {
		assertFalse(Equiv.<Integer> compareOrNull().eval(4, 5));
		assertTrue(Equiv.<String> compareOrNull().eval("Foo", "Foo"));
		assertTrue(Equiv.<String> compareOrNull().eval(null, null));
		assertFalse(Equiv.<String> compareOrNull().eval(null, ""));
		assertFalse(Equiv.<String> compareOrNull().eval("", null));
		assertSame(Equiv.<Integer> compareOrNull(), Equiv.<Integer> compareOrNull());
	}

	/** Test for {@link Equiv#on(Applicable)} */
	@Test
	public void testOn() throws Exception {
		assertTrue(Equiv.on(first(Integer.class)).eval(_(10, 20), _(10, 5)));
	}
}
