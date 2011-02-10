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
package net.sf.staccatocommons.lang.predicate;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * <p>
 * A {@link Predicate} is an abstract {@link Evaluable}.
 * </p>
 * <p>
 * Predicates in addition understand the basic boolean logic messages
 * {@link #not()}, {@link #and(Evaluable)} and {@link #or(Evaluable)} that
 * perform those operations on evaluation result.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of argument to evaluate
 */
public abstract class Predicate<T> implements Evaluable<T>, Applicable<T, Boolean> {

	@Override
	public abstract boolean eval(@NonNull T argument);

	public Boolean apply(T arg) {
		return eval(arg);
	}

	/**
	 * @return a {@link Predicate} that negates this {@link Predicate}'s result.
	 *         Non Null.
	 */
	@NonNull
	public Predicate<T> not() {
		final class Not extends Predicate<T> {
			public boolean eval(T argument) {
				return !Predicate.this.eval(argument);
			}

			@Override
			public Predicate<T> not() {
				return Predicate.this;
			}
		}
		return new Not();
	}

	/**
	 * Returns a predicate that, performs a short-circuit logical-or between this
	 * {@link Predicate}'s {@link #eval(Object)} and other
	 * 
	 * @param other
	 *          another {@link Evaluable}. Non null.
	 * @return A new predicate that performs the short circuited or between this
	 *         and other when evaluated. Non Null
	 */
	@NonNull
	public Predicate<T> or(@NonNull final Evaluable<? super T> other) {
		final class Or extends Predicate<T> {
			public boolean eval(T argument) {
				return Predicate.this.eval(argument) || other.eval(argument);
			}
		}
		return new Or();
	}

	/**
	 * Returns a predicate that performs a short-circuit logical-and between this
	 * {@link Predicate}'s {@link #eval(Object)} and other
	 * 
	 * @param other
	 *          another {@link Evaluable}. Non null.
	 * @return A new predicate that performs the short circuited logical-and
	 *         between this and other when evaluated. Non Null
	 */
	@NonNull
	public Predicate<T> and(@NonNull final Evaluable<? super T> other) {
		final class And extends Predicate<T> {
			public boolean eval(T argument) {
				return Predicate.this.eval(argument) && other.eval(argument);
			}
		}
		return new And();
	}

	public String toString() {
		return "Predicate";
	}

}
