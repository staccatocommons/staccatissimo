/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang.tuple;

import java.io.Serializable;

import net.sf.staccatocommons.defs.tuple.Tuple4;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * Four-components {@link AbstractTuple}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * 
 */
@Value
@Conditionally({ Immutable.class, Serializable.class })
public final class Quadruple<A, B, C, D> extends AbstractTuple implements Tuple4<A, B, C, D> {

  private static final long serialVersionUID = -1072243152313731077L;
  private static final RelevantState<Tuple4> VAL = new TupleState<Tuple4>(4) {
    protected void collectState(Tuple4 o, StateCollector b) {
      b.add(o.first()).add(o.second()).add(o.third()).add(o.fourth());
    }
  };

  private final A first;
  private final B second;
  private final C third;
  private final D fourth;

  /**
   * Creates a new {@link Quadruple}
   * 
   * @param first
   * @param second
   * @param third
   * @param fourth
   */
  public Quadruple(A first, B second, C third, D fourth) {
    this.first = first;
    this.second = second;
    this.third = third;
    this.fourth = fourth;
  }

  public A first() {
    return first;
  }

  public B second() {
    return second;
  }

  public C third() {
    return third;
  }

  @Override
  public D fourth() {
    return fourth;
  }

  public A _0() {
    return first();
  }

  public B _1() {
    return second();
  }

  public C _2() {
    return third();
  }

  @Override
  public D _3() {
    return fourth();
  }

  @Override
  public String toString() {
    return VAL.toString(this);
  }

  @NonNull
  @Override
  public Object[] toArray() {
    return new Object[] { first, second, third, fourth };
  }

  @Override
  public int compareTo(Tuple4<A, B, C, D> other) {
    return VAL.compareTo(this, other);
  }

  @Override
  public int hashCode() {
    return VAL.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return VAL.equals(this, obj);
  }

}
