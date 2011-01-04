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
import net.sf.staccatocommons.lang.value.BasicEquals;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Four-components {@link Tuple}
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 * 
 */
@Value
@ConditionallyImmutable
@ConditionallySerializable
public final class Quadruple<T1, T2, T3, T4> extends Tuple implements
	Comparable<Quadruple<T1, T2, T3, T4>> {

	private static final long serialVersionUID = -1072243152313731077L;

	private final T1 first;
	private final T2 second;
	private final T3 third;
	private final T4 fourth;

	/**
	 * Creates a new {@link Quadruple}
	 * 
	 * @param first
	 * @param second
	 * @param third
	 * @param fourth
	 */
	public Quadruple(T1 first, T2 second, T3 third, T4 fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
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
	 * @return the fourth component
	 */
	public T4 getFourth() {
		return fourth;
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
	 * @return the fourth component
	 */
	public T4 _4() {
		return getFourth();
	}

	@Override
	public String toString() {
		return String.format("(%s,%s,%s,%s)", first, second, third, fourth);
	}

	@NonNull
	@Override
	public Object[] toArray() {
		return new Object[] { first, second, third, fourth };
	}

	public int compareTo(Quadruple<T1, T2, T3, T4> other) {
		if (other == this)
			return 0;
		int result;
		return (result = compare(this.first, other.first)) != 0 ? result : (result = compare(
			this.second,
			other.second)) != 0 ? result : (result = compare(this.third, other.third)) != 0 ? result
			: compare(this.fourth, other.fourth);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder() //
			.append(first)
			.append(second)
			.append(third)
			.append(fourth)
			.toHashCode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		BasicEquals be = BasicEquals.from(this, obj);
		if (be.isEqualsDone())
			return be.toEquals();
		Quadruple<T1, T2, T3, T4> other = (Quadruple<T1, T2, T3, T4>) obj;
		return new EqualsBuilder() //
			.append(this.first, other.first)
			.append(this.second, other.second)
			.append(this.third, other.third)
			.append(this.fourth, other.fourth)
			.isEquals();
	}

}
