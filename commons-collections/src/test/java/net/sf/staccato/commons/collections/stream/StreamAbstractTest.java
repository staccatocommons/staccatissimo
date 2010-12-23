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
package net.sf.staccato.commons.collections.stream;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import net.sf.staccato.commons.collections.iterable.Iterables;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.function.Function;
import net.sf.staccato.commons.lang.function.Functions;
import net.sf.staccato.commons.lang.predicate.Predicate;
import net.sf.staccato.commons.lang.predicate.Predicates;
import net.sf.staccato.commons.lang.provider.Providers;
import net.sf.staccato.commons.lang.provider.internal.NullProvider;
import net.sf.staccato.commons.lang.sequence.Sequence;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author flbulgarelli
 * 
 */
@RunWith(Theories.class)
public abstract class StreamAbstractTest {

	@DataPoints
	public static Predicate[] predicates = new Predicate[] {
			Predicates.equal(5),
			Predicates.greaterThan(90), //
			Predicates.false_(), Predicates.true_(), Predicates.notNull(),
			Predicates.equal(26), //
			new Predicate<Integer>() {
				public boolean eval(Integer argument) {
					return argument % 2 == 0;
				}
			}

	};
	@DataPoints
	public static Function[] functions = new Function[] { Functions.constant(59), //
			Functions.identity(), //
			new Function<Integer, Integer>() {
				public Integer apply(Integer arg) {
					return arg + 5;
				}
			}, //
			new Function<Integer, Iterable<Integer>>() {
				public Iterable<Integer> apply(Integer arg) {
					return Sequence.fromToBy(arg, arg + 100, 10);
				}
			} };
	@DataPoints
	public static Provider<Integer>[] providers = new Provider[] {
			NullProvider.getInstance(), //
			Providers.constant(90) //
	};
	@DataPoints
	public static int[] numbers = new int[] { 0, 1, 2, 4, 90 };

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#filter(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 */
	@Theory
	public void filteredSizeLowerOrEqualToOriginal(Stream stream,
		Evaluable predicate) {
		assertThat(
			stream.filter(predicate).size(),
			lessThanOrEqualTo(stream.size()));
	}

	@Theory
	public void allFilteredElementsEvaluate(Stream stream, Evaluable predicate) {
		assertTrue(stream.filter(predicate).all(predicate));
	}

