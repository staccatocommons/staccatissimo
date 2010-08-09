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
package net.sf.staccato.commons.collections;

import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anyInternal;

import java.util.Map;
import java.util.SortedMap;

import net.sf.staccato.commons.lang.MapBuilder;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.check.Ensure;

public class Maps {

	private static final String MAP_PARAM = "map";

	public static <T> Option<T> get(Map<?, T> map, Object key) {
		Ensure.nonNull(MAP_PARAM, map);
		T value = map.get(key);
		if (value != null)
			return Option.some(value);

		if (map.containsKey(key))
			return Option.someNull();

		return Option.none();
	}

	public static <K> Option<K> anyKey(Map<K, ?> map) {
		Ensure.nonNull(MAP_PARAM, map);
		if (map.isEmpty())
			return Option.none();
		return Option.some(anyInternal(map.keySet()));
	}

	public static <K> K anyKeyOrNull(Map<K, ?> map) {
		Ensure.nonNull(MAP_PARAM, map);
		if (map.isEmpty())
			return null;
		return anyInternal(map.keySet());
	}

	public static <V> Option<V> anyValue(Map<?, V> map) {
		Ensure.nonNull(MAP_PARAM, map);
		if (map.isEmpty())
			return Option.none();
		return Option.some(anyInternal(map.values()));
	}

	public static <V> V anyValueOrNull(Map<?, V> map) {
		Ensure.nonNull(MAP_PARAM, map);
		if (map.isEmpty())
			return null;
		return anyInternal(map.values());
	}

	/**
	 * 
	 * @param <S>
	 * @param collection
	 * @return true if the collection is null or empty
	 */
	public static boolean isNullOrEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static <K, V> MapBuilder<K, V, Map<K, V>> with(K key, V value) {
		return hashedWith(key, value);
	}

	public static <K, V> MapBuilder<K, V, Map<K, V>> hashedWith(K key, V value) {
		return MapBuilder.hashMapWith(key, value);
	}

	public static <K, V> MapBuilder<K, V, Map<K, V>> linkedWith(K key, V value) {
		return MapBuilder.linkedMapWith(key, value);
	}

	public static <K, V> MapBuilder<K, V, SortedMap<K, V>> treeWith(K key, V value) {
		return MapBuilder.treeMapWith(key, value);
	}

}
