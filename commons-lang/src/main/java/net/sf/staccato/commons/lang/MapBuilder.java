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
package net.sf.staccato.commons.lang;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.builder.Builder;
import net.sf.staccato.commons.lang.builder.BuilderAlreadyUsedException;

/**
 * 
 * @author fbulgarelli
 * 
 * @param <K>
 * @param <V>
 * @param <M>
 */
public class MapBuilder<K, V, M extends Map<K, V>> implements Builder<M> {

	private M map;

	/**
	 * 
	 * Creates a new {@link MapBuilder}
	 * 
	 * @param map
	 *          the map to build. Non null
	 */
	public MapBuilder(@NonNull M map) {
		this.map = map;
	}

	/**
	 * Adds a key-value pair to the map
	 * 
	 * @param key
	 * @param value
	 * @return this {@link MapBuilder}
	 */
	@NonNull
	public MapBuilder<K, V, M> with(K key, V value) {
		map.put(key, value);
		return this;
	}

	/**
	 * Adds an entry to the map
	 * 
	 * @param entry
	 *          the entry to add. Non null
	 * 
	 * @return this {@link MapBuilder}
	 */
	@NonNull
	public MapBuilder<K, V, M> with(@NonNull Entry<K, V> entry) {
		map.put(entry.getKey(), entry.getValue());
		return this;
	}

	@NonNull
	public M build() {
		M map = this.map;
		this.map = null;
		if (map == null)
			throw new BuilderAlreadyUsedException();
		return map;
	}

	/**
	 * Creates a new {@link MapBuilder} using the given map implementation and the
	 * first key and value
	 * 
	 * @param <K>
	 *          type of key
	 * @param <V>
	 *          type of value
	 * @param <M>
	 *          type of {@link Map}
	 * @param map
	 *          non null.
	 * @param key
	 * @param value
	 * @return a new {@link MapBuilder}
	 */
	@NonNull
	public static <K, V, M extends Map<K, V>> MapBuilder<K, V, M> mapWith(@NonNull M map, K key,
		V value) {
		return new MapBuilder<K, V, M>(map).with(key, value);
	}

	/**
	 * Creates a new {@link MapBuilder} using a {@link HashMap} as map
	 * implementation and the first key and value
	 * 
	 * @param <K>
	 *          type of key
	 * @param <V>
	 *          type of value
	 * @param key
	 * @param value
	 * @return a new {@link MapBuilder}
	 */
	@NonNull
	public static <K, V> MapBuilder<K, V, Map<K, V>> hashMapWith(K key, V value) {
		return mapWith((Map<K, V>) new HashMap<K, V>(), key, value);
	}

	/**
	 * Creates a new {@link MapBuilder} using a {@link LinkedHashMap} as map
	 * implementation and the first key and value
	 * 
	 * @param <K>
	 *          type of key
	 * @param <V>
	 *          type of value
	 * @param key
	 * @param value
	 * @return a new {@link MapBuilder}
	 */
	@NonNull
	public static <K, V> MapBuilder<K, V, Map<K, V>> linkedMapWith(K key, V value) {
		return mapWith((Map<K, V>) new LinkedHashMap<K, V>(), key, value);
	}

	/**
	 * Creates a new {@link MapBuilder} using a {@link TreeMap} as map
	 * implementation and the first key and value
	 * 
	 * @param <K>
	 *          type of key
	 * @param <V>
	 *          type of value
	 * @param key
	 * @param value
	 * @return a new {@link MapBuilder}
	 */
	@NonNull
	public static <K, V> MapBuilder<K, V, SortedMap<K, V>> treeMapWith(K key, V value) {
		return mapWith((SortedMap<K, V>) new TreeMap<K, V>(), key, value);
	}

}
