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
package net.sf.staccato.commons.testing.junit;

import java.util.Collection;
import java.util.Map;

public class Assert extends org.junit.Assert {

	public static <T> void assertContains(Collection<T> collection, T element) {
		assertTrue(//
			String.format("collection %s should contain element %s", collection, element),
			collection.contains(element));
	}

	public static <T> void assertContains(String message, Collection<T> collection, T element) {
		assertTrue(message, collection.contains(element));
	}

	public static <K, V> void assertContains(String message, Map<K, V> map, K key, V value) {
		assertEquals(message, value, map.get(key));
	}

	public static <K, V> void assertContainsEntry(String message, Map<K, V> map, K key, V value) {
		assertEquals(message, value, map.get(key));
	}

	public static <K, V> void assertContainsEntry(Map<K, V> map, K key, V value) {
		assertEquals( //
			String.format("map %s should contain key %s and value %s", map, key, value),
			value,
			map.get(key));
	}

}
