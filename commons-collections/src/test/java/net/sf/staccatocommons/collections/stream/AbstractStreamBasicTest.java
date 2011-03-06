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
import static net.sf.staccatocommons.lang.number.Numbers.*;
import static net.sf.staccatocommons.lang.sequence.StopConditions.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.impl.ListStream;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.iterators.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.predicate.Equiv;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.lang.tuple.Pair;

import org.junit.Assert;
import org.junit.BeforeClass;
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

	/**
	 * 
	 */
	@BeforeClass
	public static void before() {
		lambda($(Stream.class).toList());
	}

	/** Test for sum */
	@Test
	public void sum() throws Exception {
		assertEquals((Integer) 65, Cons.from(10, 20, 35).sum(integer()));
	}

	/** Test for product */
	@Test
	public void product() throws Exception {
		assertEquals(1 * 2 * 3, (int) Cons.from(0, 1, 2).map(add(1)).product(integer()));
	}

	/** Test for implicit sum */
	@Test
	public void sumImplicit() throws Exception {
		assertEquals((Integer) 70, //
			Iterate.from(10, add(3)) //
				.take(7)
				.filter(Compare.lessThan(25))
				.tail()
				.sum());
	}

	/** Test for {@link Stream#average()} **/
	@Test
	public void testAvg() throws Exception {
		assertEquals(9.6, Cons.from(10.0, 12.0, 15.0, 2.0, 9.0).average(double_()), 0.01);
		assertEquals(6, (int) Iterate.from(1, add(1), upTo(11)).average());
	}

	/** Test for {@link Stream#average()} **/
	@Test(expected = NoSuchElementException.class)
	public void testAvgEmptyStream() throws Exception {
		Cons.<Double> from().average(double_());
	}

	/**
	 * Test method for {@link AbstractStream#fold(java.lang.Object, Applicable2)}
	 */
	@Test
	public void fold() {
		Stream<Collection<String>> stream = //
		Cons.<Collection<String>> from(Arrays.asList("foo", "baz"), Collections.singleton("bar"));

		Collection<String> result = stream.fold(
			new ArrayList<String>(),
			new AbstractFunction2<Collection<String>, Collection<String>, Collection<String>>() {
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
		Stream<BigInteger> bigints = Cons.from(i(100), i(800), i(260));
		assertEquals(i(1160), bigints.reduce(bigInteger().add()));
	}

	/**
	 * Test for {@link Stream#flatMap(net.sf.staccatocommons.defs.Applicable)}
	 */
	@Test
	public void flatMap() throws Exception {
		assertTrue(Iterate//
			.from(4, add(1))
			.take(3)
			.flatMap(new AbstractFunction<Integer, Iterable<Integer>>() {
				public Iterable<Integer> apply(Integer arg) {
					return Sequence.fromTo(1, arg);
				}
			})
			.equivalent(1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6));
	}

	/** Test for {@link AbstractStream#append(Iterable)} ***/
	@Test
	public void concat() {
		assertEquals(
			Arrays.asList(10, 90, 60, 1, 2, 20),
			Cons.from(10, 90, 60).append(Arrays.asList(1, 2)).append(Cons.from(20)).toList());

		assertEquals(
			Arrays.asList("foo"),
			Cons.from("foo").append(Streams.from(new AbstractUnmodifiableIterator<String>() {
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
		assertEquals(3, Streams.from(Arrays.asList(12, 69, null, 1).iterator()).indexOf(1));
		assertEquals(0, Cons.from(10, 90).indexOf(10));
		assertEquals(-1, Cons.from(10, 90).indexOf(87));
	}

	/** Tets for positionOf */
	@Test
	public void testPositionOf() throws Exception {
		assertEquals(1, Cons.from(2, 10, 90).positionOf(10));
	}

	/** Tets for positionOf */
	@Test(expected = NoSuchElementException.class)
	public void testPositionOfBad() throws Exception {
		Cons.from(10, 90).positionOf(87);
	}

	/** Test for {@link Stream#intersperse(Object)} */
	@Test
	public void testIntersperse() throws Exception {
		System.out.println(Cons.from(4, 5).intersperse(10).joinStrings(""));
		assertTrue( //
		Cons //
			.from(4, 5, 6)
			.intersperse(1)
			.equivalent(4, 1, 5, 1, 6));

		assertTrue( //
		Streams
			.from(Arrays.asList(4, 5).iterator())
			.append(10)
			.intersperse(1)
			.take(4)
			.equivalent(4, 1, 5, 1));
	}

	/**
	 * Test for {@link Stream#intersperse(Object)}
	 */
	@Test
	public void testMemorize() throws Exception {
		Stream<Integer> interspersed = Cons.from(10, 20, 30).intersperse(1).memorize();
		assertEquals(10, (int) interspersed.first());
		assertEquals(10, (int) interspersed.first());
		assertEquals(1, (int) interspersed.second());
		assertEquals(20, (int) interspersed.third());
		assertEquals(30, (int) interspersed.get(4));
		assertEquals(1, (int) interspersed.get(3));
		assertEquals(1, (int) interspersed.get(3));
		assertEquals(30, (int) interspersed.get(4));

		Stream<Integer> mapped = Cons.from(10, 20, 30).map(add(1));
		assertEquals(21, (int) mapped.second());
		assertEquals(11, (int) mapped.first());
		assertEquals(31, (int) mapped.third());
		assertEquals(31, (int) mapped.third());
		assertEquals(11, (int) mapped.first());
		assertEquals(21, (int) mapped.second());

	}

	/**
	 * Test that memorized streams are equivalent to themselves
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMemorizeEquivSelf() throws Exception {
		Stream<Integer> stream = Streams.from(Arrays.asList(10, 20, 30).iterator());
		assertFalse(stream.equivalent(stream));

		Stream<Integer> memorized = Streams.from(Arrays.asList(10, 20, 30).iterator()).memorize();
		assertTrue(memorized.equivalent(memorized));
	}

	/**
	 * Tests that memorized streams are completely lazy in the particular case of
	 * a append stream
	 */
	@Test
	public void testMemorizeLazyOnAppend() throws Exception {
		Stream<Integer> stream = Cons.from(10, 98).append(65).appendUndefined().append(9).memorize();
		assertEquals(stream.last(), stream.last());
	}

	/**
	 * Tests that memorized streams are completely lazy, in the particular case of
	 * a map stream
	 */
	@Test
	public void testMemorizeLazyOnMap() throws Exception {
		Stream<Integer> stream = Cons.from(10, 0, 20).map(new AbstractFunction<Integer, Integer>() {
			public Integer apply(Integer arg) {
				return 1 / arg;
			}
		}).memorize();

		assertEquals(stream.third(), stream.third());
	}

	/** Test for {@link Stream#streamPartition(Evaluable)} */
	@Test
	public void testPartition() throws Exception {
		Pair<Stream<Integer>, Stream<Integer>> partition = Cons
			.from(50, 60, 1, 6, 9, 10, 100)
			.streamPartition(Compare.greaterThan(9));

		assertTrue(partition._1().equivalent(50, 60, 10, 100));
		assertTrue(partition._2().equivalent(1, 6, 9));

	}

	/** Test for {@link Stream#reverse()} */
	@Test
	public void testReverse() throws Exception {
		assertTrue(//
		Cons //
			.from(50, 30, 9, 12, 0, 3, -5, null)
			.reverse()
			.equivalent(null, -5, 3, 0, 12, 9, 30, 50));
	}

	/** Test for {@link Stream#maximum()} */
	@Test
	public void testMaximum() throws Exception {
		String max = Cons
			.from(
				_(new Object(), 10, "hello"),
				_(new Object(), 12, "foo"),
				_(new Object(), 9, "bye"),
				_(new Object(), 6, ""))
			.maximumOn(second(Integer.class))
			._3();
		assertEquals("foo", max);
		assertEquals(150, (int) Cons.from(90, 10, 30, 6, 150, 65).maximum());
	}

	/** Test for {@link Stream#minimum()} */
	@Test
	public void testMinimum() throws Exception {
		assertEquals(6, (int) Cons.from(90, 10, 30, 6, 150, 65).minimum());
	}

	/** Test for {@link AbstractStream#groupBy(Evaluable2)} */
	@Test
	public void foo() {
		Assert
			.assertEquals(
				Arrays.asList(
					Arrays.asList(1, 1),
					Arrays.asList(2),
					Arrays.asList(4, 4, 4),
					Arrays.asList(5)),
				new ListStream<Integer>(Arrays.asList(1, 1, 2, 4, 4, 4, 5))
					.groupBy(Equiv.<Integer> equalOrNull())
					.map(lambda($(Stream.class).toList()))
					.toList());
	}

	/**
	 * Test for {@link AbstractStream#sort()}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSort() throws Exception {
		Stream<Integer> sort = Cons.from(10, 20, 6, 9, 18, 6, 26, 32).sort();
		assertEquals(6, (int) sort.first());
		assertEquals(32, (int) sort.last());
		assertTrue(sort.equivalent(6, 6, 9, 10, 18, 20, 26, 32));
	}

	@Test
	public void testRep() throws Exception {
		Stream<Integer> s = Cons.from(1, 3).intersperse(0);
		assertFalse(s.isEmpty());
		assertFalse(s.isEmpty());
		assertFalse(s.isEmpty());
		assertFalse(s.isEmpty());

		s = s.tail();
		assertFalse(s.isEmpty());
		assertFalse(s.isEmpty());

		s = s.tail();
		assertFalse(s.isEmpty());

		s = s.tail();
		assertTrue(s.isEmpty());
	}

	// TODO cycle
	// TODO replicate

	/** Test that no StackOverflow is raised on large, recursive streams */
	@Test
	public void testRecursiveLongStream() throws Exception {
		assertEquals(90000, Iterate.from(1, add(1)).intersperse(0).take(90000).size());
	}

	/** Test that no StackOverflow is raised on large, flat mapped streams */
	@Test
	public void testLongFlatMapStream() throws Exception {
		Iterate.fromTo(1, 4000).cross(Iterate.fromTo(1, 4000)).size();
	}

	/** Tests simple crossing */
	@Test
	public void testCross() throws Exception {
		assertTrue(Iterate
			.fromTo(1, 20)
			.cross(Iterate.fromTo(20, 40))
			.equivalent(Iterables.cross(Sequence.fromTo(1, 20), Sequence.fromTo(20, 40))));
	}

	/**
	 * Tests full-crossing
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFullCross() throws Exception {
		assertEquals(
			"[[1,3,5],[1,3,6],[1,4,5],[1,4,6],[2,3,5],[2,3,6],[2,4,5],[2,4,6]]",
			Cons
				.from(1, 2)
				.fullCross(Cons.<Stream<Integer>> from(Cons.from(3, 4), Cons.from(5, 6)))
				.toString());
	}
}
