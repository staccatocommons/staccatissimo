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
package net.sf.staccatocommons.defs.predicate;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.NullSafeAware;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public interface Predicate<A> extends Evaluable<A>, Applicable<A, Boolean>,
	NullSafeAware<Predicate<A>> {

	/**
	 * Negates this {@link Predicate}
	 * 
	 * @return a {@link Predicate} that negates this {@link Predicate}'s result.
	 */
	@NonNull
	Predicate<A> not();

	/**
	 * Returns a predicate that, performs a short-circuit logical-or between this
	 * {@link Predicate}'s {@link #eval(Object)} and other
	 * 
	 * @param other
	 *          another {@link Evaluable}. Non null.
	 * @return A new predicate that performs the short circuited or between this
	 *         and other when evaluated.
	 */
	@NonNull
	public Predicate<A> or(@NonNull final Evaluable<? super A> other);

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
	Predicate<A> and(@NonNull final Evaluable<? super A> other);
}