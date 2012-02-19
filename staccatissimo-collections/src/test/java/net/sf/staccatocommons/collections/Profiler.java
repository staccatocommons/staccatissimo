/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.util.Strings;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class Profiler {
  /**
   * 
   */
  private static final int ITERATIONS = 20000000;
  private static final String[] arr = Arrs.from(
    "hello",
    "world",
    "foo",
    "bar",
    "naz",
    "bye",
    "byebye",
    "blah",
    "blah",
    "blah",
    "blah",
    "foobar",
    "foobaz",
    "foobaz",
    "foobaz",
    "foobaz",
    "hello",
    "world",
    "foo",
    "naz",
    "bye",
    "byebye",
    "blah",
    "blah",
    "blah",
    "blah",
    "foobar",
    "foobaz",
    "bar",
    "naz",
    "foobar",
    "foobaz",
    "foobaz",
    "foobaz",
    "foobaz");
  // 2.7
  // 2.4
  // 3.8

  static {
    Streams.cons(1, 2, 3, 6, 4, 8);
  }

  @Test
  public void testName2() throws Exception {
    List<String> asList = Arrays.asList(arr);
    for (int i = 0; i < ITERATIONS; i++) {
      List<Integer> l2 = new LinkedList<Integer>();
      for (String e : asList) {
        if (e.startsWith("foo"))
          l2.add(e.length());
      }
      Collections.max(l2);
    }
  }

  @Test
  public void testName3() throws Exception {
    List<String> list = Arrays.asList(arr);
    Function<CharSequence, Integer> length = Strings.length();
    for (int i = 0; i < ITERATIONS; i++) {
      Collections.max(Iterables.map(Iterables.filter(list, Strings.startsWith("foo")), length));
    }
  }

  @Test
  public void testName4() throws Exception {
    Stream<String> stream = Streams.cons(arr);
    Function<CharSequence, Integer> length = Strings.length();
    for (int i = 0; i < ITERATIONS; i++) {
      stream.filter(Strings.startsWith("foo")).map(length).maximum();
    }

  }
}
