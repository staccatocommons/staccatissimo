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

import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.reductions.AbstractReduction;

public final class Sum extends AbstractReduction<Integer, Integer> {
  public Accumulator<Integer, Integer> start() {
    return new Accumulator<Integer, Integer>() {
      private int i = 0;

      public void accumulate(Integer element) {
        i += element;
      }

      public Integer value() {
        return i;
      }
    };
  }
}