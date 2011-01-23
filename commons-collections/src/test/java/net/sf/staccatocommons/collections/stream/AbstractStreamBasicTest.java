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

import static net.sf.staccatocommons.lang.number.NumberTypes.*;
import static net.sf.staccatocommons.lang.number.Numbers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import net.sf.staccatocommons.collections.internal.iterator.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.function.Function2;
import net.sf.staccatocommons.lang.sequence.Sequence;

import org.junit.Test;

/**
 * Classic tests for methods of {@link AbstractStream} that are not covered by
 * {@link StreamTheories}, because it is costly to create and maintain theories
 * about them.
 * 
 * @author flbulgarelli
 * 
 */
public class AbstractStreamBasicTest {

	/** Test for sum */
	@Test
	public void sum() throws Exception {
		assertEquals((Integer) 65, Streams.from(10, 20, 35).sum(integer()));
	}

	/**
	 * Test method for {@link AbstractStream#fold(java.lang.Object, Applicable2)}
	 */
	@Test
	public void fold() {
		Stream<Collection<String>> stream = //
		Streams.<Collection<String>> from(Arrays.asList("foo", "baz"), Collections.singleton("bar"));

		Collection<String> result = stream.fold(
			new ArrayList<String>(),
			new Function2<Collection<String>, Collection<String>, Collection<String>>() {
				public Collection<String> apply(Collection<String> arg1, Collection<String> arg2) {
					arg1.addAll(arg2);
					return arg1;
				}
			});
		assertEquals(Arrays.asList("foo", "baz", "bar"), result);
	}

	/**
	 * Test method for {@link AbstractStream#reduce(Applicable2)}
	 */
	@Test
	public void testReduce() {
		Stream<BigInteger> bigints = Streams.from(i(100), i(800), i(260));
		assertEquals(i(1160), bigints.reduce(bigInteger().add()));
	}

	/**
	 * Test for {@link Stream#flatMap(net.sf.staccatocommons.defs.Applicable)}
	 */
	@Test
	public void flatMap() throws Exception {
		assertTrue(Streams//
			.from(4, add(1))
			.take(3)
			.flatMap(new Function<Integer, Iterable<Integer>>() {
				public Iterable<Integer> apply(Integer arg) {
					return Sequence.fromTo(1, arg);
				}
			})
			.elementsEquals(1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6));
	}

	/** Test for {@link AbstractStream#concat(Iterable)} ***/
	@Test
	public void concat() {
		assertEquals(
			Arrays.asList(10, 90, 60, 1, 2, 20),
			Streams.from(10, 90, 60).concat(Arrays.asList(1, 2)).concat(Streams.from(20)).toList());

		assertEquals(
			Arrays.asList("foo"),
			Streams.from("foo").concat(Streams.from(new AbstractUnmodifiableIterator<String>() {
				public boolean hasNext() {
					return true;
				}

				public String next() {
					throw new RuntimeException("baz");
				}
			})).take(1).toList());
	}

	/** Tets for indexof */
	@Test
	public void testIndexof() throws Exception {
		assertEquals(0, Streams.from(10, 90).indexOf(10));
		assertEquals(-1, Streams.from(10, 90).indexOf(87));
	}
}
