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
package net.sf.staccatocommons.defs.type;

import java.math.BigInteger;

import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.value.Immutable;

/**
 * {@link NumberType} with extra messages restricted to integral types like
 * integers and {@link BigInteger}s
 * 
 * @author flbulgarelli
 * @since 2.1
 */
@Immutable
public interface IntegralType<A> extends NumberType<A> {

  /**
   * Answers if the given number is even
   * 
   * @param n
   *          the number
   * @return whether the number is even
   */
  boolean isEven(A n);

  /**
   * Answers if the given number is odd, that is, is not even.
   * 
   * @param n
   *          the number
   * @return whether the number is odd
   */
  boolean isOdd(A n);

  /**
   * Returns the reminder of integral dividing n1 over n2
   * 
   * @param n1
   * @param n2
   * @return n1 % n2
   */
  A remainder(A n1, A n2);

  /**
   * Answers a binary function that return reminder as specified by
   * {@link #remainder(Object, Object)}
   * 
   * @return a {@link Function2}
   */
  Function2<A, A, A> remainder();

  /**
   * Answers a predicate that returns if its argument is even, as specified by
   * {@link #isEven(Object)}
   * 
   * @return a predicate
   */
  Predicate<A> isEven();

  /**
   * Answers a predicate that returns if its argument is odd, as specified by
   * {@link #isOdd(Object)}
   * 
   * @return a predicate
   */
  Predicate<A> isOdd();
}
