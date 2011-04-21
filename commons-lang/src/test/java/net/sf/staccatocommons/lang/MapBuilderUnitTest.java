/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.lang;

import static net.sf.staccatocommons.lang.MapBuilder.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.SortedMap;

import net.sf.staccatocommons.lang.builder.BuilderAlreadyUsedException;
import net.sf.staccatocommons.restrictions.value.Immutable;

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
		assertThat(map, hasEntry("one", 1));
		assertThat(map, hasEntry("two", 2));
		assertThat(map, hasEntry("three", 3));
		assertThat(map, hasEntry("four", 4));
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
		assertThat(mapWith("FOO", 5).build(), instanceOf(Map.class));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.MapBuilder#linkedMapWith(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testLinkedMapWith() {
		assertThat(linkedMapWith("FOO", 5).build(), instanceOf(Map.class));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.MapBuilder#treeMapWith(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public void testTreeMapWith() {
		assertThat(treeMapWith("FOO", 5).build(), instanceOf(SortedMap.class));
	}

	/**
	 * Tests that maps returned by {@link MapBuilder#mapWith(Object, Object)} are
	 * Conditionally {@link Immutable}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testMapIsImmutable() {
		Map<String, Integer> map = MapBuilder //
			.mapWith("Tom", 10)
			.with("Annie", 5)
			.with("Mary", 12)
			.build();

		map.put("John", 6);
	}

}
