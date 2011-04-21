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

package net.sf.staccatocommons.lang;

import java.io.Serializable;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.lang.sequence.StopConditions;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * A {@link Range} represents a an inclusive lower and upper bound of
 * {@link Comparable} objects.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
@Value
@Conditionally({ Immutable.class, Serializable.class })
public class Range<T extends Comparable<T>> implements ContainsAware<T>, EmptyAware, Serializable {

  private static final long serialVersionUID = -1096861117755452369L;
  private static final RelevantState<Range> state = new RelevantState<Range>(2) {
    protected void collectState(Range object, StateCollector s) {
      s.add(object.min).add(object.max);
    }
  };

  private final T min;
  private final T max;

  /**
   * Creates a new {@link Range}
   * 
   * @param min
   *          the lower bound, inclusive.
   * @param max
   *          the upper bound, inclusive.
   */
  public Range(@NonNull T min, @NonNull T max) {
    Ensure.that().isGreaterThanOrEqualTo("max", max, min);
    this.min = min;
    this.max = max;
  }

  /**
   * Tests if a given element is between this range lower and upper bounds
   */
  public boolean contains(@NonNull T element) {
    return Compare.between(element, min, max);
  }

  /**
   * 
   * @param that
   *          nonNull
   * @return if the given {@link Range} overlaps with this one
   */
  public boolean overlaps(@NonNull Range<T> that) {
    return this.contains(that.getMin()) //
      || this.contains(that.getMin()) //
      || that.contains(this.getMin());
  }

  /**
   * Tests that a given range lower bounds is equal or greater to this one, and
   * that its upper bound are equal or lower to this one
   * 
   * @param that
   *          another range to test for inclusion
   * @return if the given range is included in the receptor
   */
  public boolean includes(@NonNull Range<T> that) {
    return this.getMin().compareTo(that.getMin()) <= 0 && this.getMax().compareTo(that.getMax()) >= 0;
  }

  /**
   * Answers the upper bound
   * 
   * @return the inclusive upper bound of this range.
   */
  @NonNull
  public T getMax() {
    return max;
  }

  /**
   * Answers the lower bound
   * 
   * @return the inclusive lower bound of this range.
   */
  @NonNull
  public T getMin() {
    return min;
  }

  /**
   * Answers if <code>min</code> and <code>max</code> are equal, based on
   * {@link Comparable#compareTo(Object)}
   * 
   * @return <code>getMin().compareTo(getMax()) == 0</code>
   */
  @Override
  public boolean isEmpty() {
    return getMin().compareTo(getMax()) == 0;
  }

  @Override
  public boolean equals(Object obj) {
    return state.equals(this, obj);
  }

  @Override
  public int hashCode() {
    return state.hashCode(this);
  }

  @Override
  public String toString() {
    return state.toString(this);
  }

  /**
   * Returns a {@link Sequence} that iterates from <code>this.getMin()</code> up
   * to <code>this.getMax</code>, using as generator the given
   * {@link Applicable}
   * 
   * @param generator
   *          the generator of the new sequence
   * @return a new sequence
   */
  @NonNull
  public Sequence<T> by(@NonNull Applicable<T, T> generator) {
    return Sequence.from(getMin(), generator, StopConditions.upTo(getMax()));
  }

  /**
   * Factory method that creates a new {@link Range}
   * 
   * @param <T>
   *          the type of range
   * @param min
   *          the lower bound, inclusive.
   * @param max
   *          the upper bound, inclusive.
   * @return the new Range.
   */
  @NonNull
  public static <T extends Comparable<T>> Range<T> from(@NonNull T min, @NonNull T max) {
    return new Range<T>(min, max);
  }
}
