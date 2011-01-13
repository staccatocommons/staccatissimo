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
package net.sf.staccatocommons.lang.tuple;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.restriction.ConditionallyImmutable;
import net.sf.staccatocommons.defs.restriction.ConditionallySerializable;
import net.sf.staccatocommons.defs.restriction.Value;
import net.sf.staccatocommons.lang.tuple.internal.TupleValue;

/**
 * Three-components {@link Tuple}.
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * 
 */
@Value
@ConditionallyImmutable
@ConditionallySerializable
public final class Triple<T1, T2, T3> extends Tuple implements Comparable<Triple<T1, T2, T3>> {

	private static final long serialVersionUID = 5811264763831754560L;
	private static final TupleValue<Triple> val = new TupleValue<Triple>(3) {
		protected void significant(Triple o, Criteria b) {
			b.with(o.first).with(o.second).with(o.third);
		}
	};

	private final T1 first;
	private final T2 second;
	private final T3 third;

	/**
	 * Creates a new {@link Triple}
	 * 
	 * @param first
	 *          1st component. Nullable
	 * @param second
	 *          2nd component. Nullable
	 * @param third
	 *          3rd component. Nullable.
	 */
	public Triple(T1 first, T2 second, T3 third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	/**
	 * @return the first component
	 */
	public T1 getFirst() {
		return first;
	}

	/**
	 * @return the second component
	 */
	public T2 getSecond() {
		return second;
	}

	/**
	 * @return the third component
	 */
	public T3 getThird() {
		return third;
	}

	/**
	 * @return the first component
	 */
	public T1 _1() {
		return getFirst();
	}

	/**
	 * @return the second component
	 */
	public T2 _2() {
		return getSecond();
	}

	/**
	 * @return the third component
	 */
	public T3 _3() {
		return getThird();
	}

	/**
	 * <p>
	 * Rotates this {@link Triple} components to left, creating a new one where
	 * the first component is placed at the third position, an the rests, shifted
	 * right.
	 * </p>
	 * <p>
	 * Given a triple whose components a, b, c implement appropriately equals
	 * method, the following is always <code>true</code>
	 * </p>
	 * 
	 * <pre>_(a,b,c).rotateLeft().equals(_(b,c,a))
	 * 
	 * <pre>
	 * 
	 * 
	 * @return a new, non null
	 * {@link Triple}, with its components rotated to left
	 */
	@NonNull
	public Triple<T2, T3, T1> rotateLeft() {
		return new Triple<T2, T3, T1>(this.second, this.third, this.first);
	}

	/**
	 * <p>
	 * Rotates this {@link Triple} components to right, creating a new one where
	 * the third component is placed at the first position, an the rests, shifted
	 * right.
	 * </p>
	 * <p>
	 * Given a triple whose components a, b, c implement appropriately equals
	 * method, the following is always <code>true</code>
	 * </p>
	 * 
	 * <pre>_(a,b,c).rotateLeft().equals(_(c,b,a))
	 * 
	 * <pre>
	 * 
	 * @return a new, non null
	 * {@link Triple}, with its components rotated to right
	 */
	@NonNull
	public Triple<T3, T1, T2> rotateRight() {
		return new Triple<T3, T1, T2>(this.third, this.first, this.second);
	}

	@Override
	public String toString() {
		return val.toString(this);
	}

	@Override
	@NonNull
	public Object[] toArray() {
		return new Object[] { first, second, third };
	}

	@Override
	public int hashCode() {
		return val.hashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return val.equals(this, obj);
	}

	public int compareTo(Triple<T1, T2, T3> other) {
		return val.compareTo(this, other);
	}

}
