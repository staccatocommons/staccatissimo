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

import static net.sf.staccatocommons.lang.predicate.Predicates.*;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class True<T> extends TopLevelPredicate<T> {
  private static final long serialVersionUID = 4329617085573720583L;

  public boolean eval(T argument) {
    return true;
  }

  /**
   * @return the instance
   */
  @Constant
  public static Predicate getInstance() {
    return new True();
  }

  @Override
  public Predicate<T> or(Evaluable<? super T> other) {
    return this;
  }

  @Override
  public Predicate<T> and(Evaluable<? super T> other) {
    return from(other);
  }

  @Override
  public Predicate<T> not() {
    return false_();
  }

}