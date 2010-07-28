/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.tuple;

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
public final class Triple<T1, T2, T3> extends Tuple implements
	Comparable<Triple<T1, T2, T3>> {

	private static final long serialVersionUID = 5811264763831754560L;

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

	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}

	public T3 getThird() {
		return third;
	}

	@Override
	public int length() {
		return 3;
	}

	public Triple<T2, T3, T1> rotateLeft() {
		return new Triple<T2, T3, T1>(this.second, this.third, this.first);
	}

	public Triple<T3, T1, T2> rotateRight() {
		return new Triple<T3, T1, T2>(this.third, this.first, this.second);
	}

	@Override
	public String toString() {
		return String.format("(%s,%s,%s)", first, second, third);
	}

	@Override
	public Object[] toArray() {
		return new Object[] { first, second, third };
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
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
		Triple other = (Triple) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
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

	public int compareTo(Triple<T1, T2, T3> other) {
		if (other == this)
			return 0;
		int result;
		return (result = compare(this.first, other.first)) != 0 ? result
			: (result = compare(this.second, other.second)) != 0 ? result : compare(
				this.third,
				other.third);
	}

}
