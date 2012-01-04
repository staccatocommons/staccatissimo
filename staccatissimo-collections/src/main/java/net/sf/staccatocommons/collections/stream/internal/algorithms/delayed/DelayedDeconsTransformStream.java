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

package net.sf.staccatocommons.collections.stream.internal.algorithms.delayed;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.internal.algorithms.AbstractTransformStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.tuple.Tuple2;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class DelayedDeconsTransformStream<A, B> extends AbstractTransformStream<A, B> {
  private final DelayedDeconsApplicable<A, B> function;

  /**
   * Creates a new {@link DelayedDeconsTransformStream}
   */
  public DelayedDeconsTransformStream(Stream<A> stream, DelayedDeconsApplicable<A, B> function) {
    super(stream);
    this.function = function;
  }

  protected Stream<B> apply() {
    if (getStream().isEmpty())
      return function.emptyApply();
    Tuple2<Thunk<A>, Stream<A>> decons = getStream().delayedDecons();
    return function.apply(decons._0(), decons._1());
  }
}