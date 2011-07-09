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

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.testing.junit.theories.IterableTheories;

/**
 * @author flbulgarelli
 * 
 */
public class MemorizedIteratorUnitTest extends IterableTheories {

  protected Iterable<?> createTwoElementsIterable() {
    return new MemorizedStream(Streams.from(Arrays.asList(10, 20).iterator()).iterator());
  }

  protected Iterable<?> createOneElementIterable() {
    return new MemorizedStream(Streams.cons(10).iterator());
  }

}
