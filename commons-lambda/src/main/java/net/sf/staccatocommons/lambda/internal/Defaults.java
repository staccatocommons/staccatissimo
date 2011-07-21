/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lambda.internal;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author flbulgarelli
 * 
 */
public class Defaults {

  @SuppressWarnings("serial")
  private static final Map<Class<?>, Object> MAP = new IdentityHashMap<Class<?>, Object>(16) {
    {
      put(Boolean.TYPE, false);
      put(Boolean.class, false);
      put(Character.TYPE, '\0');
      put(Character.class, '\0');
      put(Byte.TYPE, (byte) 0);
      put(Byte.class, (byte) 0);
      put(Short.TYPE, (short) 0);
      put(Short.class, (short) 0);
      put(Integer.TYPE, 0);
      put(Integer.class, 0);
      put(Long.TYPE, 0L);
      put(Long.class, 0L);
      put(Float.TYPE, 0f);
      put(Float.class, 0f);
      put(Double.TYPE, 0d);
      put(Double.class, 0d);
    }
  };

  /**
   * @param clazz
   * @return the default value for that class
   */
  public static <A> A getDefault(Class<A> clazz) {
    return (A) MAP.get(clazz);
  }

}
