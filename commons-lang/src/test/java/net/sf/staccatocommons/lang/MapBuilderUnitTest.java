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
package net.sf.staccatocommons.lang;

import static net.sf.staccatocommons.lang.MapBuilder.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static net.sf.staccatocommons.testing.junit.asserts.CollectionAssert.*;
import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.sf.staccatocommons.lang.builder.BuilderAlreadyUsedException;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class MapBuilderUnitTest {

	/**
	 * Tests the usual usage of a {@link MapBuilder}
	 */
	@Test
	public void testMapBuilder() {
		Map<String, Integer> map = //
		mapWith("one", 1) //
			.with("two", 2)
			.with("three", 3)
			.with(_("four", 4))
			.build();
		assertContainsEntry(map, "one", 1);
		assertContainsEntry(map, "two", 2);
		assertContainsEntry(map, "three", 3);
		assertContainsEntry(map, "four", 4);
	}

	/** Test for {@link MapBuilder#build()} when it has been already be built */
	@Test(expected = BuilderAlreadyUsedException.class)
	public void testReuse() throws Exception {
		MapBuilder<String, Integer, Map<String, Integer>> mb = //
		mapWith("hello", 10).with("world", 1);
		mb.build();
		mb.build();
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.MapBuilder#mapWith(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testHashMapWith() {
		assertThat(mapWith("FOO", 5).build(), instanceOf(HashMap.class));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.MapBuilder#linkedMapWith(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testLinkedMapWith() {
		assertThat(linkedMapWith("FOO", 5).build(), instanceOf(LinkedHashMap.class));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.MapBuilder#treeMapWith(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testTreeMapWith() {
		assertThat(treeMapWith("FOO", 5).build(), instanceOf(TreeMap.class));
	}

}
