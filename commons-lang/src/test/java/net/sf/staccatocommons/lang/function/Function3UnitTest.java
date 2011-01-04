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
package net.sf.staccatocommons.lang.function;

import static org.junit.Assert.assertEquals;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Test for {@link Function3}
 * 
 * @author flbulgarelli
 * 
 */
public class Function3UnitTest extends JUnit4MockObjectTestCase {

	Function3<Integer, String, Boolean, Character> function;
	Applicable3<Integer, String, Boolean, Character> applicable;

	/** Instantiates both function and applicable */
	@Before
	public void setup() {
		applicable = mock(Applicable3.class);
		function = new Function3<Integer, String, Boolean, Character>() {
			public Character apply(Integer arg1, String arg2, Boolean arg3) {
				return applicable.apply(arg1, arg2, arg3);
			}
		};
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.function.Function3#apply(java.lang.Object, java.lang.Object)}
	 * and {@link Function3#apply(Object, Object, Object)} .
	 */
	@Test
	public void testApply() {
		checking(new Expectations() {
			{
				exactly(3).of(applicable).apply(5, "foo", true);
				will(returnValue('a'));
			}
		});
		assertEquals('a', (char) function.apply(5, "foo", true));
		assertEquals('a', (char) function.apply(5, "foo").apply(true));
		assertEquals('a', (char) function.apply(5).apply("foo", true));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.function.Function3#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("Function3", function.toString());
	}
}
