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

import java.util.Iterator;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;

/**
 * @author flbulgarelli
 * 
 */
public abstract class NextGetWrapperStream<A> extends WrapperStream<A> {

  private Iterator<A> iter;

  /**
   * Creates a new {@link NextGetWrapperStream}
   */
  public NextGetWrapperStream(Stream<A> source) {
    super(source);
  }

  public final Thriterator<A> iterator() {
    if (iter == null)
      iter = nextGetIterator();
    return Thriterators.from(iter);
  }

  protected abstract Iterator<A> nextGetIterator();

}
