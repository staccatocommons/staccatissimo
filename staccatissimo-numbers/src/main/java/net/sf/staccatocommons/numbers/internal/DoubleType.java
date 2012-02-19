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
public final class DoubleType extends AbstractNumberType<Double> {

  private static final long serialVersionUID = 2443701983046831362L;
  /**
   * An instance
   */
  public static final NumberType<Double> TYPE = new DoubleType();

  public Double add(Double n0, Double n1) {
    return n0 + n1;
  }

  public Double multiply(Double n0, Double n1) {
    return n0 * n1;
  }

  public Double divide(Double n0, Double n1) {
    return n0 / n1;
  }

  public Double negate(Double n) {
    return -n;
  }

  public Double zero() {
    return 0.0;
  }

  public Double one() {
    return 1.0;
  }

  public Double increment(Double n) {
    return n + 1;
  }

  public Double decrement(Double n) {
    return n - 1;
  }

  public Double fromInt(int ordinal) {
    return Double.valueOf(ordinal);
  }
}