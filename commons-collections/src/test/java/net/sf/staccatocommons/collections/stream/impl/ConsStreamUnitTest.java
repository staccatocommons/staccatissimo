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
package net.sf.staccatocommons.collections.stream.impl;

import net.sf.staccatocommons.collections.stream.Cons;
import net.sf.staccatocommons.collections.stream.RepetableStreamTheories;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.collections.stream.impl.internal.delayed.DelayedPrependStream;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;

/**
 * Test for {@link DelayedPrependStream}
 * 
 * @author flbulgarelli
 * 
 */

public class ConsStreamUnitTest extends RepetableStreamTheories {

	/**
	 * Setups the test
	 */
	@Before
	public void setup() {
		emptyIsImpossible();
	}

	/** Data points */
	@DataPoints
	public static Stream[] stream = new Stream[] { //
	Cons.from(10, Cons.from(15, 20, 30)), //
			Cons.from(1, Streams.empty()) };

}
