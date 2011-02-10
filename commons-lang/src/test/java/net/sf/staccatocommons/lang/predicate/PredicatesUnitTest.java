/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.predicate;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.regex.Pattern;

import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.Strings;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link Predicates}
 * 
 * @author flbulgarelli
 */
public class PredicatesUnitTest extends JUnit4MockObjectTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicates#true_()}.
	 */
	@Test
	public void testTrue_() {
		Predicate<Object> true_ = Predicates.true_();
		assertTrue(true_.eval(new Object()));
		assertSame(true_, true_.or(mock(Evaluable.class)));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicates#false_()}.
	 */
	@Test
	public void testFalse_() {
		Predicate<Object> false_ = Predicates.false_();
		assertFalse(false_.eval(new Object()));
		assertSame(false_, false_.and(mock(Evaluable.class)));

	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicates#notNull()}.
	 */
	@Test
	public void testNotNull() {
		assertTrue(Predicates.notNull().eval(new Object()));
		assertFalse(Predicates.notNull().eval(null));
		assertSame(Predicates.notNull(), Predicates.null_().not());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicates#null_()}.
	 */
	@Test
	public void testNull() {
		assertFalse(Predicates.null_().eval(new Object()));
		assertTrue(Predicates.null_().eval(null));
		assertSame(Predicates.null_(), Predicates.notNull().not());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicates#equal(java.lang.Object)}
	 * .
	 */
	@Test
	public void testEqual() {
		assertTrue(Predicates.equal(5).eval(Integer.parseInt("5")));
		assertFalse(Predicates.equal(5).eval(6));
		assertFalse(Predicates.equal(6).not().eval(6));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicates#same(java.lang.Object)}
	 * .
	 */
	@Test
	public void testSame() {
		Integer i = new Integer(5);
		Integer i2 = new Integer(5);
		assertFalse(Predicates.same(i).eval(i2));
		assertTrue(Predicates.same(i).eval(i));

	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.Strings#equalsIgnoreCase(java.lang.String)}
	 * .
	 */
	@Test
	public void testEqualsIgnoreCase() {
		assertTrue(Strings.equalsIgnoreCase("Hello").eval("hello"));
		assertFalse(Strings.equalsIgnoreCase("Hello").eval("world"));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.Strings#matches(java.lang.String)} .
	 */
	@Test
	public void testMatchesRegexp() {
		assertTrue(Strings.matches("[Hh]el+o").eval("hello"));
		assertFalse(Strings.matches("[Hh]el+o").eval("world"));
	}

	/***/
	@Test
	public void testMatchesPattern() throws Exception {
		assertTrue(Strings.matches(Pattern.compile("[Hh]el+o")).eval("hello"));
		assertFalse(Strings.matches(Pattern.compile("[Hh]el+o")).eval("world"));
	}

	/***/
	@Test
	public void testConstains() {
		assertTrue(Strings.contains("foo").eval("The word foo has no special meaning"));
		assertFalse(Strings.contains("foo").eval("The word bar has no special meaning, neither"));
	}

	/**
	 * Test for not in particular predicates that have optimizations on that
	 * method
	 */
	@Test
	public void testNot() throws Exception {

		assertTrue(Predicates.false_().not().eval(5));
		assertFalse(Predicates.false_().not().not().eval(5));
	}

	/***/
	@Test
	public void testGreatherThan() throws Exception {
		assertFalse(Compare.greaterThan(5).eval(2));
		assertFalse(Compare.greaterThan(5).eval(5));
		assertTrue(Compare.greaterThan(5).eval(6));
	}

	/***/
	@Test
	public void testLowerThan() throws Exception {
		assertTrue(Compare.lessThan(5).eval(2));
		assertFalse(Compare.lessThan(5).eval(5));
		assertFalse(Compare.lessThan(5).eval(6));
	}

	/***/
	@Test
	public void testAny() throws Exception {
		assertTrue(Predicates.any(
			Predicates.<Integer> true_(),
			Compare.lessThan(2),
			Predicates.equal(5)).eval(5));

		assertTrue(Predicates.any(
			Predicates.<Integer> false_(),
			Compare.greaterThan(2),
			Predicates.equal(10)).eval(5));
	}

	/**
	 * Test that Predicates.from return a new predicates that forwards to the
	 * given evaluable
	 */
	@Test
	public void testFrom() {
		final Evaluable<Object> evaluable = mock(Evaluable.class);
		Predicate<Object> from = Predicates.from(evaluable);
		final Object arg = new Object();

		checking(new Expectations() {
			{
				one(evaluable).eval(arg);
				will(returnValue(true));
			}
		});

		assertTrue(from.eval(arg));
	}

	/***/
	@Test
	public void testFromPredicate() throws Exception {
		Predicate<Object> true_ = Predicates.true_();
		assertSame(true_, Predicates.from(true_));
	}

	/***/
	@Test
	public void testAll() throws Exception {
		assertFalse(Predicates.all(
			Predicates.<Integer> true_(),
			Predicates.<Integer> false_(),
			Predicates.equal(5)).eval(5));

		assertTrue(Predicates.all(
			Predicates.<Integer> true_(),
			Compare.greaterThan(2),
			Predicates.equal(5)).eval(5));
	}

	/**
	 * Test method for {@link Predicates#in(java.util.Collection)} and
	 * {@link Predicates#in(Object...)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIn() throws Exception {
		assertTrue(Predicates.in(4, 9, 50).eval(9));
		assertFalse(Predicates.in(4, 9, 50).eval(60));

		assertTrue(Predicates.in(Arrays.asList("hello", "world")).eval("hello"));
		assertFalse(Predicates.in(Arrays.asList("hello", "world")).eval("bye"));

		assertFalse(Predicates.in().eval(new Object()));
	}

}
