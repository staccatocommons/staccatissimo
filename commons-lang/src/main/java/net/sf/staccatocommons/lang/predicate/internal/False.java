/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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

import static net.sf.staccatocommons.lang.predicate.Predicates.*;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class False<T> extends NonAnnonymousPredicate<T> {
  private static final long serialVersionUID = 7804525181528599615L;

  public boolean eval(T argument) {
    return false;
  }

  /**
   * @return the instance
   */
  @Constant
  public static Predicate getInstance() {
    return new False();
  }

  @Override
  public Predicate<T> and(Evaluable<? super T> other) {
    return this;
  }

  @Override
  public Predicate<T> or(Evaluable<? super T> other) {
    return from(other);
  }

  @Override
  public Predicate<T> not() {
    return true_();
  }
}