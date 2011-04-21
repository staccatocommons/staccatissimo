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

import static net.sf.staccatocommons.lambda.Lambda.*;
import static net.sf.staccatocommons.lang.number.NumberTypes.*;
import static net.sf.staccatocommons.lang.predicate.Predicates.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.block.Block;
import net.sf.staccatocommons.lang.predicate.Predicates;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author flbulgarelli
 */
@RunWith(Theories.class)
public abstract class StreamTheories {

  /** predicates for testing */
  @DataPoints
  public static Predicate[] predicates = new Predicate[] { Predicates.equal(5),
      isInstanceOf(Integer.class).and(Compare.greaterThan(90)), //
      false_(), //
      true_(), //
      notNull(), //
      equal(26), //
  };

  /** Sizes for testing */
  @DataPoints
  public static int[] sizes = new int[] { 0, 1, 4, 90 };

  private boolean emptyImpossible;

  /**
   * Test method for {@link AbstractStream#take(int)} .
   */
  @Theory
  public final void take(int size, Stream<?> stream) {
    assertTrue(stream.take(size).size() <= size);
  }

  /**
   * Test method for {@link AbstractStream#fold(java.lang.Object, Applicable2)}
   * .
   */
  @Theory
  public final void reduce_OnEmpty(Stream<Integer> stream) {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream argument) {
        try {
          argument.reduce(integer().add());
          fail();
        } catch (NoSuchElementException e) {
          // ok;
        }
      }
    });
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#anyOrNone()}
   * .
   * 
   * @param stream
   */
  @Theory
  public final void anyOrNone_NotEmpty(Stream<?> stream) {
    assumeTrue(!stream.isEmpty());
    assertTrue(stream.anyOrNone().isDefined());
  }

  /**
   * Test method for {@link AbstractStream#find(Evaluable)} .
   */
  @Theory
  @Test(expected = NoSuchElementException.class)
  public final void find_NoSuchElement(Stream<?> stream) {
    stream.find(Predicates.false_());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.collections.stream.AbstractStream#all(net.sf.staccatocommons.defs.Evaluable)}
   * .
   * 
   * @param stream
   */
  @Theory
  public final void all_NotEmpty(Stream<?> stream) {
    assumeTrue(!stream.isEmpty());
    assertTrue(stream.all(Predicates.true_()));
  }

  /** Test for any */
  @Theory
  public final <A> void emptyStreamSatisfyAnyPredicate(Stream<A> stream, final Evaluable<A> predicate) {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream stream) {
        assertTrue(stream.all(predicate));
      }
    });
  }

  /**
   * Test method for {@link AbstractStream#any(Evaluable)}
   */
  @Theory
  public final <A> void anyIsTrueForTruePredicateInNonEmptyStream(Stream<A> stream) {
    assumeTrue(!stream.isEmpty());
    assertTrue(stream.any(Predicates.true_()));
  }

  /** Test that any in an empty stream returns false always */
  @Theory
  public final <A> void emptyStreamsSatisfyNoPredicate(Stream<A> stream, final Evaluable<A> predicate) {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream stream) {
        assertFalse(stream.any(predicate));
      }
    });
  }

  /** Test that empty streams have no any element */
  @Theory
  public final void anyOrNullEqualsNullInEmptyStream(Stream<?> stream) {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream stream) {
        assertNull(stream.anyOrNull());
      }
    });

  }

  /** Test that decons succeeds alwas on non empty streams */
  @Theory
  public final <A> void deconsSuceedsInNonEmptyStream(Stream<A> stream) throws Exception {
    assumeTrue(!stream.isEmpty());
    assertNotNull(stream.decons());
  }

  /** test that decons fails on empty streams */
  @Theory
  public final <A> void deconsFailsOnEmptyStream(Stream<A> stream) throws Exception {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream stream) {
        try {
          stream.decons();
          fail();
        } catch (NoSuchElementException e) {
          // ok
        }
      }
    });
  }

  /** Test that empty streams have no tail */
  @Theory
  public void tailFailsInEmptyStream(Stream<?> stream) {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream stream) {
        try {
          stream.tail();
          fail();
        } catch (NoSuchElementException e) {
          // ok
        }
      }
    });
  }

  /** Test that empty streams have no head */
  @Theory
  public void headFailsInEmptyStream(Stream<?> stream) {
    assumeEmpty(stream, new Block<Stream>() {
      public void exec(Stream stream) {
        try {
          stream.head();
          fail();
        } catch (NoSuchElementException e) {
          // ok
        }
      }
    });
  }

  /** Tests that if **/
  @Theory
  public void toEmptyAwareMakesIsEmptyRepeatable(Stream<?> stream) throws Exception {
    assertTrue(Streams
      .repeat(stream.toEmptyAware())
      .map(lambda((Boolean) $(EmptyAware.class).isEmpty()))
      .take(10)
      .allEquiv());
  }

  /** Tests that memorizing grants repeatable iteration order */
  @Theory
  public <A> void memorizeGrantsRepeatableIterationOrder(Stream<A> stream) throws Exception {
    Stream<A> memorized = stream.memorize();
    assertEquals(memorized.isEmpty(), memorized.isEmpty());
    assertTrue(memorized.equiv(memorized));
  }

  /** Ignores test that require empty streams */
  public void emptyIsImpossible() {
    emptyImpossible = true;
  }

  protected void assumeEmpty(Stream<?> stream, Block<Stream> block) {
    if (emptyImpossible)
      return;
    assumeTrue(stream.isEmpty());
    block.exec(stream);
  }
}
