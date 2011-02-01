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

import static org.junit.Assert.*;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.provider.Provider;
import net.sf.staccatocommons.lang.provider.Providers;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class LazinessIntegrationTest {

	/***/
	@Test
	public void testMapAndGet() throws Exception {
		Stream<Integer> stream = integersStream();
		assertEquals(10, (int) stream.get(0));
		assertEquals(20, (int) stream.get(1));
		assertEquals(30, (int) stream.get(2));
		assertEquals(50, (int) stream.get(4));
	}

	/***/
	@Test(expected = NullPointerException.class)
	public void testMapAndGet2() throws Exception {
		Stream<Integer> stream = integersStream();
		stream.get(3);
	}

	/***/
	@Test
	public void testMapAndDrop() throws Exception {
		assertTrue(integersStream().drop(4).equivalent(50));
	}

	/***/
	@Test
	public void testMapAndTake() throws Exception {
		assertTrue(integersStream().take(3).equivalent(10, 20, 30));
	}

	/***/
	@Test
	public void testIntersparseAndSize() throws Exception {
		Cons //
			.from(10, 20)
			.concatUndefined()
			.intersperse(1)
			.size();
	}

	/***/
	@Test
	public void testIntersparseAndTake() throws Exception {
		assertTrue(Cons //
			.from(10, 20)
			.concatUndefined()
			.intersperse(1)
			.take(4)
			.equivalent(10, 1, 20, 1));
	}

	/***/
	@Test
	public void testIntersparseAndAppend() throws Exception {
		assertEquals(25, (int) Cons //
			.from(10, 20)
			.append(21)
			.append(Providers.constant(22))
			.append(new Provider<Integer>() {
				public Integer call() throws Exception {
					throw new Exception();
				}
			})
			.append(Providers.constant(25))
			.intersperse(1)
			.last());
	}

	/***/
	@Test
	public void testConcatAndSize() throws Exception {
		int size = Cons //
			.from(10)
			.concatUndefined()
			.size();
		assertEquals(2, size);
	}

	/***/
	@Test
	public void testAppendAndSize() throws Exception {
		int size = //
		Cons //
			.from(10)
			.concatUndefined()
			.append(20)
			.size();
		assertEquals(3, size);
	}

	/***/
	@Test
	public void testConsAndSize() throws Exception {
		assertEquals(6,//
			Cons //
				.from(10, 20, 30)
				.concatUndefined()
				.prepend(20)
				.prepend(30)
				.size());
	}

	protected Stream<Integer> integersStream() {
		return Cons.from(10, 20, 30, null, 50).map(new Function<Integer, Integer>() {
			public Integer apply(Integer arg) {
				return arg - 0;
			}
		});
	}

}