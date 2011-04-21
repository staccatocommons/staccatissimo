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

package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.lang.tuple.Pair;

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
    Pair<A, Stream<A>> decons = getStream().decons();
    return function.apply(decons._0(), decons._1());
  }
}