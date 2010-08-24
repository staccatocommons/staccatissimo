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
package net.sf.staccato.commons.lang.predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class PredicatesUnitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#true_()}.
	 */
	@Test
	public void testTrue_() {
		assertTrue(Predicates.true_().eval(new Object()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#false_()}.
	 */
	@Test
	public void testFalse_() {
		assertFalse(Predicates.false_().eval(new Object()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#notNull()}.
	 */
	@Test
	public void testNotNull() {
		assertTrue(Predicates.notNull().eval(new Object()));
		assertFalse(Predicates.notNull().eval(null));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#equal(java.lang.Object)}
	 * .
	 */
	@Test
	public void testEqual() {
		assertTrue(Predicates.equal(5).eval(Integer.parseInt("5")));
		assertFalse(Predicates.equal(5).eval(6));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#same(java.lang.Object)}
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
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#equalsIgnoreCase(java.lang.String)}
	 * .
	 */
	@Test
	public void testEqualsIgnoreCase() {
		assertTrue(Predicates.equalsIgnoreCase("Hello").eval("hello"));
		assertFalse(Predicates.equalsIgnoreCase("Hello").eval("world"));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.Predicates#matchesRegexp(java.lang.String)}
	 * .
	 */
	@Test
	public void testMatchesRegexp() {
		assertTrue(Predicates.matchesRegexp("[Hh]el+o").eval("hello"));
		assertFalse(Predicates.matchesRegexp("[Hh]el+o").eval("world"));
	}

	@Test
	public void testConstains() {
		assertTrue(Predicates.contains("foo").apply(
			"The word foo has no special meaning"));
		assertFalse(Predicates.contains("foo").apply(
			"The word bar has no special meaning, too"));
	}

	@Test
	public void testNot() throws Exception {
		assertFalse(Predicates.equal(6).not().eval(6));
		assertTrue(Predicates.false_().not().eval(5));
		assertFalse(Predicates.false_().not().not().eval(5));
	}

	@Test
	public void testGreatherThan() throws Exception {
		assertFalse(Predicates.greaterThan(5).eval(2));
		assertFalse(Predicates.greaterThan(5).eval(5));
		assertTrue(Predicates.greaterThan(5).eval(6));
	}

	@Test
	public void testLowerThan() throws Exception {
		assertTrue(Predicates.lowerThan(5).eval(2));
		assertFalse(Predicates.lowerThan(5).eval(5));
		assertFalse(Predicates.lowerThan(5).eval(6));
	}
}
