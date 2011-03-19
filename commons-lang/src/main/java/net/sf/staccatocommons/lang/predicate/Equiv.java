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
package net.sf.staccatocommons.lang.predicate;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.lang.predicate.internal.CompareTest;
import net.sf.staccatocommons.lang.predicate.internal.EqualTest;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

/**
 * Factory class methods for creating common, simple {@link Predicate2} that
 * implement the notion of and equivalence test, that is, a reflexive, symmetric
 * and transitive relation between its arguments.
 * 
 * @author flbulgarelli
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Equivalence_relation">Equivalence-relation</a>
 * @see Object#equals(Object)
 */
public class Equiv {

	private Equiv() {}

	/**
	 * Answers an {@link Predicate2} that performs an equality test between its
	 * arguments, that it returns true if both are equal
	 * 
	 * @param <A>
	 * @return a constant {@link Predicate2} that performs an equality test
	 */
	@NonNull
	@Constant
	public static <A> Predicate2<A, A> equal() {
		return EqualTest.<A> equalTest();
	}

	// TODO add same()

	/**
	 * Answers an {@link Predicate2} that performs an equality test between its
	 * nullable arguments, that it returns true if both are equal or null.
	 * 
	 * @param <A>
	 * @return <code>Equiv.equal().nullSafe()</code>
	 */
	@NonNull
	@Constant
	public static <A> Predicate2<A, A> equalNullSafe() {
		return Equiv.<A> equal().nullSafe();
	}

	/**
	 * Answers an {@link Predicate2} that performs an compare test between its
	 * {@link Comparable} arguments, that it returns
	 * <code>arg0.compareTo(arg1) == 0</code>
	 * 
	 * @param <A>
	 * @return a constant {@link Predicate2} that performs a compare test
	 */
	@NonNull
	@Constant
	public static <A extends Comparable<A>> Predicate2<A, A> compare() {
		return CompareTest.<A> compareTest();
	}

	/**
	 * Answers an {@link Predicate2} that performs an equality test to the result
	 * of applying the given function to its arguments.
	 * 
	 * This is mostly useful then the given function is just a property accesor,
	 * for example, the following code will answer an {@link Predicate2} that
	 * compares <code>Employee</code>s names:
	 * 
	 * <pre>
	 * Equivalence.on(new Function&lt;Customer, String&gt;() {
	 * 	public String apply(Customer cus) {
	 * 		return cust.getName();
	 * 	}
	 * });
	 * </pre>
	 * 
	 * @param <A>
	 * @param <B>
	 * @param function
	 * @return a new {@link Predicate2}
	 */
	@NonNull
	@ForceRestrictions
	public static <A, B> Predicate2<A, A> on(@NonNull final Applicable<A, B> function) {
		return new AbstractPredicate2<A, A>() {
			public boolean eval(A arg0, A arg1) {
				return function.apply(arg0).equals(function.apply(arg1));
			}
		};
	}

}
