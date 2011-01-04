package net.sf.staccatocommons.collections;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;

import net.sf.staccatocommons.collections.Lists;

import org.junit.Test;

public class ListsUnitTests {

	@Test(expected = IllegalArgumentException.class)
	public void testThird_BadSize() throws Exception {
		Lists.third(Arrays.asList(1, 2));
	}

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

	@Test(expected = IllegalArgumentException.class)
	public void testFirst_BadSize() throws Exception {
		Lists.first(Arrays.asList());
	}

	@Test
	public void testSecond_OK() throws Exception {
		assertEquals((Integer) 2, Lists.second(Arrays.asList(1, 2, 3)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSecondBadSize() throws Exception {
		Lists.second(Arrays.asList(1));
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
