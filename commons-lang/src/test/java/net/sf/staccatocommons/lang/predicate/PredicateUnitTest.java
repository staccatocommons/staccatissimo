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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.predicate.Predicate;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class PredicateUnitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/** Test for {@link Predicate#apply(Object)} */
	@Test
	public void testApply() throws Exception {
		Predicate<Object> predicate = new Predicate<Object>() {
			public boolean eval(Object argument) {
				return argument == null;
			}
		};
		assertTrue(predicate.apply(null));
		assertFalse(predicate.apply(new Object()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicate#not()}.
	 */
	@Test
	public void testNot() {
		assertTrue(new Predicate<Object>() {
			public boolean eval(Object argument) {
				return false;
			}
		}.not().eval(new Object()));
		assertFalse(new Predicate<Object>() {
			public boolean eval(Object argument) {
				return true;
			}
		}.not().eval(new Object()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicate#or(net.sf.staccatocommons.defs.Evaluable)}
	 * .
	 */
	@Test
	public void testOr() {
		assertTrue(new Predicate<Integer>() {
			public boolean eval(Integer argument) {
				return argument < 15;
			}
		}.or(new Evaluable<Integer>() {
			public boolean eval(Integer argument) {
				return argument > 20;
			}
		}).eval(10));

		assertFalse(new Predicate<Integer>() {
			public boolean eval(Integer argument) {
				return argument > 15;
			}
		}.or(new Evaluable<Integer>() {
			public boolean eval(Integer argument) {
				return argument < 8;
			}
		}).eval(10));

	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.predicate.Predicate#and(net.sf.staccatocommons.defs.Evaluable)}
	 * .
	 */
	@Test
	public void testAnd() {
		assertTrue(new Predicate<Integer>() {
			public boolean eval(Integer argument) {
				return argument < 15;
			}
		}.and(new Evaluable<Integer>() {
			public boolean eval(Integer argument) {
				return argument > 2;
			}
		}).eval(10));

		assertFalse(new Predicate<Integer>() {
			public boolean eval(Integer argument) {
				return argument < 15;
			}
		}.and(new Evaluable<Integer>() {
			public boolean eval(Integer argument) {
				return argument > 12;
			}
		}).eval(10));
	}

}
