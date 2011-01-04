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
package net.sf.staccatocommons.lang.tuple;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * Abstract test for {@link Tuple}s
 * 
 * @author flbulgarelli
 * 
 */
public abstract class TupleAbstractUnitTest {

	@Test
	public abstract void testEqualty() throws Exception;

	@Test
	public abstract void testComparability() throws Exception;

	@Test
	public abstract void testComponents() throws Exception;

	@Test
	public abstract void testToString() throws Exception;

	@Test
	public abstract void testToArray() throws Exception;

	@Test
	public void testToList() throws Exception {
		Tuple sampleTuple = sampleTuple();
		assertEquals(sampleTuple.toList(), Arrays.asList(sampleTuple.toArray()));
	}

	protected abstract Tuple sampleTuple();

}
