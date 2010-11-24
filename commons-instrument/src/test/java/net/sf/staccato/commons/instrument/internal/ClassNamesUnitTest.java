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
package net.sf.staccato.commons.instrument.internal;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.sf.staccato.commons.instrument.internal.ClassNames;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ClassNamesUnitTest {

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.instrument.ClassNames#getClassName(java.io.File)}
	 * .
	 */
	@Test
	public void testGetClassName_AbsolutePath() {
		assertEquals("com.foo.Foo",//
			ClassNames.getClassName(//
				new File("/home/user/classes"),
				new File("/home/user/classes/com/foo/Foo.class")));
	}

	@Test
	public void testGetClassName_AbsolutePathWithEndSeparator() {
		assertEquals("com.foo.Foo",//
			ClassNames.getClassName(//
				new File("/home/user/classes/"),
				new File("/home/user/classes/com/foo/Foo.class")));
	}

	@Test
	public void testGetClassName_RelativePaths() {
		assertEquals("com.foo.Foo",//
			ClassNames.getClassName(//
				new File("classes"),
				new File("classes/com/foo/Foo.class")));
	}

	@Test
	public void testGetClassName_InnerClasses() {
		assertEquals("com.foo.Foo$Internal",//
			ClassNames.getClassName(//
				new File("classes"),
				new File("classes/com/foo/Foo$Internal.class")));
	}

}
