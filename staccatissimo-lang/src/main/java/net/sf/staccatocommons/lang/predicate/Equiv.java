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


package net.sf.staccatocommons.lang.predicate;

import org.apache.commons.lang.ObjectUtils;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.lang.predicate.internal.Compare2;
import net.sf.staccatocommons.lang.predicate.internal.Equals2;
import net.sf.staccatocommons.lang.predicate.internal.Same2;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Factory class methods for creating common, simple {@link Predicate2} that
 * implement the notion of and equivalence test, that is, a reflexive, symmetric
 * and transitive relation between its arguments.
 * 
 * All {@link Predicate2} here are {@link NullSafe}
 * 
 * @author flbulgarelli
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Equivalence_relation">Equivalence-relation</a>
 * @see Object#equals(Object)
 */
public class Equiv {

  private Equiv() {}

  /**
   * Answers an {@link Predicate2} that performs an identity test between its
   * arguments, that it returns true if both are the same object
   * 
   * @param <A>
   * @return a constant {@link Predicate2} that performs an identity test
   */
  @Constant
  public static <A> Predicate2<A, A> same() {
    return Same2.<A> same();
  }

  /**
   * Answers an {@link Predicate2} that performs an equality test between its
   * arguments, that it returns true if both are equal
   * 
   * @param <A>
   * @return a constant {@link Predicate2} that performs an equality test
   */
  @Constant
  public static <A> Predicate2<A, A> equal() {
    return Equals2.<A> equalTest();
  }

  /**
   * Answers an {@link Predicate2} that performs an equality test between its
   * nullable arguments, that it returns true if both are equal or null.
   * 
   * @param <A>
   * @return <code>Equiv.equal().nullSafe()</code>
   * @deprecated since 2.4, all {@link Predicate2} in {@link Equiv} are nullsafe
   */
  @Deprecated
  @Constant
  public static <A> Predicate2<A, A> equalNullSafe() {
    return equal();
  }

  /**
   * Answers an {@link Predicate2} that performs an compare test between its
   * {@link Comparable} arguments, that it returns
   * <code>arg0.compareTo(arg1) == 0</code>
   * 
   * @param <A>
   * @return a constant {@link Predicate2} that performs a compare test
   */
  @Constant
  public static <A extends Comparable<A>> Predicate2<A, A> compare() {
    return Compare2.<A> compareTest();
  }

  /**
   * Answers an {@link Predicate2} that performs an equality test to the result
   * of applying the given function to its arguments.
   * 
   * This is mostly useful then the given function is just a property accesor,
   * for example, the following code will answer an {@link Predicate2} that
   * compares <code>Employee</code>s names:
   * 
   * <pre>
   * Equivalence.on(new Function&lt;Customer, String&gt;() {
   *   public String apply(Customer cus) {
   *     return cust.getName();
   *   }
   * });
   * </pre>
   * 
   * @param <A>
   * @param <B>
   * @param function
   * @return a new {@link Predicate2}
   */
  public static <A, B> Predicate2<A, A> on(@NonNull final Applicable<? super A, ? extends B> function) {
    return new AbstractPredicate2<A, A>() {
      public boolean eval(A arg0, A arg1) {
        return ObjectUtils.equals(function.apply(arg0), function.apply(arg1));
      }
      public Predicate2<A, A> nullSafe() {
        return this;
      }
    };
  }
}
