/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.builder.Builder;
import net.sf.staccatocommons.lang.builder.BuilderAlreadyUsedException;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Unmodifiable;

/**
 * A {@link Builder} for {@link Map}s. With the exception of
 * {@link #from(Map, Applicable)}, all factory methods of {@link MapBuilder}
 * grant to return builders that build {@link Unmodifiable} maps
 * 
 * @author fbulgarelli
 * 
 * @param <K>
 * @param <V>
 * @param <M>
 */
public class MapBuilder<K, V, M extends Map<K, V>> implements Builder<M> {

  private M map;
  private Applicable<M, M> wrapperFunction;

  /**
   * 
   * Creates a new {@link MapBuilder}
   * 
   * @param map
   *          the map to build. Non null
   */
  public MapBuilder(@NonNull M map, @NonNull Applicable<M, M> wrapperFunction) {
    this.map = map;
    this.wrapperFunction = wrapperFunction;
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

  /**
   * Sets the wrapper function, that is, the {@link Applicable} to apply to the
   * resulting map before returning it through {@link #build()}.
   * 
   * By default, maps are wrapped with unmodifiable wrappers, but this behavior
   * can be changed through this method.
   * 
   * @param wrapperFunction
   * @return this
   */
  @NonNull
  public MapBuilder<K, V, M> withWrapper(@NonNull Applicable<M, M> wrapperFunction) {
    this.wrapperFunction = wrapperFunction;
    return this;
  }

  /**
   * Disables wrapping, which means that a built map will be returned just as it
   * was created.
   * 
   * As a consequence, the map returned by {@link #build()} will be modifiable.
   * 
   * @return this
   */
  @NonNull
  public MapBuilder<K, V, M> unwrap() {
    return withWrapper(Functions.<M> identity());
  }

  @NonNull
  public M build() {
    M map = this.map;
    this.map = null;
    if (map == null)
      throw new BuilderAlreadyUsedException();
    return wrapperFunction.apply(map);
  }

  /**
   * Creates a new {@link MapBuilder} that uses the given map instance, and
   * using {@link #toUnmodifiableMap()} as postprocessor. The map built by the
   * returned {@link Builder} grants to be {@link Unmodifiable}
   * 
   * @param <K>
   * @param <V>
   * @param map
   * @return a new {@link MapBuilder} that builds unmodifiable views of the
   *         given map
   */
  @NonNull
  public static <K, V> MapBuilder<K, V, Map<K, V>> from(@NonNull Map<K, V> map) {
    return from(map, MapBuilder.<K, V> toUnmodifiableMap());
  }

  /**
   * Creates a new {@link MapBuilder} using a {@link HashMap} as map
   * implementation and the first key and value. The map built by the returned
   * {@link Builder} grants to be {@link Unmodifiable}
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
  public static <K, V> MapBuilder<K, V, Map<K, V>> mapWith(K key, V value) {
    return from(new HashMap<K, V>()).with(key, value);
  }

  /**
   * Creates a new {@link MapBuilder} using a {@link LinkedHashMap} as map
   * implementation and the first key and value. The map built by the returned
   * {@link Builder} grants to be {@link Unmodifiable}
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
    return from(new LinkedHashMap<K, V>()).with(key, value);
  }

  /**
   * Creates a new {@link MapBuilder} using a {@link TreeMap} as map
   * implementation and the first key and value. The map built by the returned
   * {@link Builder} grants to be {@link Unmodifiable}
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
    return from(new TreeMap<K, V>(), MapBuilder.<K, V> toUnmodifiableSortedMap()).with(key, value);
  }

  /**
   * Creates a new {@link MapBuilder} using the given map instance and
   * wrapperFunction that would be applied to the built map before being
   * returned. If no wrapping is required, pass {@link Functions#identity()}
   * 
   * @param <K>
   *          type of key
   * @param <V>
   *          type of value
   * @param <M>
   *          type of {@link Map}
   * @param map
   *          the map to build
   * @param wrapperFunction
   *          the {@link Applicable} to be applied to the given map before
   *          returning it. If the {@link Applicable} returns a different map
   *          instead of modifying it, then that map is returned.
   * @return a new {@link MapBuilder}
   */
  @NonNull
  private static <K, V, M extends Map<K, V>> MapBuilder<K, V, M> from(@NonNull M map, Applicable<M, M> wrapperFunction) {
    return new MapBuilder<K, V, M>(map, wrapperFunction);
  }

  /**
   * A constant function that returns an unmodifiable view of its map argument
   * 
   * @param <K>
   * @param <V>
   * @return a constant {@link Function}
   */
  @Constant
  public static <K, V> Function<Map<K, V>, Map<K, V>> toUnmodifiableMap() {
    return new AbstractFunction<Map<K, V>, Map<K, V>>() {
      public Map<K, V> apply(Map<K, V> arg) {
        return Collections.unmodifiableMap(arg);
      }
    };
  }

  /**
   * A constant function that returns an unmodifiable view of its sortedmap
   * argument
   * 
   * @param <K>
   * @param <V>
   * @return a constant {@link Function}
   */
  @Constant
  public static <K, V> Function<SortedMap<K, V>, SortedMap<K, V>> toUnmodifiableSortedMap() {
    return new AbstractFunction<SortedMap<K, V>, SortedMap<K, V>>() {
      public SortedMap<K, V> apply(SortedMap<K, V> arg) {
        return Collections.unmodifiableSortedMap(arg);
      }
    };
  }

}
