/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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

package net.sf.staccatocommons.lang.predicate;

import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * <p>
 * A {@link AbstractPredicate} is an abstract {@link Evaluable}.
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
public abstract class AbstractPredicate<T> implements Predicate<T> {

	@Override
	public abstract boolean eval(@NonNull T argument);

	public Boolean apply(T arg) {
		return eval(arg);
	}

	/**
	 * @return a {@link AbstractPredicate} that negates this
	 *         {@link AbstractPredicate}'s result. Non Null.
	 */
	@NonNull
	public Predicate<T> not() {
		final class Not extends AbstractPredicate<T> {
			public boolean eval(T argument) {
				return !AbstractPredicate.this.eval(argument);
			}

			@Override
			public AbstractPredicate<T> not() {
				return AbstractPredicate.this;
			}
		}
		return new Not();
	}

	/**
	 * Returns a predicate that, performs a short-circuit logical-or between this
	 * {@link AbstractPredicate}'s {@link #eval(Object)} and other
	 * 
	 * @param other
	 *          another {@link Evaluable}. Non null.
	 * @return A new predicate that performs the short circuited or between this
	 *         and other when evaluated. Non Null
	 */
	@NonNull
	public Predicate<T> or(@NonNull final Evaluable<? super T> other) {
		final class Or extends AbstractPredicate<T> {
			public boolean eval(T argument) {
				return AbstractPredicate.this.eval(argument) || other.eval(argument);
			}
		}
		return new Or();
	}

	/**
	 * Returns a predicate that performs a short-circuit logical-and between this
	 * {@link AbstractPredicate}'s {@link #eval(Object)} and other
	 * 
	 * @param other
	 *          another {@link Evaluable}. Non null.
	 * @return A new predicate that performs the short circuited logical-and
	 *         between this and other when evaluated. Non Null
	 */
	@NonNull
	public Predicate<T> and(@NonNull final Evaluable<? super T> other) {
		final class And extends AbstractPredicate<T> {
			public boolean eval(T argument) {
				return AbstractPredicate.this.eval(argument) && other.eval(argument);
			}
		}
		return new And();
	}

	public Predicate<T> nullSafe() {
		return Predicates.<T> null_().or(this);
	}

	public String toString() {
		return "Predicate";
	}

}
