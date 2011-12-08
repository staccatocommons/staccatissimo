/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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

package net.sf.staccatocommons.lang;

import static net.sf.staccatocommons.lang.Option.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Test;

/**
 * Test for {@link Option}
 * 
 * @author flbulgarelli
 */
public class OptionUnitTest extends JUnit4MockObjectTestCase {
  /***/
  @Test
  public void testValuedefined() {
    Integer i = 5;
    assertSame(i, some(i).value());
  }

  /***/
  @Test(expected = NoSuchElementException.class)
  public void testValueundefined() {
    none().value();
  }

  /***/
  @Test
  public void testIsDefined() {
    assertTrue(some(5).isDefined());
    assertTrue(some(null).isDefined());
    assertFalse(none().isDefined());
  }

  /***/
  @Test
  public void testIsUndefined() {
    assertFalse(some(5).isUndefined());
    assertFalse(some(null).isUndefined());
    assertTrue(none().isUndefined());
  }

  /***/
  @Test
  public void testIsEmpty() {
    assertFalse(some(5).isEmpty());
    assertFalse(some(null).isEmpty());
    assertTrue(none().isEmpty());
  }

  /***/
  @Test
  public void testSize() {
    assertEquals(1, some(5).size());
    assertEquals(0, none().size());
  }

  /** Test for {@link Option#valueOrNull()} */
  @Test
  public void testValueOrNull() {
    assertNull(none().valueOrNull());
    assertNotNull(some("Hello").valueOrNull());
  }

  /**
   * Test for {@link Option#valueOrElse(Object)} and
   * {@link Option#valueOrElse(Thunk)}
   * 
   * @throws Exception
   */
  @Test
  public void testValueOrElse() throws Exception {
    assertEquals(4, (int) Option.some(4).valueOrElse(8));
    assertEquals(4, (int) Option.some(4).valueOrElse(Thunks.constant(9)));
    assertEquals(9, (int) Option.<Integer> none().valueOrElse(Thunks.constant(9)));
    assertEquals(9, (int) Option.<Integer> none().valueOrElse(Thunks.constant(9)));
  }

  /** Test for Option#nullTo */
  @Test
  public void testNullToNone() {
    assertEquals(none(), nullToNone(null));
    assertEquals(some("String"), nullToNone("String"));
  }

  /***/
  @Test
  public void testEqualty() throws Exception {
    assertEquals(some(5), some(5));
    assertSame(none(), none());
    assertSame(some(null), someNull());
    assertEquals(new Some<Integer>(5), new Some<Integer>(5));
    assertEquals(new Some<Integer>(null), new Some<Integer>(null));
  }

  /**
   * Test for {@link Option#ifDefined(Executable)}
   * 
   * @throws Exception
   */
  @Test
  public void testIfDefined() throws Exception {
    final Executable<String> block = mock(Executable.class);
    checking(new Expectations() {
      {
        one(block).exec("foo");
      }
    });
    Option.some("foo").ifDefined(block);
    Option.<String> none().ifDefined(block);
  }

  /** Test method for {@link Option#contains(Object)} */
  @Test
  public void testContains() throws Exception {
    assertTrue(Option.some(4).contains(4));
    assertFalse(Option.some(null).contains(4));
    assertFalse(Option.some(5).contains(4));
    assertFalse(Option.none().contains(4));
  }

  /** Test method {@link Option#toString()} */
  @Test
  public void testToString() throws Exception {
    assertEquals("None", Option.none().toString());
    assertEquals("Some(foo)", Option.some("foo").toString());
    assertEquals("Some(null)", Option.someNull().toString());
  }

  /**
   * Test for {@link Option#map(net.sf.staccatocommons.defs.Applicable)}
   * 
   * @throws Exception
   */
  @Test
  public void mapNoneIsNone() throws Exception {
    assertTrue(Option.none().map(Functions.identity()).isUndefined());
  }

  /**
   * Test for {@link Option#map(net.sf.staccatocommons.defs.Applicable)}
   * 
   * @throws Exception
   */
  @Test
  public void mapSomeIsSome() throws Exception {
    assertTrue(Option.some(10).map(Functions.identity()).isDefined());
  }

  /**
   * Test for {@link Option#filter(net.sf.staccatocommons.defs.Evaluable)}
   * 
   * @throws Exception
   */
  @Test
  public void filterNoneIsNone() throws Exception {
    assertTrue(Option.none().filter(Predicates.true_()).isUndefined());
  }

  /**
   * Test for {@link Option#filter(net.sf.staccatocommons.defs.Evaluable)}
   * 
   * @throws Exception
   */
  @Test
  public void filterSomeIsSomeIfPredicateIsTrue() throws Exception {
    assertTrue(Option.some(10).filter(Compare.greaterThan(9)).isDefined());
  }

  /**
   * Test for {@link Option#filter(net.sf.staccatocommons.defs.Evaluable)}
   * 
   * @throws Exception
   */
  @Test
  public void filterSomeIsNoneIfPredicateIsFalse() throws Exception {
    assertTrue(Option.some(10).filter(Compare.greaterThan(90)).isUndefined());
  }

}