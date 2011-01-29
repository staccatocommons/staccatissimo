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
package net.sf.staccatocommons.iterators;

import java.util.Iterator;

import net.sf.staccatocommons.testing.junit.IteratorAbstractUnitTest;

/**
 * @author flbulgarelli
 * 
 */
public class CharSequenceIteratorUnitTest extends IteratorAbstractUnitTest {

	protected Iterator<?> createTwoElementsIterator() {
		return new CharSequenceIterator("ab");
	}

	protected Iterator<?> createOneElementIterator() {
		return new CharSequenceIterator("a");
	}
}
