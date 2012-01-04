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

package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.defs.predicate.Predicate;

/**
 * @author flbulgarelli
 */
public final class NullPredicates {
  private static final Predicate NULL = new Null(), NOT_NULL = new NotNull();

  /**
   * @return the notNull
   */
  public static Predicate notNull() {
    return NOT_NULL;
  }

  /**
   * @return the null_
   */
  public static Predicate null_() {
    return NULL;
  }

  private static class NotNull<T> extends TopLevelPredicate<T> {
    /**
       * 
       */
    private static final long serialVersionUID = 876641857208937901L;

    public boolean eval(T argument) {
      return argument != null;
    }

    public Predicate<T> not() {
      return NULL;
    }
  }

  private static class Null<T> extends TopLevelPredicate<T> {
    /**
       * 
       */
    private static final long serialVersionUID = -7208581270049483766L;

    public boolean eval(T argument) {
      return argument == null;
    }

    public Predicate<T> not() {
      return NOT_NULL;
    }
  }
}