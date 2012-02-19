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
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.numbers;

import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.type.IntegralType;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * Abstract implementation of an {@link IntegralType}
 * 
 * @author flbulgarelli
 * @since 2.1
 */
public abstract class AbstractIntegralType<A extends Number & Comparable<A>> extends AbstractNumberType<A> implements
  IntegralType<A> {

  private static final long serialVersionUID = -2139137089502466431L;

  public boolean isOdd(A n) {
    return !isEven(n);
  }

  public boolean isEven(A n) {
    return isZero(remainder(n, fromInt(2)));
  }

  public Function2<A, A, A> remainder() {
    return new AbstractFunction2<A, A, A>() {
      public A apply(A arg0, A arg1) {
        return remainder(arg0, arg1);
      }
    };
  }

  @Constant
  public Predicate<A> isEven() {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return isEven(argument);
      }
    };
  }

  @Constant
  public Predicate<A> isOdd() {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return isOdd(argument);
      }
    };
  }

}
