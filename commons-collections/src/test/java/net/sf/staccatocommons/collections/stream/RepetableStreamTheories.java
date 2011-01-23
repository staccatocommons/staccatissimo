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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.lang.provider.Providers;
import net.sf.staccatocommons.lang.provider.internal.NullProvider;

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
public abstract class RepetableStreamTheories extends StreamTheories {

	/** Functions data points */
	@DataPoints
	public static Function[] functions = new Function[] { Functions.constant(59),
			Functions.identity(), Functions.toString_() };

	/** providers data points */
	@DataPoints
	public static Thunk<Integer>[] providers = new Thunk[] { NullProvider.getInstance(), //
			Providers.constant(90) //
	};

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

	/**
	 * Test that all elements from a filter stream stasify the filtering condition
	 */
	@Theory
	public void allFilteredElementsEvaluate(Stream stream, Evaluable predicate) {
		assertTrue(stream.filter(predicate).all(predicate));
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
	 * Test method for {@link AbstractStream#anyOrNull()} .
	 * 
	 * @param stream
	 */
	@Theory
	public final void anyEqualsAnyOrNullInNonEmpty(Stream stream) {
		assumeTrue(!stream.isEmpty());
		assertEquals(stream.any(), stream.anyOrNull());
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
	public void findFailsForFalsePredicate(Searchable stream) {
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
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#findOrElse(net.sf.staccatocommons.defs.Evaluable, net.sf.staccatocommons.defs.Thunk)}
	 * .
	 * 
	 * @param stream
	 * @param predicate
	 * @param provider
	 */
	@Theory
	public void testFindOrElse(Stream stream, Evaluable predicate, Thunk provider) {
		assertEquals(
			stream.findOrNone(predicate).valueOrElse(provider),
			stream.findOrElse(predicate, provider));
	}

	/**
	 * Test method for
	 * {@link AbstractStream#map(net.sf.staccatocommons.defs.Applicable)}
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
		assertEquals(stream.first(), stream.head());
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
		assumeTrue(!stream.isEmpty());
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
	public <A> void testToList(Stream<A> stream) {
		assertEquals(Iterables.toList(stream), stream.toList());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.collections.stream.AbstractStream#toList()}.
	 * 
	 * @param stream
	 */
	@Theory
	public <A> void testContains(final Stream<A> stream) {
		assertTrue(stream.all(new Predicate<A>() {
			public boolean eval(A argument) {
				return stream.contains(argument);
			}
		}));
	}

	/** Test for {@link Stream#elementsEquals(Iterable)} */
	@Theory
	public <A> void testElementEqual(Stream<A> stream) throws Exception {
		assertTrue(stream.elementsEquals(stream));
		assertTrue(stream.elementsEquals(stream.toRepetableStream()));
		assertTrue(stream.elementsEquals(stream.toList()));
		assertTrue(stream.elementsEquals(stream.toArray((Class<A>) Object.class)));
	}

	/** Test method for {@link AbstractStream#zip(Iterable)} */
	@Theory
	public <A, B> void testZip(Stream<A> stream, Iterable<B> iterable) throws Exception {
		assertEquals(Iterables.zip(stream, iterable), stream.zip(iterable).toList());
	}
}
