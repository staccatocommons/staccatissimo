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

import java.util.Map;

import net.sf.staccatocommons.lang.tuple.Tuple.FirstAware;
import net.sf.staccatocommons.lang.tuple.Tuple.SecondAware;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.ConditionallySerializable;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.ConditionallyImmutable;
import net.sf.staccatocommons.restrictions.value.Value;

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
@Value
@ConditionallyImmutable
@ConditionallySerializable
public final class Pair<T1, T2> extends Tuple implements Comparable<Pair<T1, T2>>,
	Map.Entry<T1, T2>, FirstAware<T1>, SecondAware<T2> {

	private static final long serialVersionUID = -6479045670420592337L;
	private static final RelevantState<Pair> val = new TupleState<Pair>(2) {
		protected void collectState(Pair o, StateCollector s) {
			s.add(o.first).add(o.second);
		}
	};

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

	@Override
	public T1 first() {
		return first;
	}

	@Override
	public T1 _0() {
		return first;
	}

	@Override
	public T2 second() {
		return second;
	}

	@Override
	public T2 _1() {
		return second;
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
	public String toString() {
		return val.toString(this);
	}

	@NonNull
	@Override
	public Object[] toArray() {
		return new Object[] { first, second };
	}

	public int compareTo(Pair<T1, T2> other) {
		return val.compareTo(this, other);
	}

	@Override
	public int hashCode() {
		return val.hashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return val.equals(this, obj);
	}

	public T1 getKey() {
		return first();
	}

	public T2 getValue() {
		return second();
	}

	public T2 setValue(T2 arg0) {
		throw new UnsupportedOperationException();
	}

}
