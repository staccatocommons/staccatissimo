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
package net.sf.staccatocommons.lambda;

import java.util.Collection;

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lambda.internal.Defaults;

import org.apache.commons.proxy.ProxyFactory;
import org.apache.commons.proxy.factory.javassist.JavassistProxyFactory;

/**
 * An utility class for accessing in a shared, thread safe {@link LambdaFactory}
 * , or creating standalone {@link LambdaFactory}s
 * 
 * {@link Lambda} and {@link LambdaFactory} a DSL for instantiating simple
 * {@link Function}s and {@link Predicate}s. There are two manners of using
 * those classes:
 * 
 * <h3>Using shared factories</h3>
 * 
 * This is the simplest use case. Just it needs to static-import {@link Lambda},
 * and combine {@link #$(Class)} with {@link #lambda(Object)} and variants, in
 * the form <code>lambdaVariant($(ArgumentType).message(args...))</code> . For
 * example:
 * 
 * <pre>
 * lambda($(Collection.class).isEmpty());
 * </pre>
 * 
 * This creates a new {@link Predicate} that takes a {@link Collection} argument
 * and answers if it is empty. It is equivalent to the more verbose:
 * 
 * <pre>
 * new Predicate&lt;Collection&gt;() {
 *   public boolean eval(Collection arg) {
 *     return arg.isEmpty();
 *   }
 * };
 * </pre>
 * 
 * Although this syntax is quite convenient, accessing the shared
 * {@link LambdaFactory} - hidden by the static-imports - needs a thread local
 * variable to make it thread safe, which may be seen in some contexts like
 * resources waste.
 * 
 * <h3>Using Local factories</h3>
 * 
 * For applications that want to avoid the previously described synchronization,
 * then a better idea is to use local factories instead of a shared one, which
 * looks a little less fancy, though. Local factories works exactly in the same
 * was as shared factories, but need an explicit factory instantiation and
 * reference. For example:
 * 
 * <pre>
 * LambdaFactory l = Lambda.factory();
 * l.lambda(l.$(Collection.class).isEmpty());
 * </pre>
 * 
 * Local factories <strong>must</strong> be declared as local variables.
 * 
 * @see LambdaFactory
 * @author flbulgarelli
 * 
 */
public final class Lambda {

  private static final ThreadLocal<LambdaFactory> SHARED_LAMBDA_FACTORY = new ThreadLocal<LambdaFactory>() {
    protected LambdaFactory initialValue() {
      return factory();
    };
  };

  /**
   * Stubs a type for creating a lambda. The resulting stub
   * <strong>must</strong> receive one and only one message.
   * 
   * Not all {@link LambdaFactory}s can stub all types - ie, stubbing
   * interfaces, concrete classes or final classes. Their stubbing capabilities
   * depend on the {@link ProxyFactory} passed as constructor argument to this
   * {@link LambdaFactory}
   * 
   * @param <A>
   * @param clazz
   *          the type to stub
   * @return a new stub
   * @see net.sf.staccatocommons.lambda.LambdaFactory#$(java.lang.Class)
   */
  public static <A> A $(Class<A> clazz) {
    return getSharedLambdaFactory().$(clazz);
  }

  /**
   * Answers a {@link Function} that when applied sends to its argument the
   * message previously sent to the last stubbed type. Refer to the use cases
   * described in {@link Lambda}
   * 
   * @param returnType
   *          meaningless, this argument is simply ignored
   * @return a new {@link Function}
   * 
   * @see net.sf.staccatocommons.lambda.LambdaFactory#lambda(java.lang.Object)
   */
  public static <A> Function<Object, A> lambda(A returnType) {
    return getSharedLambdaFactory().lambda(returnType);
  }

  /**
   * Answers a {@link Predicate} that when evaluated sends to its argument the
   * message previously sent to the last stubbed type. Refer to the use cases
   * described in {@link Lambda}
   * 
   * @param returnType
   *          meaningless, this argument is simply ignored
   * @return a new {@link Predicate}
   * @see Lambda
   * @see net.sf.staccatocommons.lambda.LambdaFactory#lambda(boolean)
   */
  public static Predicate<Object> lambda(boolean returnType) {
    return getSharedLambdaFactory().lambda(returnType);
  }

  /**
   * Answers a {@link Function2} that when applied sends to its first argument
   * the message previously sent to the last stubbed type, passing its second
   * argument as the first message argument. Refer to the use cases described in
   * {@link Lambda}
   * 
   * @param returnType
   *          meaningless, this argument is simply ignored
   * @return a new {@link Function2}
   * 
   * @see net.sf.staccatocommons.lambda.LambdaFactory#lambda2(java.lang.Object)
   */
  public static <A> Function2<Object, Object, A> lambda2(A returnType) {
    return getSharedLambdaFactory().lambda2(returnType);
  }

  /**
   * Answers a {@link Function3} that when applied sends to its first argument
   * the message previously sent to the last stubbed type, passing its second
   * argument as the first message argument, and its third argument to the
   * second message argument.
   * 
   * Refer to the use cases described in {@link Lambda}
   * 
   * @param returnType
   *          meaningless, this argument is simply ignored
   * @return a new {@link Function3}
   * 
   * @see net.sf.staccatocommons.lambda.LambdaFactory#lambda3(java.lang.Object)
   */
  public static <A> Function3<Object, Object, Object, A> lambda3(A returnType) {
    return getSharedLambdaFactory().lambda3(returnType);
  }

  /**
   * Answers a new {@link LambdaFactory} that is capable of stubbing interfaces
   * and non-final classes
   * 
   * @return a new {@link LambdaFactory}
   */
  public static LambdaFactory factory() {
    return new LambdaFactory(new JavassistProxyFactory());
  }

  private static LambdaFactory getSharedLambdaFactory() {
    return SHARED_LAMBDA_FACTORY.get();
  }

  /**
   * Answers a placeholder for lambdas arguments with arity > 1.
   * 
   * @param <A>
   * @param clazz
   * @return a placeholder for a lambda argument. Its value is opaque to client
   *         code
   */
  public static <A> A _(Class<A> clazz) {
    return Defaults.getDefault(clazz);
  }

  /**
   * Equivalent to _(Object.class)
   */
  public static final Object _ = null;
}
