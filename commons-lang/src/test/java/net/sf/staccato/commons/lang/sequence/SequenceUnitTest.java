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
package net.sf.staccato.commons.lang.sequence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author flbulgarelli
 * 
 */
public class SequenceUnitTest {

	@Test
	public void testFromTo_Asc() {
		Sequence<Integer> seq = Sequence.fromTo(1, 5);
		assertNotNull(seq);
		assertEquals(Arrays.asList(1, 2, 3, 4, 5), asList(seq));

	}

	@Test
	public void testFromTo_Asc_WithStep() {
		Sequence<Integer> seq = Sequence.fromToBy(1, 10, 2);
		assertNotNull(seq);
		assertEquals(Arrays.asList(1, 3, 5, 7, 9), asList(seq));
	}

	@Ignore
	@Test
	public void testFromTo_Desc() {
		Sequence<Integer> seq = Sequence.fromToBy(5, 1, -1);

		assertNotNull(seq);
		assertEquals(Arrays.asList(5, 4, 3, 2), asList(seq));

	}

	@Test
	public void testFromTo_Desc_WithStep() {
		Sequence<Integer> seq = Sequence.fromToBy(10, 3, -2);

		assertNotNull(seq);
		assertEquals(Arrays.asList(10, 8, 6, 4), asList(seq));
	}

	private <T> List<T> asList(Sequence<T> seq) {
		LinkedList<T> l = new LinkedList<T>();
		for (T e : seq)
			l.add(e);
		return l;
	}

}
