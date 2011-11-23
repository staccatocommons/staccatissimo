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

import static java.lang.Math.min;
import net.sf.staccatocommons.collections.internal.iterator.ZipIterator;
import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <C>
 * @param <A>
 * @param <B>
 */
public final class ZipStream<C, A, B> extends AbstractStream<C> {
  private final Iterable<B> iterable;
  private final Function2<A, B, C> function;
  private final Stream<A> stream;

  /**
   * Creates a new {@link ZipStream}
   */
  public ZipStream(@NonNull AbstractStream<A> abstractStream, @NonNull Iterable<B> iterable,
    @NonNull Function2<A, B, C> function) {
    this.iterable = iterable;
    this.function = function;
    this.stream = abstractStream;
  }

  @Override
  public Thriterator<C> iterator() {
    return new ZipIterator(stream.iterator(), Thriterators.from(iterable.iterator()), function);
  }

  @Override
  public int size() {
    return min(stream.size(), Iterables.size(iterable));
  }

  @Override
  public boolean isEmpty() {
    return stream.isEmpty() || Iterables.isEmpty(iterable);
  }

}