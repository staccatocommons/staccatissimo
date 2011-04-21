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

package net.sf.staccatocommons.io.preferences;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.sf.staccatocommons.restrictions.check.NonNull;

import org.apache.commons.lang.SerializationException;

/**
 * A mirror of the {@link Preferences} API, built on top of it, that supports
 * storing and retrieving "small" objects.
 * 
 * @author flbulgarelli
 */
public interface ObjectPreferences {

  /**
   * Saves an Object in the backing Preferences system, serializing it to either
   * a binary o character stream, depending on the implementation. Only "small"
   * objects should be saved, that is, objects with as few attributes as
   * possible, which must be "small" too, due to backing store system space
   * limitations.
   * 
   * @param key
   *          the key of the object to store
   * @param value
   *          the object that will be stored either using a binary or character
   *          representation. More specific sizes must be defined by
   *          implementors.
   * 
   * @throws SerializationException
   *           if object can not be serialized
   * @see Preferences#putByteArray(String, byte[])
   * @see Preferences#put(String, String)
   * @see Preferences#MAX_VALUE_LENGTH
   */
  void put(@NonNull String key, Object value) throws SerializationException;

  /**
   * 
   * @param <T>
   * @param key
   * @param defaultValue
   * @return the value for that key
   * @throws ClassCastException
   *           if the key exists and contains a valid object, but it can not be
   *           casted to the desired &lt;T&gt;
   */
  <T> T get(@NonNull String key, T defaultValue);

  // <T> T getStrict(String key, T defaultValue);

  /**
   * Refer too {@link Preferences#flush()}
   * 
   * @throws BackingStoreException
   *           if underlying {@link Preferences} throws it when flushing
   */
  void flush() throws BackingStoreException;

  /**
   * @throws BackingStoreException
   *           if underlying {@link Preferences} throws it when synchronizing
   * @see {@link Preferences#sync()}
   */
  void sync() throws BackingStoreException;

  /**
   * 
   * @param key
   *          the key to remove
   * @see {@link Preferences#remove(String)}
   */
  public void remove(String key);

  /**
   * <p>
   * The actual preferences node that is backing objects storing implementation.
   * </p>
   * <p>
   * Clients that want to store {@link String}s, byte[] and primitives using
   * {@link ObjectPreferences} must never use the {@link #put(String, Object)}
   * method, but the specific messages of the preferences node, for example:
   * </p>
   * 
   * <pre>
   * objectPrefs.getNode().put(&quot;Hello&quot;, &quot;World&quot;);
   * </pre>
   * 
   * @return the {@link Preferences} node this {@link ObjectPreferences} sits on
   *         top
   */
  @NonNull
  Preferences getNode();

  // TODO add specific listener
}