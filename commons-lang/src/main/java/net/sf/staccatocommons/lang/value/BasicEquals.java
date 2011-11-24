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

package net.sf.staccatocommons.lang.value;

import net.sf.staccatocommons.restrictions.check.NonNull;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Enumeration of equalty-test scenarios that helps on building effective
 * {@link Object#equals(Object)} method.
 * 
 * Normal usage scenario is the following:
 * 
 * <pre>
 * public boolean equals(Object obj) {
 *    BasicEquals be = BasicEquals.from(this, obj);
 *    if (be.isEqualsDone())
 *       return be.toEquals();
 *    MyClass that = (MyClass) obj;
 *    ... test equalty based on internal state ...
 *    return ....; 
 * }
 * </pre>
 * 
 * {@link BasicEquals} observes the following rules:
 * <ol>
 * <li>Reflexivity: an object is always equal to it</li>
 * <li>Non-nullability: an object can never be equal to null</li>
 * <li>An object can never to an object of a different type - required for
 * symmetry</li>
 * </ol>
 * 
 * {@link BasicEquals} implements rule nº 3 using a class-comparison strategy -
 * as opposed to instanceof-comparison strategy. In many scenarios this is just
 * enough, but this may not be the better strategy always, so client code should
 * take care about it.
 * 
 * @author flbulgarelli
 * @see EqualsBuilder
 * @see Object#equals(Object)
 * 
 */
public enum BasicEquals {

  /**
   * {@link BasicEquals} test result where the two objects are always equal,
   * because their are the same object
   */
  ALWAYS {
    @Override
    public boolean toEquals() {
      return true;
    }

    public boolean isEqualsDone() {
      return true;
    }
  },
  /**
   * {@link BasicEquals} test result where the two objects can never be equal,
   * as either have different classes, or the second one is null
   */
  NEVER {
    @Override
    public boolean toEquals() {
      return false;
    }

    public boolean isEqualsDone() {
      return true;
    }
  },
  /**
   * {@link BasicEquals} test result where the two objects may be equal if and
   * only if the have a similar internal state
   */
  MAYBE {
    @Override
    public boolean toEquals() {
      throw new IllegalStateException();
    }

    public boolean isEqualsDone() {
      return false;
    }
  };

  /**
   * Performs a {@link BasicEquals} test
   * 
   * @param <T>
   * @param this_
   *          the "left hand" object to test equalty, that is, the object to
   *          wich the equals message has been sent
   * @param that
   *          the "right hand" objet of the equlty test, that is, the object
   *          that is parameter of the the equals message the
   * @return {@link #NEVER} if <code>that</code> is null or its class is not the
   *         same that <code>this_.getClass()</code>, {@link #ALWAYS} if both
   *         objects are the same, or {@link #MAYBE} otherwise
   */
  @NonNull
  public static <T> BasicEquals from(@NonNull T this_, Object that) {
    if (that == null)
      return NEVER;
    if (that == this_)
      return ALWAYS;
    if (that.getClass() != this_.getClass())
      return NEVER;
    return MAYBE;
  }

  // @NonNull
  // @ForceChecks
  // public static <T> BasicEquals from(@NonNull Class<T> thisClass, @NonNull T
  // this_, Object that) {
  // if (that == null)
  // return NEVER;
  // if (that == this_)
  // return ALWAYS;
  // if (!thisClass.isAssignableFrom(that.getClass()))
  // return NEVER;
  // return MAYBE;
  // }

  /**
   * Returns the equals test based on this basic equals test result. This method
   * <strong>must</strong> only be called if there is enough information to
   * determine it, that is, if {@link #isEqualsDone()}.
   * 
   * @return true if this is {@link #ALWAYS} or false if this is {@link #NEVER}
   * @throws IllegalStateException
   *           of this is {@link #MAYBE}
   */
  public abstract boolean toEquals();

  /**
   * Answers if this basic equalty-test result is enough to determine if the two
   * objects given are equal or not
   * 
   * @return <code>true</code> for {@link #NEVER} and {@link #ALWAYS},
   *         <code>false</code> for {@link #MAYBE}
   */
  public abstract boolean isEqualsDone();
}