	@Theory
	public void allFilteredElementsEvaluate(Stream stream, Predicate predicate) {
		assertEquals(
			stream.size(),
			stream.filter(predicate).concat(stream.filter(predicate.not())).size());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#takeWhile(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Ignore
	@Theory
	public void testTakeWhile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#take(int)}
	 * .
	 */
	@Theory
	public void testTake(int size, Stream<Integer> stream) {
		assumeThat(size, lessThanOrEqualTo(stream.size()));
		assertEquals(size, stream.take(size).size());
	}

	@Theory
	public void flatMapSize(Stream<Integer> stream) throws Exception {
		Function<Integer, Iterable<Integer>> function2 = new Function<Integer, Iterable<Integer>>() {
			@Override
			public Iterable<Integer> apply(Integer arg) {
				return Arrays.asList(arg, -arg);
			}
		};
		assertEquals(2 * stream.size(), stream.flatMap(function2).size());
		assertEquals(
			0,
			(int) stream.flatMap(function2).fold(0, Functions.integerSum()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#reduce(net.sf.staccato.commons.lang.Applicable2)}
	 * .
	 */
	@Ignore
	@Theory
	public void testReduce() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#fold(java.lang.Object, net.sf.staccato.commons.lang.Applicable2)}
	 * .
	 */
	@Ignore
	@Theory
	public void testFold() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#any()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testAny(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assertTrue(stream.toList().contains(stream.any()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrNone()}
	 * .
	 * 
	 * @param stream
	 */
	@Theory
	public void testAnyOrNone(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assertTrue(stream.anyOrNone().isDefined());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrNull()}
	 * .
	 * 
	 * @param stream
	 */
	@Theory
	public void anyEqualsAnyOrNullOnNonEmpty(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assertEquals(stream.any(), stream.anyOrNull());
	}

	@Theory
	public void anyOrNullEqualsNullOnEmpty(Stream stream) {
		assumeTrue(stream.isEmpty());
		assertNull(stream.anyOrNull());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrElse(net.sf.staccato.commons.lang.Provider)}
	 * .
	 */
	@Ignore
	@Theory
	public void testAnyOrElseProviderOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#find(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 * 
	 * @param stream
	 */
	@Theory
	@Test(expected = NoSuchElementException.class)
	public void testFind_NoSuchElement(Searchable stream) {
		stream.find(Predicates.false_());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#findOrNull(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 */
	@Theory
	public void testFindOrNull(Stream stream, Evaluable predicate) {
		assertEquals(
			stream.findOrNone(predicate).valueOrNull(),
			stream.findOrNull(predicate));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#findOrElse(net.sf.staccato.commons.lang.Evaluable, net.sf.staccato.commons.lang.Provider)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 * @param provider
	 */
	@Theory
	public void testFindOrElse(Stream stream, Evaluable predicate,
		Provider provider) {
		assertEquals(
			stream.findOrNone(predicate).valueOrElse(provider),
			stream.findOrElse(predicate, provider));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#all(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 * 
	 * @param stream
	 */
	@Theory
	public void testAll(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assertTrue(stream.all(Predicates.true_()));
		assertFalse(stream.all(Predicates.false_()));
	}

	@Theory
	public void emptyStreamSatisfyAnyPredicate(Stream stream, Evaluable predicate) {
		assumeTrue(stream.isEmpty());
		assertTrue(stream.all(predicate));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#any(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 * 
	 * @param stream
	 */
	@Theory
	public void testAnyEvaluable(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assertTrue(stream.any(Predicates.true_()));
		assertFalse(stream.any(Predicates.false_()));
	}

	@Theory
	public void emptyStreamsSatisfyNoPredicate(Stream stream, Evaluable predicate) {
		assumeTrue(stream.isEmpty());
		assertFalse(stream.any(predicate));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#map(net.sf.staccato.commons.lang.Applicable)}
	 * .
	 * 
	 * @param stream
	 * @param function
	 */
	@Theory
	public void testMap(Stream stream, Applicable function) {
		assertEquals(stream.size(), stream.map(function).size());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#first()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testFirst(Stream stream) {
		assumeThat(stream.size(), greaterThan(0));
		assumeThat(stream.first(), is(stream.first()));
		assertEquals(stream.get(0), stream.first());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#second()}.
	 */
	@Theory
	public void testSecond(Stream stream) {
		assumeThat(stream.size(), greaterThan(1));
		assumeThat(stream.second(), is(stream.second()));
		assertEquals(stream.get(1), stream.second());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#third()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testThird(Stream stream) {
		assumeThat(stream.size(), greaterThan(2));
		assumeThat(stream.third(), is(stream.third()));
		assertEquals(stream.get(2), stream.third());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#last()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testLast(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assumeThat(stream.last(), is(stream.last()));
		assertEquals(stream.get(stream.size() - 1), stream.last());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#get(int)}.
	 * 
	 * @param stream
	 */
	@Theory
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet_NoSuchElement(Stream stream) {
		stream.get(stream.size());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toCollection()}
	 * .
	 * 
	 * @param stream
	 */
	@Theory
	public void testToCollection(Stream stream) {
		assertEquals(Iterables.toList(stream), stream.toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toSet()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testToSet(Stream stream) {
		assertEquals(Iterables.toSet(stream), stream.toSet());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toList()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testToList(Stream stream) {
		assertEquals(Iterables.toList(stream), stream.toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toList()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testContains(final Stream<Integer> stream) {
		assertTrue(stream.all(new Predicate<Integer>() {
			@Override
			public boolean eval(Integer argument) {
				return stream.contains(argument);
			}
		}));
	}
}
