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

package net.sf.staccatocommons.io.serialization.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.staccatocommons.io.serialization.SerializationManager;
import net.sf.staccatocommons.lang.lifecycle.CloseableLifecycle;
import net.sf.staccatocommons.lang.lifecycle.Lifecycle;
import net.sf.staccatocommons.restrictions.check.NonNull;

public abstract class SerializationLifecycle<TargetType extends Closeable, ReturnType> extends
  CloseableLifecycle<TargetType, ReturnType> {

  private final SerializationManager serializationManager;

  /**
   * Creates a new {@link SerializationLifecycle}
   * 
   * @param serializationManager
   */
  public SerializationLifecycle(@NonNull SerializationManager serializationManager) {
    this.serializationManager = serializationManager;
  }

  /**
   * Answers the underlying {@link SerializationManager}
   * 
   * @return the {@link SerializationManager} used by this
   *         {@link SerializationLifecycle}
   */
  @NonNull
  public SerializationManager getSerializationManager() {
    return serializationManager;
  }

  /**
   * A {@link Lifecycle} that serializes a single object using a
   * {@link SerializationManager}
   * 
   * @author flbulgarelli
   */
  public abstract static class Serialize extends SerializationLifecycle<OutputStream, Void> {

    private final Object target;

    public Serialize(@NonNull SerializationManager serializationManager, Object target) {
      super(serializationManager);
      this.target = target;
    }

    @Override
    public void doVoidWork(@NonNull OutputStream output) throws IOException {
      getSerializationManager().serialize(target, output);
    }
  }

  /**
   * A {@link Lifecycle} that deserializes a single object of type {@code A}
   * using a {@link SerializationManager}
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   */
  public abstract static class Deserialize<A> extends SerializationLifecycle<InputStream, A> {

    public Deserialize(@NonNull SerializationManager serializationManager) {
      super(serializationManager);
    }

    @Override
    protected A doWork(@NonNull InputStream resource) throws Exception {
      return getSerializationManager().deserialize(resource);
    }
  }

}
