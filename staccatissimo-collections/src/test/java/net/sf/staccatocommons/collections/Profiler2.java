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
package net.sf.staccatocommons.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.util.Strings;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class Profiler2 {
  /**
   * 
   */
  private static final int ITERATIONS = 10000000;

  // 2.7
  // 2.4
  // 3.8

  static {
    Streams.cons(1, 2, 3, 6, 4, 8);
    System.gc();
  }

  @Test
  public void testName2() throws Exception {
    for (int i = 0; i < ITERATIONS; i++) {
      List<String> l2 = new LinkedList<String>();
      for (String e : Arrays.asList("hello", "world", "foo", "bar", "naz", "foobar", "foobaz")) {
        if (e.startsWith("foo"))
          l2.add(e);
      }
      Collections.max(l2);
    }
  }

  @Test
  public void testName3() throws Exception {
    for (int i = 0; i < ITERATIONS; i++)
      Collections.max(Iterables.filter(
        Arrays.asList("hello", "world", "foo", "bar", "naz", "foobar", "foobaz"),
        Strings.startsWith("foo")));
  }

  @Test
  public void testName4() throws Exception {
    for (int i = 0; i < ITERATIONS; i++)
      Streams
        .cons("hello", "world", "foo", "bar", "naz", "foobar", "foobaz")
        .filter(Strings.startsWith("foo"))
        .maximum();
  }
}
