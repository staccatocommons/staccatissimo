/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Static factory methods for creating {@link BigDecimal}s and
 * {@link BigInteger}s aimed to palliate the lack of big decimal and big integer
 * literals in the Java language
 * <p>
 * Although such class methods already exist - {@link BigDecimal#valueOf(long)}
 * is an example - even when using static imports, dealing with them is clumsy,
 * because of its long names. This in turn introduces new potential performance
 * issues unnecessarily, as many programmers end up invoking directly BigDecimal
 * and BigInteger's constructors, bypassing its cache.
 * </p>
 * Thus, the intention of this class is offering much shorter methods names,
 * with some resemblance to the Java literals for longs and floats - like for
 * example <code>1L or 2.5f</code>. Using this class, declaring
 * {@link BigInteger} and {@link BigDecimal} literals will look this way:
 * 
 * <pre>
 *  import static net.sf.staccatocommons.lang.number.Numbers.*;
 *  ...
 *  //Equivalent to BigInteger.valueOf(900)
 *  BigInteger initialPopulation = i(900);
 *  ....
 *  //distance is 15906 × 10^-3
 *  //Equivalent to BigDecimal.valueOf(15906,3)
 *  BigDecimal distance = e(15906,-3);
 *  ...
 *  //salary is 5000. 
 *  //Equivalent to BigDecimal.valueOf(5000)
 *  BigDecimal salary = d(5000);
 * </pre>
 * 
 * 
 * @author flbulgarelli
 * 
 */
public class Numbers {

  private Numbers() {}

  /**
   * Synonym for {@link BigDecimal#valueOf(long)}
   * 
   * @param val
   * @return a BigDecimal whose value is val
   */
  @NonNull
  public static BigDecimal d(long val) {
    return BigDecimal.valueOf(val);
  }

  /**
   * Synonym for {@link BigDecimal#valueOf(long, int)}
   * 
   * @param val
   * @param scale
   * @return a {@link BigDecimal} whose value is mantissa × 10^-scale
   */
  @NonNull
  public static BigDecimal d(long val, int scale) {
    return BigDecimal.valueOf(val, scale);
  }

  /**
   * Answers a big decimal of the given value expressed in calculator
   * "E notation": <code>mantissa E exponent</code>. In scientific notation, its
   * value is <code>mantissa × 10^exponent</code>.
   * <p>
   * <strong>Warning:</strong> This method is <strong>not</strong> a synonym of
   * {@link BigDecimal#valueOf(double)}, as the latter negates the exponent
   * </p>
   * 
   * @param mantissa
   *          - aka significand
   * @param exponent
   *          the exponent
   * @return a BigDecimal whose value is mantissa × 10^exponent.
   */
  @NonNull
  public static BigDecimal e(long mantissa, int exponent) {
    return BigDecimal.valueOf(mantissa, -exponent);
  }

  /**
   * Synonym for {@link BigInteger#valueOf(long)}
   * 
   * @param val
   * @return a BigInteger with the specified value.
   */
  @NonNull
  public static BigInteger i(long val) {
    return BigInteger.valueOf(val);
  }

}
