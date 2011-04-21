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
package net.sf.staccatocommons.lang.predicate;

import net.sf.staccatocommons.defs.predicate.Predicate2;

/**
 * @author flbulgarelli
 */
public abstract class AbstractPredicate2<A, B> implements Predicate2<A, B> {

  public final Boolean apply(A arg0, B arg1) {
    return eval(arg0, arg1);
  }

  public Predicate2<A, B> nullSafe() {
    return new NullSafePredicate2();
  }

  /**
   * @author flbulgarelli
   */
  protected final class NullSafePredicate2 extends AbstractPredicate2<A, B> {
    public boolean eval(A arg0, B arg1) {
      if (arg0 == null)
        return arg1 == null;
      if (arg1 == null)
        return false;
      return AbstractPredicate2.this.eval(arg0, arg1);
    }

    public Predicate2<A, B> nullSafe() {
      return this;
    }
  }
}
