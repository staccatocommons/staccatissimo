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

package net.sf.staccatocommons.collections.stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.lang.thunk.internal.NullThunk;
import net.sf.staccatocommons.reductions.Reductions;
import net.sf.staccatocommons.util.Strings;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author flbulgarelli
 * 
 */
@RunWith(Theories.class)
public abstract class RepetableStreamTheories extends StreamTheories {

  /** Functions data points */
  @DataPoints
  public static final Function[] FUNCTIONS = new Function[] { Functions.constant(59),
      Functions.identity(), Strings.toString_() };

  /** thunks data points */
  @DataPoints
  public static final Thunk<Integer>[] THUNKS = new Thunk[] { NullThunk.null_(), //
      Thunks.constant(90) //
  };
  
  @Theory
  public void ifContainsBeforeThenReferencesIsNotContainedInFirstSegment(Stream<Integer> stream, int element, int next) throws Exception {
    assumeTrue(stream.containsBefore(element, next));
    assertFalse(stream.takeWhile(Predicates.equal(element).not()).contains(next));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#filter(net.sf.staccatocommons.defs.Evaluable)}
   * .
   * 
   * @param stream
   * @param predicate
   */
  @Theory
  public void filteredSizeLowerOrEqualToOriginal(Stream stream, Evaluable predicate) {
    assertThat(stream.filter(predicate).size(), lessThanOrEqualTo(stream.size()));
  }

  /**
   * Test that all elements from a filter stream stasify the filtering condition
   */
  @Theory
  public void allFilteredElementsEvaluate(Stream stream, Evaluable predicate) {
    assertTrue(stream.filter(predicate).all(predicate));
  }

  /**
   */
  @Theory
  public void nonEmptyStreamContainsAny(Stream stream) {
    assumeTrue(!stream.isEmpty());
    assertTrue(stream.toList().contains(stream.any()));
  }

  /**
   * Test method for {@link AbstractStream#anyOrNull()} .
   * 
   * @param stream
   */
  @Theory
  public final void anyEqualsAnyOrNullInNonEmpty(Stream stream) {
    assumeTrue(!stream.isEmpty());
    assertEquals(stream.any(), stream.anyOrNull());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#find(net.sf.staccatocommons.defs.Evaluable)}
   * .
   * 
   * @param stream
   */
  @Theory
  @Test(expected = NoSuchElementException.class)
  public void findFailsForFalsePredicate(Stream stream) {
    stream.find(Predicates.false_());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#findOrNull(net.sf.staccatocommons.defs.Evaluable)}
   * .
   * 
   * @param stream
   * @param predicate
   */
  @Theory
  public void testFindOrNull(Stream stream, Evaluable predicate) {
    assertEquals(stream.findOrNone(predicate).valueOrNull(), stream.findOrNull(predicate));
  }

  /**
   * Test method for {@link AbstractStream#findOrElse(Evaluable, Thunk)} .
   * 
   * @param stream
   * @param predicate
   * @param thunk
   */
  @Theory
  public void testFindOrElse(Stream stream, Evaluable predicate, Thunk thunk) {
    assertEquals(
      stream.findOrNone(predicate).valueOrElse(thunk),
      stream.findOrElse(predicate, thunk));
  }

  /**
   * Test method for
   * {@link AbstractStream#map(net.sf.staccatocommons.defs.Applicable)}
   * 
   * @param stream
   * @param function
   */
  @Theory
  public void sizeStreamReturnedByMapSizeIsTheSameOriginalStream(Stream stream, Function function) {
    assertEquals(stream.size(), stream.map(function).size());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#first()}.
   * 
   * @param stream
   */
  @Theory
  public void firstHeadGetZeroAnyAreEquivalent(Stream stream) {
    assumeThat(stream.size(), greaterThan(0));
    assertEquals(stream.any(), stream.head());
    assertEquals(stream.first(), stream.head());
    assertEquals(stream.get(0), stream.first());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#second()}.
   */
  @Theory
  public void secondAndGetOneAreEquivalent(Stream stream) {
    assumeThat(stream.size(), greaterThan(1));
    assertEquals(stream.get(1), stream.second());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#third()}.
   * 
   * @param stream
   */
  @Theory
  public void thirdAndGetTwoAreEquivalent(Stream stream) {
    assumeThat(stream.size(), greaterThan(2));
    assertEquals(stream.get(2), stream.third());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#last()}.
   * 
   * @param stream
   */
  @Theory
  public void testLast(Stream stream) {
    assumeTrue(!stream.isEmpty());
    assertEquals(stream.get(stream.size() - 1), stream.last());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#get(int)}.
   * 
   * @param stream
   */
  @Theory
  @Test(expected = IndexOutOfBoundsException.class)
  public void gettingElementOutOfBoundsThrowsIndexOutOfBoundException(Stream stream) {
    stream.get(stream.size());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toSet()}.
   * 
   * @param stream
   */
  @Theory
  public void testToSet(Stream stream) {
    assertEquals(Iterables.toSet(stream), stream.toSet());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toList()}.
   * 
   * @param stream
   */
  @Theory
  public <A> void testToList(Stream<A> stream) {
    assertEquals(Iterables.toList(stream), stream.toList());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toList()}.
   * 
   * @param stream
   */
  @Theory
  public <A> void testContains(final Stream<A> stream) {
    assertTrue(stream.all(new AbstractPredicate<A>() {
      @Override
      public boolean eval(A argument) {
        return stream.contains(argument);
      }
    }));
  }

  /** Test for {@link Stream#equiv(Iterable)} */
  @Theory
  public <A> void testElementEqual(Stream<A> stream) throws Exception {
    assertTrue(stream.equiv(stream));
    assertTrue(stream.equiv(stream.force()));
    assertTrue(stream.equiv(stream.toList()));
    assertTrue(stream.equiv(stream.toArray(Object.class)));
  }

  /** Test method for {@link AbstractStream#zip(Iterable)} */
  @Theory
  public <A, B> void testZip(Stream<A> stream, Iterable<B> iterable) throws Exception {
    assertEquals(Iterables.zip(stream, iterable), stream.zip(iterable).toList());
  }

  /***
   * Theory for reduction and equivalence
   */
  @Theory
  public <A> void reduceToListIsEquivalentToItself(Stream<A> stream) throws Exception {
    assertTrue(stream.equiv(stream.reduce(Reductions.<A> append())));
  }

  /** Theory for countOf and filter */
  @Theory
  public <A> void countOfIsEquivalentToFilterAndSize(Stream<A> stream, Evaluable<A> predicate)
    throws Exception {
    assertEquals(stream.countOf(predicate), stream.filter(predicate).size());
  }
}
