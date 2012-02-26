/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.dynamic.internal;

import java.lang.reflect.Constructor;

import net.sf.staccatocommons.dynamic.InstantiationFailedException;

/**
 * @author flbulgarelli
 * @since 2.2
 */
public class Classes {

  /**
   * Creates a new instance for the given classname
   */
  public static Object newInstance(String classname) { 
    //TODO remove and add from, which returns a class
    Class<?> clazz;
    try {
      clazz = Class.forName(classname);
    } catch (Exception e) {
      throw new InstantiationFailedException(e);
    }
    return newInstance(clazz);
  }

  /**
   * Creates a new instance for the given clazz
   */
  public static Object newInstance(Class<?> clazz) {
    try {
      return clazz.newInstance();
    } catch (Exception e) {
      throw new InstantiationFailedException(e);
    }
  }

  /**
   * Creates a new instance for the given class and argument
   */
  public static Object newInstance(Class<?> clazz, Object... args) {
    Constructor constructor = Methods.findConstructor(clazz, Methods.getArgTypes(args));
    // TODO refactor message and use MethodDescriptor
    if (constructor == null)
      throw new InstantiationFailedException("There is no suitable constructor in class " + clazz + " for arguments");
    try {
      Object newInstance = constructor.newInstance(args);
      return newInstance;
    } catch (Exception e) {
      throw new InstantiationFailedException(e);
    }
  }
}
