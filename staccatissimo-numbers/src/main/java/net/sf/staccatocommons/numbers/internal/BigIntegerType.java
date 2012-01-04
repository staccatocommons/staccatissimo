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

import java.math.BigInteger;

import net.sf.staccatocommons.numbers.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class BigIntegerType extends AbstractNumberType<BigInteger> {

  private static final long serialVersionUID = 8595141753229390523L;
  /**
   * An instance
   */
  public static final BigIntegerType TYPE = new BigIntegerType();

  public BigInteger add(BigInteger n0, BigInteger n1) {
    return n0.add(n1);
  }

  public BigInteger multiply(BigInteger n0, BigInteger n1) {
    return n0.multiply(n1);
  }

  public BigInteger divide(BigInteger n0, BigInteger n1) {
    return n0.divide(n1);
  }

  public BigInteger negate(BigInteger n) {
    return n.negate();
  }

  public BigInteger zero() {
    return BigInteger.ZERO;
  }

  public BigInteger one() {
    return BigInteger.ONE;
  }

}