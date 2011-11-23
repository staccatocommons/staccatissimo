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
package net.sf.staccatocommons.collections.stream.impl;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.StreamTheories;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.iterators.EmptyThriterator;
import net.sf.staccatocommons.lang.tuple.Pair;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;

/**
 * @author flbulgarelli
 * 
 */
public class IteratorStreamUnitTest extends StreamTheories {

  /** data point */
  @DataPoint
  public static Stream emptyIter() {
    return Streams.from(EmptyThriterator.empty());
  }

  /** data point */
  @DataPoint
  public static Stream oneElementsIter() {
    return Streams.from(Arrays.asList(90, 50, 60, 230).iterator());
  }

  /** data point */
  @DataPoint
  public static Stream twoElementsIter() {
    return Streams.from(Collections.singletonList(90).iterator());
  }

  /**
   * Test method for {@link IteratorStream#decons()}.
   */
  @Test
  public void testDecons() {
    Pair<Object, Stream<Object>> decons = Streams.from(Arrays.asList(90, 50, "foo").iterator()).decons();
    assertEquals(90, decons._0());
    assertThat(decons._1(), instanceOf(IteratorStream.class));
    assertEquals(Arrays.asList(50, "foo"), decons._1().toList());
  }

  /**
   * Test method for {@link IteratorStream#IteratorStream(java.util.Iterator)}.
   */
  @Test
  public void testIteratorStream() {
    assertEquals(Arrays.asList(90, 56, 60), Streams.from(Arrays.asList(90, 56, 60).iterator()).toList());

    Set<String> set = Iterables.toSet(Arrays.asList("foo", "bar", "baz", "foobar"));
    assertEquals(set, Streams.from(set).toSet());
  }
}
