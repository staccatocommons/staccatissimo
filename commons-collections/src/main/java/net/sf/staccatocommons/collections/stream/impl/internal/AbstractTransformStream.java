/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.List;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 */
public abstract class AbstractTransformStream<A, B> extends AbstractStream<B> {

  private final Stream<A> stream;

  /**
   * Creates a new {@link AbstractTransformStream}
   */
  public AbstractTransformStream(@NonNull Stream<A> stream) {
    this.stream = stream;
  }

  @Override
  public final Thriterator<B> iterator() {
    return apply().iterator();
  }

  @Override
  public final B get(int n) {
    return apply().get(n);
  }

  @Override
  public final Stream<B> toEmptyAware() {
    return apply().toEmptyAware();
  }

  @Override
  public final Pair<Thunk<B>, Stream<B>> delayedDecons() {
    return apply().delayedDecons();
  }

  @Override
  public final boolean isEmpty() {
    return apply().isEmpty();
  }

  @Override
  public final List<B> toList() {
    return apply().toList();
  }

  protected abstract Stream<B> apply();

  /**
   * @return the stream
   */
  protected Stream<A> getStream() {
    return stream;
  }

}
