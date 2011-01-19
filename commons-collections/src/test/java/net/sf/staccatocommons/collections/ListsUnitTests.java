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
package net.sf.staccatocommons.collections;

import static junit.framework.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Test for {@link Lists}
 * 
 * @author flbulgarelli
 * 
 */
public class ListsUnitTests {

	@Test
	public void testAddBefore_middle() throws Exception {

		LinkedList<Integer> list = oneThreeList();

		Lists.addBefore(list, 2, 3);
		assertEquals(Arrays.asList(1, 2, 3), list);
	}

	@Test
	public void testAddBefore_head() throws Exception {

		LinkedList<Integer> list = oneThreeList();

		Lists.addBefore(list, 2, 1);

		assertEquals(Arrays.asList(2, 1, 3), list);

	}

	@Test
	public void testAddAfter_middle() throws Exception {

		LinkedList<Integer> list = oneThreeList();

		Lists.addAfter(list, 2, 1);

		assertEquals(Arrays.asList(1, 2, 3), list);
	}

	@Test
	public void testAddAfter_tail() throws Exception {

		LinkedList<Integer> list = oneThreeList();

		Lists.addAfter(list, 2, 3);

		assertEquals(Arrays.asList(1, 3, 2), list);
	}

	@Test
	public void testFirst_OK() throws Exception {
		assertEquals((Integer) 1, Lists.first(Arrays.asList(1, 2, 3)));
	}

	@Test
	public void testSecond_OK() throws Exception {
		assertEquals((Integer) 2, Lists.second(Arrays.asList(1, 2, 3)));
	}

	@Test
	public void testThird_OK() throws Exception {
		assertEquals((Integer) 3, Lists.third(Arrays.asList(1, 2, 3)));
	}

	private LinkedList<Integer> oneThreeList() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(3);
		return list;
	}
}
