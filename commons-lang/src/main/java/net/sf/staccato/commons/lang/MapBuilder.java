/*
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
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

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

	public MapBuilder(M map) {
		this.map = map;
	}

	/**
	 * Adds a key-value pair to the map
	 * 
	 * @param key
	 * @param value
	 * @return this {@link MapBuilder}
	 */
	public MapBuilder<K, V, M> with(K key, V value) {
		map.put(key, value);
		return this;
	}

	/**
	 * Adds an entry to the map
	 * 
	 * @param key
	 * @param value
	 * @return this {@link MapBuilder}
	 */
	public MapBuilder<K, V, M> with(Entry<K, V> entry) {
		map.put(entry.getKey(), entry.getValue());
		return this;
	}

	public M build() {
		M map = this.map;
		this.map = null;
		if (map == null)
			throw new BuilderAlreadyUsedException();
		return map;
	}

	public static <K, V> MapBuilder<K, V, Map<K, V>> hashMapWith(K key, V value) {
		return new MapBuilder<K, V, Map<K, V>>( //
			new HashMap<K, V>()).with(key, value);
	}

	public static <K, V> MapBuilder<K, V, Map<K, V>> linkedMapWith(K key, V value) {
		return new MapBuilder<K, V, Map<K, V>>( //
			new LinkedHashMap<K, V>()).with(key, value);
	}

	public static <K, V> MapBuilder<K, V, SortedMap<K, V>> treeMapWith(K key,
		V value) {
		return new MapBuilder<K, V, SortedMap<K, V>>( //
			new TreeMap<K, V>()).with(key, value);
	}

}
