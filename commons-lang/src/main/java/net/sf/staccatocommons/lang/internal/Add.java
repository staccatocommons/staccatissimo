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

package net.sf.staccatocommons.lang.internal;

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 */
public final class Add extends AbstractFunction<Integer, Integer> {

  private final int i;

  /**
   * @param i
   */
  public Add(int i) {
    this.i = i;
  }

  @Override
  public Integer apply(Integer arg) {
    return arg.intValue() + i;
  }

  /**
   * @param i
   */
  public static Function<Integer, Integer> add(int i) {
    return i == 1 ? one() : new Add(i);
  }

  /**
   * @return {@code add(1)}
   */
  @Constant
  public static Function<Integer, Integer> one() {
    return new Add(1);
  }

}
