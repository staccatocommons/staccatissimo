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

import net.sf.staccatocommons.check.annotation.ForceChecks;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.lang.predicate.internal.CompareTest;
import net.sf.staccatocommons.lang.predicate.internal.EqualTest;

/**
 * Factory class methods for creating common, simple {@link Evaluable2} that
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
	 * Answers an {@link Evaluable2} that performs an equality test between its
	 * nullable arguments, that it returns true if both are null or both are non
	 * null and equal
	 * 
	 * @param <A>
	 * @return a constant {@link Evaluable2} that performs an equality test
	 */
	@NonNull
	@Constant
	public static <A> Evaluable2<A, A> equalOrNull() {
		return EqualTest.equalTest();
	}

	/**
	 * Answers an {@link Evaluable2} that performs an compare test between its
	 * nullable {@link Comparable} arguments, that it returns true if both are
	 * null or <code>arg0.compareTo(arg1) == 0</code>
	 * 
	 * @param <A>
	 * @return a constant {@link Evaluable2} that performs a compare test
	 */
	@NonNull
	@Constant
	public static <A extends Comparable<A>> Evaluable2<A, A> compareOrNull() {
		return CompareTest.compareTest();
	}

	/**
	 * Answers an {@link Evaluable2} that performs an equality test to the result
	 * of applying the given function to its arguments.
	 * 
	 * This is mostly usefull then the given function is just a property accesor,
	 * for example, the following code will answer an {@link Evaluable2} that
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
	 * @return a new {@link Evaluable2}
	 */
	@NonNull
	@ForceChecks
	public static <A, B extends Comparable<B>> Evaluable2<A, A> on(
		@NonNull final Applicable<A, B> function) {
		return new Evaluable2<A, A>() {
			public boolean eval(A arg0, A arg1) {
				return function.apply(arg0).equals(function.apply(arg1));
			}
		};
	}

	// TODO have a similar or strategy for nulls with eval2

}
