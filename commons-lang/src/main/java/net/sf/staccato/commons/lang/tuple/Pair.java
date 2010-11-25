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

import java.util.Map;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * Two-components {@link Tuple}
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          first component type
 * @param <T2>
 *          second component type
 */
public final class Pair<T1, T2> extends Tuple implements
	Comparable<Pair<T1, T2>>, Map.Entry<T1, T2> {

	private static final long serialVersionUID = -6479045670420592337L;

	private final T1 first;
	private final T2 second;

	/**
	 * Creates a new pair.
	 * 
	 * @param fist
	 * @param second
	 */
	public Pair(T1 fist, T2 second) {
		this.first = fist;
		this.second = second;
	}

	/**
	 * Answers the first component
	 * 
	 * @return the first component
	 */
	public T1 getFirst() {
		return first;
	}

	/**
	 * Synonym for {@link #getFirst()}
	 * 
	 * @return {@link #getFirst()}
	 * 
	 */
	public T1 _1() {
		return first;
	}

	/**
	 * Answers the second component
	 * 
	 * @return the second component
	 */
	public T2 getSecond() {
		return second;
	}

	/**
	 * Synonym for {@link #getSecond()}
	 * 
	 * @return {@link #getSecond()}
	 * 
	 */
	public T1 _2() {
		return first;
	}

	/**
	 * Creates a new tuple, with swaped components
	 * 
	 * @return a new pair, never null.
	 */
	@NonNull
	public Pair<T2, T1> swap() {
		return new Pair<T2, T1>(second, first);
	}

	@Override
	public int length() {
		return 2;
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)", first, second);
	}

	@NonNull
	@Override
	public Object[] toArray() {
		return new Object[] { first, second };
	}

	public int compareTo(Pair<T1, T2> other) {
		int result;
		if (other == this)
			return 0;

		return (result = compare(this.first, other.first)) != 0 ? result : //
			compare(this.second, other.second);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
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
		Pair other = (Pair) obj;
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
		return true;
	}

	public T1 getKey() {
		return getFirst();
	}

	public T2 getValue() {
		return getSecond();
	}

	public T2 setValue(T2 arg0) {
		throw new UnsupportedOperationException();
	}

}
