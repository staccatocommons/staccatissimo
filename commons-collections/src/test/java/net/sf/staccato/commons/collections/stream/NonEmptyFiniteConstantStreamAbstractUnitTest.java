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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import net.sf.staccato.commons.collections.iterable.Iterables;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.function.Function;
import net.sf.staccato.commons.lang.predicate.Predicate;
import net.sf.staccato.commons.lang.predicate.Predicates;
import net.sf.staccato.commons.lang.provider.NullProvider;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public abstract class NonEmptyFiniteConstantStreamAbstractUnitTest<T> {
	// TODO PARAMETERIZE functions and predicates
	private Stream<T> stream;
	private Evaluable<? super T> predicate;
	private Applicable<? super T, Integer> function;
	private Provider<T> provider;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stream = createStream();
		predicate = createPredicateMock();
		function = createFunctionMock();
		provider = createProviderMock();
	}

	/**
	 * @return
	 */
	protected Provider<T> createProviderMock() {
		return NullProvider.getInstance();
	}

	protected Applicable<? super T, Integer> createFunctionMock() {
		return new Function<T, Integer>() {
			public Integer apply(T arg) {
				return System.identityHashCode(arg);
			}
		};
	}

	protected Evaluable<? super T> createPredicateMock() {
		return new Predicate<T>() {
			int i = 0;

			public boolean eval(T argument) {
				return i % 2 == 0;
			}
		};
	}

	protected abstract Stream<T> createStream();

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#value()}.
	 */
	@Test
	public void testValue() {
		assertEquals(stream.any(), stream.value());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#filter(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Test
	public void testFilter() {
		assertEquals(Iterables.filter(stream, predicate), stream
			.filter(predicate)
			.toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#takeWhile(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Ignore
	@Test
	public void testTakeWhile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#take(int)}
	 * .
	 */
	@Test
	public void testTake() {
		assertEquals(2, stream.take(2).size());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#reduce(net.sf.staccato.commons.lang.Applicable2)}
	 * .
	 */
	@Ignore
	@Test
	public void testReduce() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#fold(java.lang.Object, net.sf.staccato.commons.lang.Applicable2)}
	 * .
	 */
	@Ignore
	@Test
	public void testFold() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#apply(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testApply() {
		assertEquals(stream.get(2), stream.apply(2));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#any()}.
	 */
	@Test
	public void testAny() {
		assertTrue(stream.toList().contains(stream.any()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrNone()}
	 * .
	 */
	@Test
	public void testAnyOrNone() {
		assertTrue(stream.anyOrNone().isDefined());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrNull()}
	 * .
	 */
	@Test
	public void testAnyOrNull() {
		assertEquals(stream.any(), stream.anyOrNull());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrElse(net.sf.staccato.commons.lang.Provider)}
	 * .
	 */
	@Ignore
	@Test
	public void testAnyOrElseProviderOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#anyOrElse(java.lang.Object)}
	 * .
	 */
	@Test
	public void testAnyOrElseT() {
		assertEquals(stream.any(), stream.anyOrElse((T) null));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#find(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Test(expected = NoSuchElementException.class)
	public void testFind_NoSuchElement() {
		stream.find(Predicates.false_());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#findOrNone(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Test
	public void testFindOrNone_NoSuchElement() {
		assertTrue(stream.findOrNone(Predicates.false_()).isUndefined());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#findOrNull(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Test
	public void testFindOrNull() {
		assertEquals(
			stream.findOrNone(predicate).valueOrNull(),
			stream.findOrNull(predicate));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#findOrElse(net.sf.staccato.commons.lang.Evaluable, net.sf.staccato.commons.lang.Provider)}
	 * .
	 */
	@Test
	public void testFindOrElse() {
		assertEquals(
			stream.findOrNone(predicate).valueOrElse(provider),
			stream.findOrElse(predicate, provider));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#all(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Test
	public void testAll() {
		assertTrue(stream.all(Predicates.true_()));
		assertFalse(stream.all(Predicates.false_()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#any(net.sf.staccato.commons.lang.Evaluable)}
	 * .
	 */
	@Test
	public void testAnyEvaluable() {
		assertTrue(stream.any(Predicates.true_()));
		assertFalse(stream.any(Predicates.false_()));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#map(net.sf.staccato.commons.lang.Applicable)}
	 * .
	 */
	@Test
	public void testMap() {
		assertEquals(Iterables.map(stream, function), stream.map(function).toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#first()}.
	 */
	@Test
	public void testFirst() {
		assertEquals(stream.get(0), stream.first());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#second()}.
	 */
	@Test
	public void testSecond() {
		assertEquals(stream.get(1), stream.second());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#third()}.
	 */
	@Test
	public void testThird() {
		assertEquals(stream.get(2), stream.third());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#last()}.
	 */
	@Test
	public void testLast() {
		assertEquals(stream.get(stream.size() - 1), stream.last());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#get(int)}.
	 */
	@Test
	public void testGet() {
		assertEquals(Iterables.get(stream, 0), stream.get(0));
		assertEquals(Iterables.get(stream, 1), stream.get(1));
		assertEquals(Iterables.get(stream, 2), stream.get(2));
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#get(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testGet_NoSuchElement() {
		stream.get(stream.size());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toCollection()}
	 * .
	 */
	@Test
	public void testToCollection() {
		assertEquals(Iterables.asList(stream), stream.toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toSet()}.
	 */
	@Test
	public void testToSet() {
		assertEquals(Iterables.asSet(stream), stream.toSet());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toList()}.
	 */
	@Test
	public void testToList() {
		assertEquals(Iterables.asList(stream), stream.toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.collections.stream.AbstractStream#toList()}.
	 */
	@Test
	public void testContains() {
		// Invariant: the stream contains all element in it
		assertTrue(stream.all(new Predicate<T>() {
			@Override
			public boolean eval(T argument) {
				return stream.contains(argument);
			}
		}));
	}
}
