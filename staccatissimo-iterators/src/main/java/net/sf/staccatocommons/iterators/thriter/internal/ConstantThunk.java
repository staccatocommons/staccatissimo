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


package net.sf.staccatocommons.iterators.thriter.internal;

import java.io.Serializable;

import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 * 
 */
public final class ConstantThunk<T> implements Serializable, Thunk<T> {

  private static final long serialVersionUID = -7769276251688297460L;

  private final T value;

  /**
   * 
   * Creates a new {@link ConstantThunk}
   * 
   * @param value
   *          the value to provide
   */
  public ConstantThunk(T value) {
    this.value = value;
  }

  public T value() {
    return value;
  }

  public String toString() {
    return "ConstantThunk(" + String.valueOf(value) + ")";
  }

}
