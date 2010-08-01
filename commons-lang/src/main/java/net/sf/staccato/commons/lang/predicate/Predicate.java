/*
 Copyright (c) 2010, The Staccato-Commons Team   
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.predicate;

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.Executable2;
import net.sf.staccato.commons.lang.Executable3;
import net.sf.staccato.commons.lang.block.Block;
import net.sf.staccato.commons.lang.block.Block2;
import net.sf.staccato.commons.lang.block.Block3;
import net.sf.staccato.commons.lang.function.Function;

/**
 * <p>
 * A {@link Predicate} is a {@link Function} that can return only
 * {@link Boolean}s and is also {@link Evaluable}.
 * </p>
 * <p>
 * Predicates in addition understand the basic boolean logic messages
 * {@link #not()}, {@link #and(Predicate)} and {@link #or(Predicate)} that
 * perform those operations on evaluation result.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of argument to evaluate
 */
public abstract class Predicate<T> extends Function<T, Boolean> implements
	Evaluable<T> {

	/**
	 * Evaluates the argument using {@link #eval(Object)}, and returns it as
	 * {@link Boolean}
	 */
	public Boolean apply(T argument) {
		return eval(argument);
	}

	@Override
	public abstract boolean eval(T argument);

	/**
	 * @return a {@link Predicate} that negates this {@link Predicate}'s result.
	 *         Non Null.
	 */
	public Predicate<T> not() {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return !Predicate.this.eval(argument);
			}
		};
	}

	/**
	 * Returns a predicate that, when evaluated, performs a short-circuit or
	 * between this {@link Predicate}'s {@link #eval(Object)} and other
	 * 
	 * @param other
	 *          another {@link Evaluable}. Non null.
	 * @return A new predicate that performs the short circuited or between this
	 *         and other when evaluated. Non Null
	 */
	public Predicate<T> or(final Evaluable<T> other) {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return Predicate.this.eval(argument) || other.eval(argument);
			}
		};
	}

	public Predicate<T> and(final Evaluable<T> other) {
		return new Predicate<T>() {
			public boolean eval(T argument) {
				return Predicate.this.eval(argument) && other.eval(argument);
			}
		};
	}

	public Executable<T> whileTrue(final Executable<T> aBlock) {
		return new Block<T>() {
			public void exec(T argument) {
				while (eval(argument))
					aBlock.exec(argument);
			}
		};
	}

	public <T2> Block2<T, T2> whileTrue(final Executable2<T, T2> aBlock) {
		return new Block2<T, T2>() {
			public void exec(T argument1, T2 argument2) {
				while (eval(argument1))
					aBlock.exec(argument1, argument2);
			}
		};
	}

	public <T2, T3> Block3<T, T2, T3> whileTrue(
		final Executable3<T, T2, T3> aBlock) {
		return new Block3<T, T2, T3>() {
			public void exec(T argument1, T2 argument2, T3 argument3) {
				while (eval(argument1))
					aBlock.exec(argument1, argument2, argument3);
			}
		};
	}

	public Executable<T> ifTrue(final Executable<T> aBlock) {
		return new Block<T>() {
			public void exec(T argument) {
				if (eval(argument))
					aBlock.exec(argument);
			}
		};
	}

	public <T2> Block2<T, T2> ifTrue(final Executable2<T, T2> aBlock) {
		return new Block2<T, T2>() {
			public void exec(T argument1, T2 argument2) {
				if (eval(argument1))
					aBlock.exec(argument1, argument2);
			}
		};
	}

	public <T2, T3> Block3<T, T2, T3> ifTrue(final Executable3<T, T2, T3> aBlock) {
		return new Block3<T, T2, T3>() {
			public void exec(T argument1, T2 argument2, T3 argument3) {
				if (eval(argument1))
					aBlock.exec(argument1, argument2, argument3);
			}
		};
	}

	public Applicable<T, T> ifTrue(
		final Applicable<? super T, ? extends T> aFunction) {
		return new Function<T, T>() {
			public T apply(T arg) {
				return Predicate.this.eval(arg) ? aFunction.apply(arg) : arg;
			}
		};
	}

}
