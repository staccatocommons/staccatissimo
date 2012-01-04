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

package net.sf.staccatocommons.reductions.internal;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.defs.reduction.Reduction;
import net.sf.staccatocommons.reductions.AbstractReduction;

/**
 * Fold with initial element {@link Reduction}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public final class Foldl<A, B> extends AbstractReduction<A, B> {
  private final B initial;
  private final Applicable2<? super B, ? super A, ? extends B> function;

  /**
   * Creates the {@link Foldl} reduction
   * 
   * @param initial
   * @param function
   */
  public Foldl(B initial, Applicable2<? super B, ? super A, ? extends B> function) {
    this.initial = initial;
    this.function = function;
  }

  public Accumulator<A, B> newAccumulator() {
    return new Accumulator<A, B>() {
      private B i = initial;

      public void accumulate(A element) {
        i = function.apply(i, element);
      }

      public B value() {
        return i;
      }
    };
  }
}