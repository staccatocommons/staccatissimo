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

package net.sf.staccatocommons.collections;

import static net.sf.staccatocommons.collections.iterable.Iterables.*;

import java.util.Map;

import net.sf.staccatocommons.lang.None;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.restrictions.check.NonNull;

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

}
