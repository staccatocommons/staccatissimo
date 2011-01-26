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
package net.sf.staccatocommons.collections.internal.iterator;

import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.collections.stream.impl.internal.IteratorAbstractUnitTest;

/**
 * @author flbulgarelli
 * 
 */
public class AppendIteratorUnitTest extends IteratorAbstractUnitTest {

	protected Iterable<?> createTwoElementsIterable() {
		return Streams.from(20).append(10);
	}

	protected Iterable<?> createOneElementIterable() {
		return Streams.empty().append(10);
	}

}
