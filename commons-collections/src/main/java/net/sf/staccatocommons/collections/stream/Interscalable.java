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
package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.collections.restrictions.Projection;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Interface for interscalating elements in a stream
 * 
 * @author flbulgarelli
 * 
 */
public interface Interscalable<A> {

  /**
   * Inserts the given <code>element</code> between each retrieved element of
   * this {@link Stream}
   * 
   * @param element
   * @return a new {@link Stream}
   */
  @Projection
  Stream<A> intersperse(A element);

  @Projection
  Stream<A> incorporate(@NonNull Function<? super A, ? extends A> function);

  @Projection
  Stream<A> incorporate(@NonNull A element);

}
