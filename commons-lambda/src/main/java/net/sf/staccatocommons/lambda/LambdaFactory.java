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

import static net.sf.staccatocommons.lang.number.NumberTypes.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import net.sf.staccatocommons.check.Validate;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.function.AbstractFunction3;
import net.sf.staccatocommons.lang.number.NumberTypeAware;
import net.sf.staccatocommons.lang.number.internal.NumberTypeFunction;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

import org.apache.commons.proxy.ProxyFactory;
import org.apache.commons.proxy.invoker.NullInvoker;

/**
 * 
 * {@link LambdaFactory}s are objects capable of creating simple
 * {@link Function}s and {@link Predicate}s of different arities in a DSL style,
 * instead of implementing anonymous classes.
 * 
 * @author flbulgarelli
 * 
 */
public final class LambdaFactory {

	private static final Validate<IllegalStateException> state = Validate
		.throwing(IllegalStateException.class);
	private final ProxyFactory proxyFactory;
	private final Handler handler = new Handler();
	private boolean firstStep = false;

	/**
	 * Creates a new {@link LambdaFactory}
	 */
	public LambdaFactory(@NonNull ProxyFactory proxyFactory) {
		this.proxyFactory = proxyFactory;
	}

	private final class Handler extends NullInvoker {
		private Method method;
		private Object[] args;

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			this.method = method;
			this.args = args;
			return super.invoke(proxy, method, args);
		}

		public Method getMethod() {
			state.that(firstStep, "Wrong invocation order");
			firstStep = false;
			return method;
		}

		public Object[] getArgs() {
			return args;
		}

		public Object[] getArgsCopy() {
			return Arrays.copyOf(args, args.length);
		}
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
	 */
	@ForceRestrictions
	@NonNull
	public <A> A $(@NonNull Class<A> clazz) {
		state.that(!firstStep, "Wrong invocation order");
		A stub = (A) proxyFactory.createInvokerProxy(handler, new Class[] { clazz });
		firstStep = true;
		return stub;
	}

	/**
	 * Answers a {@link Predicate} that when evaluated sends to its argument the
	 * message previously sent to the last stubbed type. Refer to the use cases
	 * described in {@link Lambda}
	 * 
	 * @param returnType
	 *          meaningless, this argument is simply ignored
	 * @return a new {@link AbstractPredicate}
	 * @see Lambda
	 */
	@NonNull
	public Predicate<Object> lambda(boolean returnType) {
		final Method method = handler.getMethod();
		final Object[] args = handler.getArgs();
		return new AbstractPredicate<Object>() {
			public boolean eval(Object argument) {
				return invoke(method, argument, args);
			}
		};
	}

	/**
	 * Answers a {@link Function} that when applied sends to its argument the
	 * message previously sent to the last stubbed type. Refer to the use cases
	 * described in {@link Lambda}
	 * 
	 * @param returnType
	 *          meaningless, this argument is simply ignored
	 * @return a new {@link Function}
	 * @see Lambda
	 */
	@NonNull
	public <A> Function<Object, A> lambda(A returnType) {
		final Method method = handler.getMethod();
		final Object[] args = handler.getArgs();
		return new AbstractFunction<Object, A>() {
			public A apply(Object receptor) {
				return invoke(method, receptor, args);
			}
		};
	}

	/**
	 * Answers a {@link Function} that when applied sends to its argument the
	 * message previously sent to the last stubbed type. The returned function is
	 * {@link NumberTypeAware}
	 * 
	 * Refer to the use cases described in {@link Lambda}
	 * 
	 * @param returnType
	 *          meaningless, this argument is simply ignored
	 * @return a new {@link Function}
	 * @see Lambda
	 */
	@NonNull
	public Function<Object, Integer> lambda(Integer returnType) {
		return numberFunction(integer());
	}

	/**
	 * Answers a {@link Function} that when applied sends to its argument the
	 * message previously sent to the last stubbed type. The returned function is
	 * {@link NumberTypeAware}
	 * 
	 * Refer to the use cases described in {@link Lambda}
	 * 
	 * @param returnType
	 *          meaningless, this argument is simply ignored
	 * @return a new {@link Function}
	 * @see Lambda
	 */
	@NonNull
	public Function<Object, BigDecimal> lambda(BigDecimal returnType) {
		return numberFunction(bigDecimal());
	}

	/**
	 * Answers a {@link Function} that when applied sends to its argument the
	 * message previously sent to the last stubbed type. The returned function is
	 * {@link NumberTypeAware}
	 * 
	 * Refer to the use cases described in {@link Lambda}
	 * 
	 * @param returnType
	 *          meaningless, this argument is simply ignored
	 * @return a new {@link Function}
	 * @see Lambda
	 */
	@NonNull
	public Function<Object, BigInteger> lambda(BigInteger returnType) {
		return numberFunction(bigInteger());
	}

	private <A> Function<Object, A> numberFunction(NumberType<A> type) {
		final Method method = handler.getMethod();
		final Object[] args = handler.getArgs();
		return new NumberTypeFunction<Object, A>(type) {
			public A apply(Object receptor) {
				return invoke(method, receptor, args);
			}
		};
	}

	public <A> Function2<Object, Object, A> lambda2(A returnType) {
		final Method method = handler.getMethod();
		final Object[] args = handler.getArgsCopy();
		args[0] = null;
		return new AbstractFunction2<Object, Object, A>() {
			public A apply(Object arg0, Object arg1) {
				args[0] = arg1;
				return invoke(method, arg0, args);
			}
		};
	}

	public <A> Function3<Object, Object, Object, A> lambda3(A returnType) {
		final Method method = handler.getMethod();
		final Object[] args = handler.getArgsCopy();
		args[0] = null;
		args[1] = null;
		return new AbstractFunction3<Object, Object, Object, A>() {
			public A apply(Object arg0, Object arg1, Object arg2) {
				args[0] = arg1;
				args[1] = arg2;
				return invoke(method, arg0, args);
			}
		};
	}

	private <T> T invoke(final Method method, Object receptor, Object[] args) {
		try {
			return (T) method.invoke(receptor, args);
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

}
