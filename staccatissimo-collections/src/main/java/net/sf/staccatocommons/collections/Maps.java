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

package net.sf.staccatocommons.collections;

import static net.sf.staccatocommons.collections.iterable.Iterables.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.MapBuilder;
import net.sf.staccatocommons.lang.None;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Unmodifiable;

/**
 * Class methods for dealing with maps
 * 
 * @author flbulgarelli
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
  @NonNull
  public static <V> Option<V> get(@NonNull Map<?, V> map, Object key) {
    V value = map.get(key);
    if (value != null)
      return Option.some(value);

    if (map.containsKey(key))
      return Option.someNull();

    return Option.none();
  }

  /**
   * Answers the value for the given key. Throws a
   * {@link NoSuchElementException} if there is no mapping for it
   * 
   * @param <V>
   * @param map
   * @param key
   *          the key to lookup
   * @return Some(value) if the key exists, None, otherwise
   */
  @NonNull
  public static <V> V getExistent(@NonNull Map<?, V> map, Object key) {
    V value = map.get(key);
    if (value != null)
      return value;

    if (map.containsKey(key))
      return null;

    throw new NoSuchElementException("No entry found for key " + key);
  }

  /**
   * Answers Some(key) from the given {@link Map}, or {@link None}, if it is
   * empty. Notice that <strong>any does not mean random</strong>, it may return
   * always the same key, as this method just guarantees that if Some(key) is
   * returned, map.containsKey(key) will return <code>true</code>
   * 
   * @param <K>
   * @param map
   * @return Some(key) if map is not empty, None otherwise
   */
  @NonNull
  public static <K> Option<K> anyKey(@NonNull Map<K, ?> map) {
    if (map.isEmpty())
      return Option.none();
    return Option.some(any(map.keySet()));
  }

  /**
   * Answers a key from the given {@link Map}, or null, if it is empty. Notice
   * that <strong>any does not mean random</strong>, it may return always the
   * same key, as this method just guarantees that if a non null key is
   * returned, <code>map.containsKey(key)</code> will return <code>true</code>
   * 
   * @param <K>
   * @param map
   * @return a key if map is not empty, null otherwise
   */
  public static <K> K anyKeyOrNull(@NonNull Map<K, ?> map) {
    if (map.isEmpty())
      return null;
    return any(map.keySet());
  }

  /**
   * Answers a <code>Some(value)</code> from the given {@link Map}, or
   * {@link None}, if it is empty. Notice that <strong>any does not mean
   * random</strong>, it may return always the same value, this method just
   * guarantees that if a <code>Some(value)</code> is returned,
   * <code>map.containsValue(value)</code> will return <code>true</code>
   * 
   * @param <V>
   * @param map
   * @return Some(value) if map is not empty, None otherwise
   */
  @NonNull
  public static <V> Option<V> anyValue(@NonNull Map<?, V> map) {
    if (map.isEmpty())
      return Option.none();
    return Option.some(any(map.values()));
  }

  /**
   * Answers a value from the given {@link Map}, or <code>null</code>, if it is
   * empty. Notice that <strong>any does not mean random</strong>, it may return
   * always the same value, as this method just guarantees that if a
   * <code>value</code> is returned, <code>map.containsValue(value)</code> will
   * return <code>true</code>
   * 
   * @param <V>
   * @param map
   * @return a <code>value</code> if map is not empty, <code>null</code>
   *         otherwise
   */
  public static <V> V anyValueOrNull(@NonNull Map<?, V> map) {
    if (map.isEmpty())
      return null;
    return any(map.values());
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
  
  public static <K, V1, V2> Map<K, V2> mapKeys(Map<K, V1> map, Applicable<V1, V2> function) {
    Map<K, V2> result = new LinkedHashMap<K, V2>();
    for (Entry<K, V1> e : map.entrySet())
      result.put(e.getKey(), function.apply(e.getValue()));
    return Collections.unmodifiableMap(result);
  }
  
  public static <K, V1, V2> Map<K, V2> delayedMapKeys(final Map<K, V1> map,
    final Applicable<? super V1, ? extends V2> function) {
    return Collections.unmodifiableMap(new LinkedHashMap<K, V2>() {
      public V2 get(Object key) {
        return function.apply(map.get(key));
      }
    });
  }

  /**
   * Answers a new {@link Unmodifiable} map with the given entries
   * 
   * @param <K>
   * @param <V>
   * @param entries
   *          the new map entries
   * @return a new unmodifiable map
   */
  public static <K, V> Map<K, V> from(Iterable<Tuple2<K, V>> entries) {
    MapBuilder<K, V, Map<K, V>> b = MapBuilder.from(new LinkedHashMap<K, V>());
    for (Tuple2<K, V> p : entries)
      b.with(p);
    return b.build();
  }

  /**
   * Answers a new {@link Unmodifiable} map with the given entries
   * 
   * @param <K>
   * @param <V>
   * @param entries
   *          the new map entries
   * @return a new unmodifiable map
   */
  public static <K, V> Map<K, V> from(Tuple2<K, V>... entries) {
    return from(Arrays.asList(entries));
  }

}
