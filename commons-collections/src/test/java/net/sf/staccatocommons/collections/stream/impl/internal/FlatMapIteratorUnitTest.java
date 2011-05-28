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

import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.testing.junit.theories.IterableTheories;

/**
 * @author flbulgarelli
 * 
 */
public class FlatMapIteratorUnitTest extends IterableTheories {

  protected Iterable<?> createOneElementIterable() {
    return Streams.cons(1).flatMap(Functions.constant(Streams.cons(10)));
  }

  protected Iterable<?> createTwoElementsIterable() {
    return Streams.cons(1, 2).flatMap(Functions.constant(Streams.cons(10)));
  }

}
