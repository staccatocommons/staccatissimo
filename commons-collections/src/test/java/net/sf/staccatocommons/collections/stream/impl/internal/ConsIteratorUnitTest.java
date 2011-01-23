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
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Arrays;
import java.util.Iterator;

import net.sf.staccatocommons.collections.internal.ConsIterator;
import net.sf.staccatocommons.collections.internal.EmptyIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;

/**
 * @author flbulgarelli
 * 
 */
public class ConsIteratorUnitTest extends IteratorAbstractUnitTest {

	protected Iterable<?> createTwoElementsIterable() {
		return new AbstractStream<Integer>() {
			public Iterator<Integer> iterator() {
				return new ConsIterator<Integer>(50, Arrays.asList(10).iterator());
			}
		};
	}

	protected Iterable<?> createOneElementIterable() {
		return new AbstractStream<Integer>() {
			public Iterator<Integer> iterator() {
				return new ConsIterator<Integer>(60, EmptyIterator.<Integer> getInstance());
			}
		};
	}

}
