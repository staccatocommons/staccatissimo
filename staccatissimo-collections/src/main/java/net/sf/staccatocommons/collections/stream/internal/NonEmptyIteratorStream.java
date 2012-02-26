/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.stream.internal;

import java.util.Iterator;

/**
 * @author flbulgarelli
 * 
 */
public final class NonEmptyIteratorStream<A> extends IteratorStream<A> {

  /**
   * Creates a new {@link NonEmptyIteratorStream}
   */
  public NonEmptyIteratorStream(Iterator<? extends A> iterator) {
    super(iterator);
  }
  
  public boolean isEmpty() {
    return false;
  }

}
