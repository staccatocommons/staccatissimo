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
package net.sf.staccatocommons.dynamic.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author flbulgarelli
 * 
 */
public class Methods {

  /***/
  public static Method findMethod(final Class initialClazz, final String selector,
    final Class<?>[] argsTypes) {
    for (Class<?> c = initialClazz; c != null; c = c.getSuperclass()) {
      for (Method m : c.getDeclaredMethods()) {
        if (isDesiredMethod(selector, m, argsTypes)) {
          return m;
        }
      }
    }
    return null;
  }

  /***/
  public static Constructor findConstructor(final Class clazz, final Class<?>[] argsTypes) {
    for (Constructor constructor : clazz.getConstructors()) {
      if (isDesiredConstructor(constructor, argsTypes)) {
        return constructor;
      }
    }
    return null;
  }

  private static boolean isDesiredMethod(final String selector, Method method,
    final Class<?>[] argTypes) {
    return Modifier.isPublic(method.getModifiers()) //
      && method.getParameterTypes().length == argTypes.length //
      && method.getName().equals(selector) //
      && argTypesMatch(argTypes, method.getParameterTypes()); //
  }

  private static boolean isDesiredConstructor(Constructor constructor, final Class<?>[] argTypes) {
    return Modifier.isPublic(constructor.getModifiers()) //
      && constructor.getParameterTypes().length == argTypes.length //
      && argTypesMatch(argTypes, constructor.getParameterTypes()); //
  }

  private static boolean argTypesMatch(Class<?>[] passedArgTypes, Class<?>[] actualArgTypes) {
    for (int i = 0; i < passedArgTypes.length; i++) {
      Class<?> actual = actualArgTypes[i];
      Class<?> passed = passedArgTypes[i];
      if (!(actual.isAssignableFrom(passed) || PrimitiveWrappers.isPrimitiveWrapperFor(
        actual,
        passed)))
        return false;
    }
    return true;
  }

  /***/
  public static Class<?>[] getArgTypes(Object[] args) {
    int arguments = args.length;
    Class<?>[] parameterTypes = new Class[arguments];
    for (int i = 0; i < arguments; i++) {
      parameterTypes[i] = args[i].getClass();
    }
    return parameterTypes;
  }

}
