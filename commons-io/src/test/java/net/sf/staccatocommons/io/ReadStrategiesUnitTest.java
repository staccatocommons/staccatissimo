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
package net.sf.staccatocommons.io;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import net.sf.staccatocommons.io.IOStreams;
import net.sf.staccatocommons.io.ReadStrategies;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ReadStrategiesUnitTest {

	private static final String SAMPLE_TEXT = "this is line 1\n this is line 2 \n this is line three";
	private Readable readable;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		readable = new StringReader(SAMPLE_TEXT);
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.io.ReadStrategies#readLines()}.
	 */
	@Test
	public void testReadLines() {
		assertEquals(
			"this is line 1| this is line 2 | this is line three",
			IOStreams.from(readable, ReadStrategies.readLines()).joinStrings("|"));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.io.ReadStrategies#readWords()}.
	 */
	@Test
	public void testReadWords() {
		assertEquals(
			"this|is|line|1|this|is|line|2|this|is|line|three",
			IOStreams.from(readable, ReadStrategies.readWords()).joinStrings("|"));
	}

}
