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
package net.sf.staccatocommons.collections.stream.impl.internal.delayed;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.internal.WrapperStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.delayed.DelayedAppendIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class DelayedAppendStream<A> extends WrapperStream<A> {

  private Thunk<A> element;

  /**
   * Creates a new {@link DelayedAppendStream}
   */
  public DelayedAppendStream(@NonNull Stream<A> source, @NonNull Thunk<A> element) {
    super(source);
    this.element = element;
  }

  @Override
  public Thriterator<A> iterator() {
    return new DelayedAppendIterator(getSource().iterator(), element);
  }

}
