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
package net.sf.staccato.commons.instrument.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class SimpleInstrumentationMarkUnitTest {

	/**
	 * Test method for
	 * {@link SimpleInstrumentationMark#SimpleInstrumentationMark(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testSimpleInstrumentationMark() {
		SimpleInstrumentationMark mark = new SimpleInstrumentationMark("mark-key", "mark-value");
		assertEquals("mark-key", mark.getMarkAttributeName());
		assertEquals("mark-value", new String(mark.getMarkAttributeValue()));
	}

}
