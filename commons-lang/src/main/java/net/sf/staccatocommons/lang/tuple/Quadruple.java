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
import net.sf.staccatocommons.lang.tuple.Tuple.FirstAware;
import net.sf.staccatocommons.lang.tuple.Tuple.FourthAware;
import net.sf.staccatocommons.lang.tuple.Tuple.SecondAware;
import net.sf.staccatocommons.lang.tuple.Tuple.ThirdAware;
import net.sf.staccatocommons.lang.value.RelevantState;

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
	Comparable<Quadruple<T1, T2, T3, T4>>, FirstAware<T1>, SecondAware<T2>, ThirdAware<T3>,
	FourthAware<T4> {

	private static final long serialVersionUID = -1072243152313731077L;
	private static final RelevantState<Quadruple> val = new TupleState<Quadruple>(4) {
		protected void collectState(Quadruple o, StateCollector b) {
			b.add(o.first).add(o.second).add(o.third).add(o.fourth);
		}
	};

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

	public T1 first() {
		return first;
	}

	public T2 second() {
		return second;
	}

	public T3 third() {
		return third;
	}

	@Override
	public T4 fourth() {
		return fourth;
	}

	public T1 _1() {
		return first();
	}

	public T2 _2() {
		return second();
	}

	public T3 _3() {
		return third();
	}

	@Override
	public T4 _4() {
		return fourth();
	}

	@Override
	public String toString() {
		return val.toString(this);
	}

	@NonNull
	@Override
	public Object[] toArray() {
		return new Object[] { first, second, third, fourth };
	}

	@Override
	public int compareTo(Quadruple<T1, T2, T3, T4> other) {
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

}
