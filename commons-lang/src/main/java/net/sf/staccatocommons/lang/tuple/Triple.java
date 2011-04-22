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

import net.sf.staccatocommons.lang.tuple.Tuple.FirstAware;
import net.sf.staccatocommons.lang.tuple.Tuple.SecondAware;
import net.sf.staccatocommons.lang.tuple.Tuple.ThirdAware;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

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
@Conditionally({ Immutable.class, Serializable.class })
public final class Triple<T1, T2, T3> extends Tuple implements Comparable<Triple<T1, T2, T3>>, FirstAware<T1>,
  SecondAware<T2>, ThirdAware<T3> {

  private static final long serialVersionUID = 5811264763831754560L;
  private static final RelevantState<Triple> VAL = new TupleState<Triple>(3) {
    protected void collectState(Triple o, StateCollector b) {
      b.add(o.first).add(o.second).add(o.third);
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

  @Override
  public T1 first() {
    return first;
  }

  @Override
  public T2 second() {
    return second;
  }

  @Override
  public T3 third() {
    return third;
  }

  @Override
  public T1 _0() {
    return first();
  }

  @Override
  public T2 _1() {
    return second();
  }

  public T3 _2() {
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

  public int compareTo(Triple<T1, T2, T3> other) {
    return VAL.compareTo(this, other);
  }

}
