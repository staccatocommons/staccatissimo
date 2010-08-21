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
package net.sf.staccato.commons.lang.tuple;

import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * Four-components tuple
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 * 
 */
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
	public int length() {
		return 4;
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
		return (result = compare(this.first, other.first)) != 0 ? result
			: (result = compare(this.second, other.second)) != 0 ? result
				: (result = compare(this.third, other.third)) != 0 ? result : compare(
					this.fourth,
					other.fourth);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((fourth == null) ? 0 : fourth.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((third == null) ? 0 : third.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quadruple other = (Quadruple) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (fourth == null) {
			if (other.fourth != null)
				return false;
		} else if (!fourth.equals(other.fourth))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		if (third == null) {
			if (other.third != null)
				return false;
		} else if (!third.equals(other.third))
			return false;
		return true;
	}

}
