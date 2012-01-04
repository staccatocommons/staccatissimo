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

package net.sf.staccatocommons.io.serialization.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.sf.staccatocommons.io.internal.lifecycle.CloseableLifecycle;
import net.sf.staccatocommons.io.internal.lifecycle.Lifecycle;
import net.sf.staccatocommons.io.serialization.CharSerializationManager;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link CharSerializationLifecycle} are abstract {@link Lifecycle}s that open
 * a writer or reader, serialize or deserialize an object, and close the open
 * resource.
 * <p>
 * Typical usage of the serialization lifecycle is the following:
 * </p>
 * 
 * <pre>
 * new CharSerializationLifecycle.Serialize(aSerializationManager, anObject) {
 *  public Writer initialize() throws IOException {
 *    return ...create your writer here...;
 *  }
 * }.value();
 * </pre>
 * 
 * <p>
 * On the other hand, typical usage is the deserialization lifecycle is the
 * following:
 * </p>
 * 
 * <pre>
 * T result = new CharSerializationLifecycle.Deserialize&lt;T&gt;(aSerializarionManager) {
 *  public Reader initialize() throws Exception {
 *   return ..create your reader hear...;
 *  }
 * }.value();
 * </pre>
 * 
 * @author flbulgarelli
 * 
 * @param <TargetType>
 * @param <ReturnType>
 */
public abstract class CharSerializationLifecycle<TargetType extends Closeable, ReturnType> extends
  CloseableLifecycle<TargetType, ReturnType> {

  private CharSerializationManager charSerializationManager;

  /**
   * Creates a new {@link CharSerializationLifecycle}
   * 
   * @param charSerializationManager
   *          the {@link CharSerializationManager} used to serialize or
   *          deserialize an object as part of this {@link Lifecycle}
   */
  public CharSerializationLifecycle(@NonNull CharSerializationManager charSerializationManager) {
    this.charSerializationManager = charSerializationManager;
  }

  /**
   * Answers the underlying {@link CharSerializationManager}
   */
  public CharSerializationManager getSerializationManager() {
    return charSerializationManager;
  }

  /**
   * A {@link Lifecycle} that serializes a single object using a
   * {@link CharSerializationManager}
   * 
   * @author flbulgarelli
   */
  public abstract static class Serialize extends CharSerializationLifecycle<Writer, Void> {

    private final Object target;

    /**
     * Creates a new {@link CharSerializationLifecycle.Serialize}
     * 
     * @param serializationManager
     *          the {@link CharSerializationManager} used for performing the
     *          serialization
     * @param target
     *          the object to serialize
     */
    public Serialize(@NonNull CharSerializationManager serializationManager, Object target) {
      super(serializationManager);
      this.target = target;
    }

    @Override
    public void doVoidWork(Writer output) throws IOException {
      getSerializationManager().serialize(target, output);
    }
  }

  /**
   * A {@link Lifecycle} that deserializes a single object of type {@code A}
   * using a {@link CharSerializationManager}
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   */
  public abstract static class Deserialize<A> extends CharSerializationLifecycle<Reader, A> {

    /**
     * Creates a new {@link CharSerializationLifecycle.Deserialize}
     * 
     * @param serializationManager
     *          the {@link CharSerializationManager} used to deserialize the
     *          object
     */
    public Deserialize(@NonNull CharSerializationManager serializationManager) {
      super(serializationManager);
    }

    @Override
    public A doWork(Reader input) throws IOException {
      return getSerializationManager().deserialize(input);
    }
  }

}
