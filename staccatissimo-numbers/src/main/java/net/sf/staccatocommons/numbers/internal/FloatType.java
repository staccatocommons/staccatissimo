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

package net.sf.staccatocommons.numbers.internal;

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.numbers.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class FloatType extends AbstractNumberType<Float> {

  private static final long serialVersionUID = 5307960326139220406L;
  /**
   * An instance
   */
  public static final NumberType<Float> TYPE = new FloatType();

  public Float add(Float n0, Float n1) {
    return n0 + n1;
  }

  public Float multiply(Float n0, Float n1) {
    return n0 * n1;
  }

  public Float divide(Float n0, Float n1) {
    return n0 / n1;
  }

  public Float negate(Float n) {
    return -n;
  }

  public Float zero() {
    return 0f;
  }

  public Float one() {
    return 1f;
  }

  public Float increment(Float n) {
    return n + 1;
  }

  public Float decrement(Float n) {
    return n - 1;
  }

  public Float fromInt(int ordinal) {
    return Float.valueOf(ordinal);
  }
}