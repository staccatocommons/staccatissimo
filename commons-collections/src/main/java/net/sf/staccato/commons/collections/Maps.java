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

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.MapBuilder;
import net.sf.staccato.commons.lang.Option;

/**
 * 
 * @author flbulgarelli
 * 
 */
public class Maps {

	/**
	 * Answers the optional value for the given key
	 * 
	 * @param <V>
	 * @param map
	 * @param key
	 *          the key to lookup
	 * @return Some(value) if the key exists, None, otherwise
	 */
	public static <V> Option<V> get(@NonNull Map<?, V> map, Object key) {
		V value = map.get(key);
		if (value != null)
			return Option.some(value);

		if (map.containsKey(key))
			return Option.someNull();

		return Option.none();
	}

	public static <K> Option<K> anyKey(@NonNull Map<K, ?> map) {
		if (map.isEmpty())
			return Option.none();
		return Option.some(anyInternal(map.keySet()));
	}

	public static <K> K anyKeyOrNull(@NonNull Map<K, ?> map) {
		if (map.isEmpty())
			return null;
		return anyInternal(map.keySet());
	}

	public static <V> Option<V> anyValue(@NonNull Map<?, V> map) {
		if (map.isEmpty())
			return Option.none();
		return Option.some(anyInternal(map.values()));
	}

	public static <V> V anyValueOrNull(@NonNull Map<?, V> map) {
		if (map.isEmpty())
			return null;
		return anyInternal(map.values());
	}

	/**
	 * Answers if the given map is null or empty
	 * 
	 * @param map
	 * @return <code>map == null || map.isEmpty()</code>
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
