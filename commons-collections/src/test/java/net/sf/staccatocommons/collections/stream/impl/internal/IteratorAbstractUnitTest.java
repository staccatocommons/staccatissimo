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

import static junit.framework.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccato.commons.lang.sequence.Sequence;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Abstract test for assuring some properties of a well defined iterator
 * 
 * @author flbulgarelli
 * 
 */
@RunWith(Theories.class)
public abstract class IteratorAbstractUnitTest {

	/** Sizes of 1 and 2 **/
	@DataPoints
	public static int[] sizes = new int[] { 1, 2 };

	/**
	 * Test that for an iterator of a given size, next can be invoked size times
	 * 
	 * @param size
	 * @throws Exception
	 */
	@Theory
	public void testNext(int size) throws Exception {
		Iterator<?> it = createIterable(size).iterator();
		for (@SuppressWarnings("unused")
		Integer i : times(size)) {
			it.next();
		}
	}

	@Theory
	@Test(expected = NoSuchElementException.class)
	public void testNextThrowsNoSuchElementException(int size) throws Exception {
		Iterator<?> it = createIterable(size).iterator();
		for (@SuppressWarnings("unused")
		Integer i : times(size + 1)) {
			it.next();
		}
	}

	@Theory
	public void testHasNextIsRepeatable(int size) throws Exception {
		Iterator<?> it = createIterable(size).iterator();
		for (Integer i : times(size + 2)) {
			assertTrue("On iteration " + i, it.hasNext());
		}
	}

	private static Iterable<Integer> times(int times) {
		return Sequence.fromTo(1, times);
	}

	/**
	 * Creates an iterable for the given size
	 * 
	 * @param size
	 * @return
	 */
	public Iterable<?> createIterable(int size) {
		switch (size) {
		case 1:
			return createOneElementIterable();
		default:
			return createTwoElementsIterable();
		}
	}

	/**
	 * @return
	 */
	protected abstract Iterable<?> createTwoElementsIterable();

	/**
	 * @return
	 */
	protected abstract Iterable<?> createOneElementIterable();

}
