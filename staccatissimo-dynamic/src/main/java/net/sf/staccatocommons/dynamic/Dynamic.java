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


package net.sf.staccatocommons.dynamic;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Dynamic}s are wrapper objects that are capable of receiving any
 * message, and determine on runtime which method of the wrapped objecy will be
 * actually evaluated.
 * 
 * If there is no method matching the message, errors will not occur in compile
 * time, but in runtime - a {@link MessageNotUnderstoodException} will be thrown
 * 
 * @author flbulgarelli
 * 
 */
public interface Dynamic extends Thunk<Object> {

  /**
   * Sends a message to this {@link Dynamic}, and returns the message result
   * 
   * If there is no method matching for the given selector and argument count
   * and types, the message is said to not being understood and a
   * {@link MessageNotUnderstoodException} is thrown
   * 
   * If there exists more than one method matching the given selector and
   * arguments count and types, Dynamic makes no assumptions about which one
   * will be evaluated.
   * 
   * @param <T>
   * @param selector
   *          the message name
   * @param args
   *          the message arguments
   * @return the message result
   * @throws MessageNotUnderstoodException
   *           if this dynamic object does not understand the given message
   * @throws MethodEvaluationException
   *           if a matching method exists, but its evaluation threw a an
   *           exception. The original exception is its cause
   */
  <T> T send(@NonNull final String selector, @NonNull final Object... args);

  /**
   * Sends a message to this {@link Dynamic}, as defined by
   * {@link #send(String, Object...)}, and returns the message result wrapped as
   * a dynamic, or <code>Dynamics.null_()</code>, if the result was
   * <code>null</code>.
   * 
   * If the message is not understood, instead of throwing an exception, this
   * method returns the null dynamic.
   * 
   * This method makes chaining dynamic methods easier. Example:
   * 
   * <pre>
   * Dynamics.from(anObject).$(&quot;getFoo&quot;).$(&quot;getBar&quot;).$(&quot;getBaz&quot;).value()
   * </pre>
   * 
   * This will return the messages chain result, or null, if any message of the
   * chain returned null or was not understood
   * 
   * @param selector
   * @param args
   * @return the null dynamic if the message was not understood or if answered
   *         <code>null</code>. The message result, wrapped as a Dynamic,
   *         otherwise.
   * @throws MethodEvaluationException
   *           if a matching method exists, but its evaluation threw a an
   *           exception. The original exception is its cause
   * @see Dynamics#null_()
   */
  @NonNull
  Dynamic chainingSend(@NonNull String selector, @NonNull Object... args);

  /**
   * Synonym for {@link #chainingSend(String, Object...)}
   * 
   * @param selector
   * @param args
   * @return {@link #chainingSend(String, Object...)}
   */
  @NonNull
  Dynamic $(@NonNull String selector, @NonNull Object... args);

  /**
   * Asynchronously sends a message to this dynamic object by returning a thunk
   * that, when evaluated, performs the actual message passing
   * 
   * @param <T>
   * @param selector
   *          the message selector
   * @param args
   *          the message arguments
   * @return a thunk that, when evaluted, will send the given message
   */
  @NonNull
  <T> Thunk<T> delayedSend(@NonNull final String selector, @NonNull final Object... args);

  /**
   * "casts" this dynamic to the desired type, by returning the wrapped value,
   * if it can be casted to the given class, or by returning a proxy that
   * forwards messages to {@link #send(String, Object...)}, otherwise.
   * 
   * Methods of the proxy result will throw {@link ClassCastException} when the
   * actual returned value of the dynamic can not be cast to the returned value
   * of the method, and {@link NullPointerException} if null is returned when a
   * primitive return type is expected
   * 
   * @param <T>
   * @param clazz
   * @return a new proxy of the given type, that forwards messages to this
   *         {@link Dynamic}
   */
  @NonNull
  <T> T as(@NonNull Class<T> clazz);

  /**
   * "casts" this dynamic to the desired type, by returning the wrapped value,
   * if it can be casted to the given class, or by returning a proxy that
   * forwards messages to {@link #chainingSend(String, Object...)}, casting with
   * {@link #chainingAs(Class)} the result to the desired method return type,
   * otherwise.
   * 
   * Methods will throw {@link NullPointerException} when null is returned when
   * a primitive return type is expected
   * 
   * @param <T>
   * @param clazz
   * @return a new proxy of the given type, that forwards messages to this
   *         {@link Dynamic}
   */
  @NonNull
  <T> T chainingAs(@NonNull Class<T> clazz);

  /**
   * The wrapped value
   */
  Object value();

}