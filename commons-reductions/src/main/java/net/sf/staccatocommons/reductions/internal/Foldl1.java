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

package net.sf.staccatocommons.reductions.internal;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.reductions.AbstractReduction;

/**
 * Reduction that performs no-initial-element folding
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class Foldl1<A> extends AbstractReduction<A, A> {
  private final Applicable2<? super A, ? super A, ? extends A> function;

  /**
   * @param function
   */
  public Foldl1(Applicable2<? super A, ? super A, ? extends A> function) {
    this.function = function;
  }

  public Accumulator<A, A> newAccumulator() {
    return new Accumulator<A, A>() {
      private A i;
      private boolean init;

      public void accumulate(A element) {
        if (!init) {
          i = element;
          init = true;
        } else
          i = function.apply(i, element);
      }

      public A value() {
        if (!init)
          throw new NoSuchElementException();
        return i;
      }
    };
  }
}