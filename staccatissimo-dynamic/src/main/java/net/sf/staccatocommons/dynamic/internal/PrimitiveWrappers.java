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


package net.sf.staccatocommons.dynamic.internal;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author flbulgarelli
 * 
 */
public class PrimitiveWrappers {

  @SuppressWarnings("serial")
  private static final Map<Class<?>, Class<?>> MAP = new IdentityHashMap<Class<?>, Class<?>>(8) {
    {
      put(Integer.TYPE, Integer.class);
      put(Long.TYPE, Long.class);
      put(Character.TYPE, Character.class);
      put(Boolean.TYPE, Boolean.class);
      put(Short.TYPE, Short.class);
      put(Byte.TYPE, Byte.class);
      put(Double.TYPE, Double.class);
      put(Float.TYPE, Float.class);
    }
  };

  /**
   * @param type
   * @return the wrapper for the given primitive
   */
  public static Class<?> getWrapperForType(Class<?> type) {
    return MAP.get(type);
  }

  /** If a class is the wrapper of the another */
  public static boolean isPrimitiveWrapperFor(Class<?> original, Class<?> wrapper) {
    return original.isPrimitive() && PrimitiveWrappers.getWrapperForType(original) == wrapper;
  }

}
