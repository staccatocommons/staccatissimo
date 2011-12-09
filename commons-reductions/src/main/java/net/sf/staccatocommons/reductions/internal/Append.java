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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.reductions.AbstractReduction;

/**
 * Append reduction
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class Append<A> extends AbstractReduction<A, List<A>> {
  public Accumulator<A, List<A>> newAccumulator() {
    return new Accumulator<A, List<A>>() {
      private List<A> list = new LinkedList<A>();

      public void accumulate(A element) {
        list.add(element);
      }

      public List<A> value() {
        return Collections.unmodifiableList(list);
      }
    };
  }
}