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

import net.sf.staccatocommons.defs.Evaluable;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class All<T> extends TopLevelPredicate<T> {
  private static final long serialVersionUID = 2032230415038798930L;
  private final Iterable<Evaluable<T>> predicates;

  /**
   * Creates a new {@link All}
   * 
   * @param predicates
   */
  public All(Iterable<Evaluable<T>> predicates) {
    this.predicates = predicates;
  }

  public boolean eval(T argument) {
    for (Evaluable<T> predicate : predicates)
      if (!predicate.eval(argument))
        return false;
    return true;
  }
}