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


package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.collections.stream.internal.algorithms.MemoizedStream;
import net.sf.staccatocommons.testing.junit.theories.IterableTheories;

/**
 * @author flbulgarelli
 * 
 */
public class MemoizedIteratorUnitTest extends IterableTheories {

  protected Iterable<?> createTwoElementsIterable() {
    return new MemoizedStream(Streams.from(Arrays.asList(10, 20).iterator()).iterator());
  }

  protected Iterable<?> createOneElementIterable() {
    return new MemoizedStream(Streams.cons(10).iterator());
  }

}
