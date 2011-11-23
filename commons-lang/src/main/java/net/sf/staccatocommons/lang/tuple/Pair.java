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

import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * Two-components {@link AbstractTuple}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          first component type
 * @param <B>
 *          second component type
 */
@Value
@Conditionally({ Immutable.class, Serializable.class })
public final class Pair<A, B> extends AbstractTuple implements Tuple2<A, B> {

  private static final long serialVersionUID = -6479045670420592337L;
  private static final RelevantState<Tuple2> VAL = new TupleState<Tuple2>(2) {
    protected void collectState(Tuple2 o, StateCollector s) {
      s.add(o.first()).add(o.second());
    }
  };

  private final A first;
  private final B second;

  /**
   * Creates a new pair.
   * 
   * @param fist
   * @param second
   */
  public Pair(A fist, B second) {
    this.first = fist;
    this.second = second;
  }

  @Override
  public A first() {
    return first;
  }

  @Override
  public A _0() {
    return first;
  }

  @Override
  public B second() {
    return second;
  }

  @Override
  public B _1() {
    return second;
  }

  /**
   * Creates a new tuple, with swaped components
   * 
   * @return a new pair, never null.
   */
  @NonNull
  public Pair<B, A> swap() {
    return new Pair<B, A>(second, first);
  }

  @Override
  public String toString() {
    return VAL.toString(this);
  }

  @NonNull
  @Override
  public Object[] toArray() {
    return new Object[] { first, second };
  }

  public int compareTo(Tuple2<A, B> other) {
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

  public A getKey() {
    return first();
  }

  public B getValue() {
    return second();
  }

  public B setValue(B arg0) {
    throw new UnsupportedOperationException();
  }

}
