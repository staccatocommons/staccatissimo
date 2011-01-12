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
package net.sf.staccatocommons.collections.stream;

import static net.sf.staccatocommons.lang.number.NumberTypes.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.iterable.ModifiableIterables;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.lang.cell.Cells;
import net.sf.staccatocommons.lang.cell.internal.NullCell;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.function.Function2;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.lang.sequence.Sequence;

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
	public static Predicate[] predicates = new Predicate[] { Predicates.equal(5),
			Predicates.greaterThan(90), //
			Predicates.false_(), Predicates.true_(), Predicates.notNull(), Predicates.equal(26), //
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
	public static Provider<Integer>[] providers = new Provider[] { NullCell.getInstance(), //
			Cells.constant(90) //
	};
	@DataPoints
	public static int[] numbers = new int[] { 0, 1, 2, 4, 90 };

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#filter(net.sf.staccatocommons.defs.Evaluable)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 */
	@Theory
	public void filteredSizeLowerOrEqualToOriginal(Stream stream, Evaluable predicate) {
		assertThat(stream.filter(predicate).size(), lessThanOrEqualTo(stream.size()));
	}

	@Theory
	public void allFilteredElementsEvaluate(Stream stream, Evaluable predicate) {
		assertTrue(stream.filter(predicate).all(predicate));
	}

	@Theory
	public void allFilteredElementsEvaluate(Stream stream, Predicate predicate) {
		assertEquals(stream.size(),//
			stream.filter(predicate).concat(stream.filter(predicate.not())).size());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#takeWhile(net.sf.staccatocommons.defs.Evaluable)}
	 * .
	 */
	@Ignore
	@Theory
	public void testTakeWhile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#take(int)}
	 * .
	 */
	@Theory
	public void testTake(int size, Stream<?> stream) {
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
		assertEquals(0, (int) stream.flatMap(function2).fold(0, integer().add()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#reduce(net.sf.staccatocommons.lang.Applicable2)}
	 * .
	 */
	@Theory
	public void testFold(Stream<Integer> stream, final Function<Integer, Integer> function) {
		assumeTrue(function != functions[3]);
		assertEquals(
			stream.map(function).toList(),
			stream.fold(new ArrayList<Integer>(), new Function2<List<Integer>, Integer, List<Integer>>() {
				public List<Integer> apply(List<Integer> arg1, Integer arg2) {
					arg1.add(function.apply(arg2));
					return arg1;
				}
			}));

	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#fold(java.lang.Object, net.sf.staccatocommons.lang.Applicable2)}
	 * .
	 */
	@Ignore
	@Test(expected = IllegalStateException.class)
	@Theory
	public void testReduce_Fail(Stream<Integer> stream) {
		assumeTrue(stream.isEmpty());
		stream.reduce(integer().add());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#fold(java.lang.Object, net.sf.staccatocommons.lang.Applicable2)}
	 */
	@Test
	@Theory
	public void testReduce(Stream<Integer> stream) {
		assumeTrue(!stream.isEmpty());
		assertEquals(stream.reduce(integer().add()), stream.fold(0, integer().add()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#any()}.
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#anyOrNone()}
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#anyOrNull()}
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#anyOrElse(net.sf.staccatocommons.defs.Provider)}
	 * .
	 */
	@Ignore
	@Theory
	public void testAnyOrElseProviderOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#find(net.sf.staccatocommons.defs.Evaluable)}
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#findOrNull(net.sf.staccatocommons.defs.Evaluable)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 */
	@Theory
	public void testFindOrNull(Stream stream, Evaluable predicate) {
		assertEquals(stream.findOrNone(predicate).valueOrNull(), stream.findOrNull(predicate));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#findOrElse(net.sf.staccatocommons.defs.Evaluable, net.sf.staccatocommons.defs.Provider)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 * @param provider
	 */
	@Theory
	public void testFindOrElse(Stream stream, Evaluable predicate, Provider provider) {
		assertEquals(
			stream.findOrNone(predicate).valueOrElse(provider),
			stream.findOrElse(predicate, provider));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#all(net.sf.staccatocommons.defs.Evaluable)}
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#any(net.sf.staccatocommons.defs.Evaluable)}
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#map(net.sf.staccatocommons.defs.Applicable)}
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#first()}.
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#second()}.
	 */
	@Theory
	public void testSecond(Stream stream) {
		assumeThat(stream.size(), greaterThan(1));
		assumeThat(stream.second(), is(stream.second()));
		assertEquals(stream.get(1), stream.second());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#third()}.
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#last()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testLast(Stream stream) {
		assumeTrue(!stream.isEmpty());//
		assumeThat(stream.last(), is(stream.last()));
		assertEquals(stream.get(stream.size() - 1), stream.last());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#get(int)}.
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toSet()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testToSet(Stream stream) {
		assertEquals(Iterables.toSet(stream), stream.toSet());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toList()}.
	 * 
	 * @param stream
	 */
	@Theory
	public void testToList(Stream stream) {
		assertEquals(Iterables.toList(stream), stream.toList());
	}

	@Theory
	public void testToStream(Stream stream) {
		Stream stream2 = stream.toOrderedStream();
		Stream stream3 = stream2.toOrderedStream();
		assertEquals(
			ModifiableIterables.addAll(new HashSet(), stream2),
			ModifiableIterables.addAll(new HashSet(), stream3));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toList()}.
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

	/** Test method for {@link AbstractStream#zip(Iterable)} */
	@Theory
	public void testZip(Stream stream, Iterable iterable) throws Exception {
		assertEquals(Iterables.zip(stream, iterable), stream.zip(iterable).toList());
	}
}
