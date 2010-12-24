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
package net.sf.staccato.commons.collections.stream.impl.internal;

import static junit.framework.Assert.assertEquals;
import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.lang.function.Function2;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class CharSequenceIteratorUnitTest extends IteratorAbstractUnitTest {

	@Test
	public void testname() throws Exception {
		String string = "hello world!";
		assertEquals(
			string,
			Streams.from(new CharSequenceIterator(string)).fold(
				"",
				new Function2<String, Character, String>() {
					public String apply(String arg1, Character arg2) {
						return arg1 += arg2;
					}
				}));
	}

	protected Iterable<?> createTwoElementsIterable() {
		return Streams.from(new CharSequenceIterator("ab"));
	}

	protected Iterable<?> createOneElementIterable() {
		return Streams.from(new CharSequenceIterator("a"));
	}
}
