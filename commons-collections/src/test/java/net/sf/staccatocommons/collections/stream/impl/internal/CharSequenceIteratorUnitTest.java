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
package net.sf.staccatocommons.collections.stream.impl.internal;

import static junit.framework.Assert.*;
import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class CharSequenceIteratorUnitTest extends IteratorAbstractUnitTest {

	/** Test that joining a character stream returns back the original string */
	@Test
	public void testJoin() throws Exception {
		String string = "hello world!";
		assertEquals(string, Streams.fromChars(string).joinStrings(""));
	}

	protected Iterable<?> createTwoElementsIterable() {
		return Streams.from(new CharSequenceIterator("ab"));
	}

	protected Iterable<?> createOneElementIterable() {
		return Streams.from(new CharSequenceIterator("a"));
	}
}
