/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.collections.stream.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import net.sf.staccatocommons.collections.stream.RepetableStreamTheories;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.experimental.theories.DataPoints;

/**
 * @author flbulgarelli
 * 
 */
public class CollectionStreamUnitTest extends RepetableStreamTheories {

	/** Set of CollectionStreams to test */
	@DataPoints
	public static Stream[] streams = new Stream[] {
			Streams.from((Collection<Integer>) Arrays.asList(4, 5, 6, 9, 33, 0)),
			Streams.from((Collection<Integer>) Arrays.<Integer> asList()),
			Streams.from(Collections.EMPTY_SET), Streams.from(Collections.singleton(5)),
			Streams.from((Collection<Integer>) Arrays.asList(900, 5)) };

}
