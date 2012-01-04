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

import java.math.BigDecimal;

import net.sf.staccatocommons.numbers.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class BigDecimalType extends AbstractNumberType<BigDecimal> {

  private static final long serialVersionUID = 4097436616137740515L;
  /**
   * An instance
   */
  public static final BigDecimalType TYPE = new BigDecimalType();

  public BigDecimal add(BigDecimal n0, BigDecimal n1) {
    return n0.add(n1);
  }

  public BigDecimal multiply(BigDecimal n0, BigDecimal n1) {
    return n0.multiply(n1);
  }

  public BigDecimal divide(BigDecimal n0, BigDecimal n1) {
    return n0.divide(n1);
  }

  public BigDecimal negate(BigDecimal n) {
    return n.negate();
  }

  public BigDecimal zero() {
    return BigDecimal.ZERO;
  }

  public BigDecimal one() {
    return BigDecimal.ONE;
  }

}