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
package net.sf.staccatocommons.lambda;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;

import java.awt.List;
import java.util.Iterator;
import java.util.Queue;

import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.lang.tuple.Tuple;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class LambdaFactoryUnitTest {

	/**
	 * Tests that {@link LambdaFactory} are reusable
	 */
	@Test
	public void testFactoryReusable() throws Exception {
		LambdaFactory l = Lambda.factory();

		final Iterator<?> iter = Thriterators.empty();
		assertSame(iter, l.lambda(l.$(Iterable.class).iterator()).apply(new Iterable() {
			public Iterator iterator() {
				return iter;
			}
		}));
		assertEquals(10, l.lambda(l.$(Tuple.FirstAware.class).first()).apply(_(10, 20)));
	}

	/** Test that fails if invoked in the wrong order */
	@Test(expected = IllegalStateException.class)
	public void testWronInvocationOrder1() throws Exception {
		LambdaFactory l = Lambda.factory();
		l.lambda(10);
	}

	/** Test that fails if invoked in the wrong order */
	@Test(expected = IllegalStateException.class)
	public void testWrongOrder2() throws Exception {
		LambdaFactory l = Lambda.factory();
		l.$(List.class);
		l.$(Queue.class);
	}

	/** Tests that lambda factory throws an exception it does not became unsuable */
	@Test
	public void testThrowsException() throws Exception {
		LambdaFactory l = Lambda.factory();
		try {
			l.lambda(l.$(String.class).isEmpty());
		} catch (Exception e) { /* ignore */}
		assertNotNull(l.lambda(l.$(Tuple.FirstAware.class).first()));
	}

}
