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
package net.sf.staccatocommons.collections.stream.impl;

import java.util.Collections;

import net.sf.staccatocommons.collections.stream.RepetableStreamTheories;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.lang.sequence.Sequence;

import org.junit.experimental.theories.DataPoints;

/**
 * @author flbulgarelli
 * 
 */
public class IterableStreamUnitTest extends RepetableStreamTheories {

	/** Set of iterable Streams to test */
	@DataPoints
	public static Stream[] streams = new Stream[] { Streams.from(Sequence.fromToBy(85, 3, 190)),
			Streams.from((Iterable<Integer>) Streams.from(5, 9, 10, 156).toSet()),
			Streams.from((Iterable<Integer>) Collections.singleton(5)),
			Streams.from((Iterable<Integer>) Collections.<Integer> emptyList()) };

}
