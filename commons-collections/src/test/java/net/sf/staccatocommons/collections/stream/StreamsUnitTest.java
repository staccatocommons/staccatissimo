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
package net.sf.staccatocommons.collections.stream;

import static junit.framework.Assert.*;
import static net.sf.staccatocommons.lang.sequence.StopConditions.*;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.lang.sequence.internal.IntegerIncrement;

import org.junit.Test;

/**
 * 
 * Test for {@link Streams}
 * 
 * @author flbulgarelli
 * 
 */
public class StreamsUnitTest {

	/**
	 * Test method for
	 * {@link Streams#from(java.lang.Object, Applicable, Evaluable)}.
	 */
	@Test
	public void testFromSeq() {
		assertEquals(
			Streams.from(10, new IntegerIncrement(20), upTo(50)).toList(),
			Streams.from(Sequence.fromToBy(10, 50, 20)).toList());
	}

}
