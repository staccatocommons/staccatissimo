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
package net.sf.staccato.commons.collections.stream;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author flbulgarelli
 * 
 */
public class NonEmptyCollectionStreamUnitTest extends
	NonEmptyFiniteConstantStreamAbstractUnitTest<Integer> {
	@Override
	protected Stream<Integer> createStream() {
		return Streams.from((Collection<Integer>) Arrays.asList(4, 5, 6, 9, 33, 0));
	}

}
