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

package net.sf.staccatocommons.collections.stream.internal.algorithms;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.tuple.Tuple2;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <A>
 */
public final class DeconsTransformStream<B, A> extends AbstractTransformStream<A, B> {
  private final DeconsApplicable<A, B> function;

  /**
   * Creates a new {@link DeconsTransformStream}
   */
  public DeconsTransformStream(Stream<A> stream, DeconsApplicable<A, B> function) {
    super(stream);
    this.function = function;
  }

  @Override
  protected Stream<B> apply() {
    if (getStream().isEmpty())
      return function.emptyApply();
    Tuple2<A, Stream<A>> decons = getStream().decons();
    return function.apply(decons._0(), decons._1());
  }
}