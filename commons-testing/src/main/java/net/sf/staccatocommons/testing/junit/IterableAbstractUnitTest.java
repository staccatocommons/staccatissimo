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
package net.sf.staccatocommons.testing.junit;

import java.util.Iterator;

/**
 * @author flbulgarelli
 * 
 */
public abstract class IterableAbstractUnitTest extends IteratorAbstractUnitTest {

	protected final Iterator<?> createOneElementIterator() {
		return createOneElementIterable().iterator();
	}

	protected final Iterator<?> createTwoElementsIterator() {
		return createTwoElementsIterable().iterator();
	}

	protected abstract Iterable<?> createOneElementIterable();

	protected abstract Iterable<?> createTwoElementsIterable();

}
