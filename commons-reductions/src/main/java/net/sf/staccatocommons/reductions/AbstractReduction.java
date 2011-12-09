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

package net.sf.staccatocommons.reductions;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.defs.reduction.Reduction;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public abstract class AbstractReduction<A, B> implements Reduction<A, B> {

  @Override
  public final <C> Reduction<A, C> then(final Applicable<B, C> function) {
    return new AbstractReduction<A, C>() {
      public Accumulator<A, C> newAccumulator() {
        final Accumulator<A, B> start = AbstractReduction.this.newAccumulator();
        return new Accumulator<A, C>() {
          public void accumulate(A element) {
            start.accumulate(element);
          }

          public C value() {
            return function.apply(start.value());
          }
        };
      }
    };
  }

  @Override
  public final <C> Reduction<C, B> of(final Applicable<C, A> function) {
    return new AbstractReduction<C, B>() {
      public Accumulator<C, B> newAccumulator() {
        final Accumulator<A, B> accum = AbstractReduction.this.newAccumulator();
        return new Accumulator<C, B>() {
          public void accumulate(C element) {
            accum.accumulate(function.apply(element));
          }

          public B value() {
            return accum.value();
          }
        };
      }
    };
  }

}
