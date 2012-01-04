/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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
import java.util.Arrays;
import java.util.List;

import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.defs.tuple.Tuple3;
import net.sf.staccatocommons.lang.tuple.internal.TupleToStringStyle;
import net.sf.staccatocommons.lang.value.RelevantState;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * <p>
 * A {@link AbstractTuple} is a fixed size sequence of heterogeneous objects.
 * They are {@link ConditionallyImmutable} and {@link ConditionallySerializable}
 * . They are comparable, as long as they components are, too.
 * </p>
 * <p>
 * Tuples are aimed to be used in those situations where an object that just
 * relates others together is needed. Such object does not encapsulate any
 * business concept nor any specific behavior except of
 * {@link Object#toString()}, {@link Object#equals(Object)},
 * {@link Object#hashCode()} and {@link Comparable#compareTo(Object, Object)}.
 * Tuples are thus not intended to be used everywhere nor extended, but there
 * are some concrete scenarios where they are useful:
 * </p>
 * <ul>
 * <li>
 * When immutable components are used, they are valid keys for hashed maps or
 * elements of hashed sets</li>
 * <li>When comparable components are used, they are valid keys for sorted maps
 * or elements of sorted sets and sorted lists</li>
 * <li>For debugging purposes, when it is needed to print to an string multiple
 * objects.</li>
 * <li>When a method needs to return a fixed amount of different results. For
 * example, using 2-components tuple, it is easy to implement a divmod method
 * that returns the quotient and modulus of an integral division, with the
 * signature <code>Tuple2&lt;Integer, Integer&gt; divMod(int x, int y)</code></li>
 * </ul>
 * 
 * Although it is possible to create tuples of arities from 2 to 4 invoking the
 * appropriate constructor, the recommended way of instantiating tuples is using
 * the family of class methods in {@link Tuples} named <code>_</code>. Although
 * it looks odd at first glance, combining it with static imports produces quite
 * clean code. For example, using again the divMod method:
 * 
 * <pre>
 *  import static net.sf.staccatocommons.lang.tuple._;
 *  
 *  ...
 *  Tuple2&lt;Integer, Integer&gt; divMod(int x, int y)
 *   return _(x/y, x%y)
 *  ...
 * 
 * </pre>
 * 
 * 
 * @author flbulgarelli
 * @see Tuple2
 * @see Tuple3
 * @see Quadruple
 */
@Value
@Conditionally({ Immutable.class, Serializable.class })
public abstract class AbstractTuple implements Serializable {

  private static final long serialVersionUID = -3943649706502147516L;

  AbstractTuple() {}

  /**
   * Converts this tuple into an array
   * 
   * @return an new Object[] containing each of the elements of this tuple
   */
  @NonNull
  public abstract Object[] toArray();

  /**
   * Gets an unmodifiable list containing each components of this tuple as
   * elements
   * 
   * @return a non null, unmodifiable list
   */
  @NonNull
  public List<Object> toList() {
    return Arrays.asList(toArray());
  }

  protected abstract static class TupleState<A> extends RelevantState<A> {
    /**
     * Creates a new {@link TupleState}
     */
    public TupleState(int componentsCount) {
      super(componentsCount, TupleToStringStyle.getInstance());
    }
  }

}
