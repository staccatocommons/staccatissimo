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
package net.sf.staccatocommons.collections.stream.impl.internal;

import static net.sf.staccatocommons.lang.predicate.Predicates.greaterThan;
import net.sf.staccatocommons.collections.stream.Streams;

/**
 * @author flbulgarelli
 * 
 */
public class FilterIteratorUnitTest extends IteratorAbstractUnitTest {

	protected Iterable<?> createTwoElementsIterable() {
		return Streams.from(10, 20, 60, 50, 90).filter(greaterThan(55));
	}

	protected Iterable<?> createOneElementIterable() {
		return Streams.from(10, 20, 60, 50).filter(greaterThan(55));
	}

}
