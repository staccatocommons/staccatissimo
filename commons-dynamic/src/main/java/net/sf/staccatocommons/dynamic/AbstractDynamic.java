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
package net.sf.staccatocommons.dynamic;

import java.lang.reflect.Method;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.dynamic.internal.PrimitiveWrappers;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

import org.apache.commons.proxy.Invoker;
import org.apache.commons.proxy.exception.ProxyFactoryException;
import org.apache.commons.proxy.factory.javassist.JavassistProxyFactory;
import org.apache.commons.proxy.invoker.NullInvoker;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractDynamic implements Dynamic {

  /**
   * 
   */
  protected static final JavassistProxyFactory PROXY_FACTORY = new JavassistProxyFactory();
  protected static final NullInvoker NULL_INVOKER = new NullInvoker();
  private static final Object[] EMPTY_ARRAY = new Object[0];

  public final Dynamic $(String selector, Object... args) {
    return chainingSend(selector, args);
  }

  @NonNull
  @ForceRestrictions
  public final <T> Thunk<T> delayedSend(@NonNull final String selector, @NonNull final Object... args) {
    return new Thunk<T>() {
      public T value() {
        return send(selector, args);
      }
    };
  }

  @NonNull
  @ForceRestrictions
  public final <T> T as(@NonNull Class<T> clazz) {
    if (valueIsInstanceOf(clazz)) {
      return (T) value();
    }
    return cast(clazz, getInvoker());
  }

  @NonNull
  @ForceRestrictions
  public final <T> T chainingAs(@NonNull Class<T> clazz) {
    if (valueIsInstanceOf(clazz)) {
      return (T) value();
    }
    return cast(clazz, getDynamicInvoker());
  }

  private <T> T cast(Class<T> clazz, Invoker invoker) {
    return (T) PROXY_FACTORY.createInvokerProxy(invoker, new Class[] { clazz });
  }

  protected boolean valueIsInstanceOf(Class<?> clazz) {
    return clazz.isAssignableFrom(value().getClass())
      || PrimitiveWrappers.isPrimitiveWrapperFor(clazz, value().getClass());
  }

  protected Invoker getInvoker() {
    return new Invoker() {
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = send(method.getName(), args != null ? args : EMPTY_ARRAY);
        return result != null ? result : NULL_INVOKER.invoke(proxy, method, args);
      }
    };
  }

  private Invoker getDynamicInvoker() {
    return new Invoker() {
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
          return chainingSend(method.getName(), args != null ? args : EMPTY_ARRAY).chainingAs(method.getReturnType());
        } catch (ProxyFactoryException e) {
          return NULL_INVOKER.invoke(proxy, method, args);
        }
      }
    };
  }

}
