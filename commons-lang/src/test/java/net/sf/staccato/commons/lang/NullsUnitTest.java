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
package net.sf.staccato.commons.lang;

import static org.junit.Assert.assertEquals;
import net.sf.staccato.commons.lang.provider.internal.Constant;

import org.junit.Test;

public class NullsUnitTest {

	@Test
	public void testNonNullOrElseTT() {
		assertEquals("Foo", Nulls.coalesce(null, "Foo"));
		assertEquals("Bar", Nulls.coalesce("Bar", "Foo"));
	}

	@Test
	public void testNonNullOrElseTProviderOfT() {
		assertEquals("Foo", Nulls.coalesce(null, new Constant("Foo")));
		assertEquals("Bar", Nulls.coalesce("Bar", new Constant("Foo")));
	}

}
