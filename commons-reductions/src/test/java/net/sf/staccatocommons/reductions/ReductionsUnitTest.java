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
package net.sf.staccatocommons.reductions;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.defs.reduction.Reduction;
import net.sf.staccatocommons.util.Strings;

import org.junit.Test;

/**
 * @author flbulgarelli
 */
public class ReductionsUnitTest {

  /**
   * Test method for {@link Reductions#count()}.
   */
  @Test
  public void count() {
    assertEquals(3, reduceSomeIntegers(Reductions.<Integer> count()));
  }

  /**
   * Test method for {@link Reductions#append()}.
   */
  @Test
  public void append() {
    Accumulator<Object, List<Object>> accum = Reductions.append().start();
    accum.accumulate("foo");
    accum.accumulate("bar");
    List<Object> result = accum.value();
    assertEquals(2, result.size());
    assertEquals(0, result.indexOf("foo"));
    assertEquals(1, result.indexOf("bar"));
  }

  /**
   * Test method for {@link Reductions#append()}.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void appendAnswersUnmodifiableList() {
    Accumulator<Object, List<Object>> accum = Reductions.append().start();
    accum.accumulate("foo");
    accum.value().set(0, "");
  }

  /**
   * Test method for {@link Reductions#sum()}.
   */
  @Test
  public void testSum() {
    assertEquals(113, reduceSomeIntegers(Reductions.sum()));
  }

  /**
   * Test method for {@link Reductions#max()}.
   */
  @Test
  public void testMax() {
    assertEquals(63, reduceSomeIntegers(Reductions.<Integer> max()));
  }

  /**
   * Test method for {@link Reductions#min()}.
   */
  @Test
  public void testMin() {
    assertEquals(10, reduceSomeIntegers(Reductions.<Integer> min()));
  }

  protected int reduceSomeIntegers(Reduction<Integer, Integer> reduction) {
    Accumulator<Integer, Integer> accum = reduction.start();
    accum.accumulate(40);
    accum.accumulate(63);
    accum.accumulate(10);
    return accum.value();
  }

  /**
   * Test for {@link Reductions#sumOn(net.sf.staccatocommons.defs.Applicable)}
   * 
   * @throws Exception
   */
  @Test
  public void sumOn() throws Exception {
    assertEquals(16, reduceSomeStrings(Reductions.sumOn(Strings.length())));
  }

  /**
   * test for {@link Reduction#of(net.sf.staccatocommons.defs.Applicable)}
   * 
   * @throws Exception
   */
  @Test
  public void ofComposition() throws Exception {
    assertEquals(8, reduceSomeStrings(Reductions.<Integer> max().of(Strings.length())));
  }

  protected int reduceSomeStrings(Reduction<CharSequence, Integer> reduction) {
    Accumulator<CharSequence, Integer> accum = reduction.start();
    accum.accumulate("hello");
    accum.accumulate("foo");
    accum.accumulate("Bye Bye!");
    return accum.value();
  }

}
