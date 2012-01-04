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

import net.sf.staccatocommons.defs.tuple.Tuple3;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * Three-components {@link AbstractTuple}.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * 
 */
@Value
@Conditionally({ Immutable.class, Serializable.class })
public final class Triple<A, B, C> extends AbstractTuple implements Tuple3<A, B, C> {

  private static final long serialVersionUID = 5811264763831754560L;
  private static final RelevantState<Tuple3> VAL = new TupleState<Tuple3>(3) {
    protected void collectState(Tuple3 o, StateCollector b) {
      b.add(o.first()).add(o.second()).add(o.third());
    }
  };

  private final A first;
  private final B second;
  private final C third;

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
  public Triple(A first, B second, C third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  @Override
  public A first() {
    return first;
  }

  @Override
  public B second() {
    return second;
  }

  @Override
  public C third() {
    return third;
  }

  @Override
  public A _0() {
    return first();
  }

  @Override
  public B _1() {
    return second();
  }

  public C _2() {
    return third();
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
  public Tuple3<B, C, A> rotateLeft() {
    return new Triple<B, C, A>(this.second, this.third, this.first);
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
  public Tuple3<C, A, B> rotateRight() {
    return new Triple<C, A, B>(this.third, this.first, this.second);
  }

  @Override
  public String toString() {
    return VAL.toString(this);
  }

  @Override
  @NonNull
  public Object[] toArray() {
    return new Object[] { first, second, third };
  }

  @Override
  public int hashCode() {
    return VAL.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return VAL.equals(this, obj);
  }

  public int compareTo(Tuple3<A, B, C> other) {
    return VAL.compareTo(this, other);
  }

}
