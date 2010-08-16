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

import static org.junit.Assert.*;

import net.sf.staccato.commons.lang.predicate.internal.ContainsSubstringPredicate;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ContainsSubstringPredicateUnitTest {

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.predicate.internal.ContainsSubstringPredicate#eval(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvalString() {
		assertTrue(new ContainsSubstringPredicate("foo")
			.apply("The word foo has no special meaning"));
		assertFalse(new ContainsSubstringPredicate("foo")
			.apply("The word bar has no special meaning, too"));
	}

}
