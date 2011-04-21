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

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * A {@link Thunk} that is {@link Comparable}, based on a priority attribute.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          The type of object provided
 * @param <P>
 *          the type of priority object
 */
@Value
@Conditionally({ Immutable.class, Serializable.class })
public class Prioritized<P extends Comparable<P>, T> implements Comparable<Prioritized<P, T>>, Thunk<T>, Serializable {

  private static final long serialVersionUID = 7131041003021112454L;
  private static final RelevantState<Prioritized> state = new RelevantState<Prioritized>(1) {
    protected void collectState(Prioritized object, StateCollector s) {
      s.add(object.priority);
    }
  };
  private final P priority;
  private final T value;

  /**
   * Creates a new {@link Prioritized}
   * 
   * @param priority
   *          the priority. Non Null.
   * @param value
   *          the provided - prioritized - value. Nullable.
   */
  public Prioritized(@NonNull P priority, T value) {
    this.value = value;
    this.priority = priority;
  }

  public T value() {
    return value;
  }

  /**
   * @return the priority used to determine order in comparison
   */
  @NonNull
  public P getPriority() {
    return priority;
  }

  public int compareTo(Prioritized<P, T> other) {
    return state.compareTo(this, other);
  }

  /**
   * Test for equalty. In order to be consistent with
   * {@link #compareTo(Prioritized)}, this method will only consider the
   * priority attribute in the test
   */
  public boolean equals(Object obj) {
    return state.equals(this, obj);
  }

  public int hashCode() {
    return state.hashCode(this);
  }

  public String toString() {
    return String.format("Prioritized(%s,%s)", priority, value);
  }

  /**
   * Factory method that creates a {@link Prioritized}
   * 
   * @param priority
   *          Non Null.
   * @param value
   *          the provided value. Non Nullable.
   * 
   * @param <T>
   * @param <P>
   * @return a new {@link Prioritized}
   */
  @NonNull
  public static <T, P extends Comparable<P>> Prioritized<P, T> from(P priority, T value) {
    return new Prioritized<P, T>(priority, value);
  }

}
