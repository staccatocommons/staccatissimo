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

import static net.sf.staccatocommons.dynamic.internal.Methods.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import net.sf.staccatocommons.dynamic.AbstractDynamic;
import net.sf.staccatocommons.dynamic.Dynamic;
import net.sf.staccatocommons.dynamic.Dynamics;
import net.sf.staccatocommons.dynamic.MessageNotUnderstoodException;
import net.sf.staccatocommons.dynamic.MethodEvaluationException;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.EnforceRestrictions;

/**
 * @author flbulgarelli
 */
public final class ReflectiveDynamic extends AbstractDynamic {

  private final Object target;
  private static final Map<MethodDescriptor, Method> CACHE = Collections
    .synchronizedMap(new WeakHashMap<MethodDescriptor, Method>());

  /**
   * Creates a new {@link ReflectiveDynamic}
   * 
   * @param target
   *          the object to wrap
   */
  public ReflectiveDynamic(@NonNull Object target) {
    this.target = target;
  }

  @EnforceRestrictions
  public <T> T send(@NonNull final String selector, @NonNull final Object... args) {
    MethodDescriptor descriptor = newDescriptor(selector, getArgTypes(args));
    Method method = getMethod(descriptor);
    if (method != null) {
      return invoke(method, args);
    }
    throw new MessageNotUnderstoodException(descriptor);
  }

  public Dynamic chainingSend(String selector, Object... args) {
    Method method = getMethod(newDescriptor(selector, getArgTypes(args)));
    if (method != null) {
      return Dynamics.nullSafeFrom(invoke(method, args));
    }
    return Dynamics.null_();
  }

  /**
   * Answers the object wrapped by this dynamic
   */
  public Object value() {
    return target;
  }

  private Method getMethod(MethodDescriptor key) {
    Method method = CACHE.get(key);
    if (method != null) {
      return method;
    }
    method = findMethod(target.getClass(), key.getSelector(), key.getArgTypes());
    if (method == null)
      return null;
    CACHE.put(key, method);
    return method;
  }

  private MethodDescriptor newDescriptor(final String selector, final Class<?>... argTypes) {
    MethodDescriptor key = new MethodDescriptor(target.getClass(), selector, argTypes);
    return key;
  }

  private <T> T invoke(Method method, final Object... args) {
    try {
      return (T) method.invoke(target, args);
    } catch (IllegalAccessException e) {
      throw new MethodEvaluationException(e);
    } catch (InvocationTargetException e) {
      throw new MethodEvaluationException(e.getCause());
    }
  }

}
