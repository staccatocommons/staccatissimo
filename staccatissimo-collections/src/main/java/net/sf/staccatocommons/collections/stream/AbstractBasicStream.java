/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.stream;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.AbstractProtoMonad;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.Equiv;
import net.sf.staccatocommons.lang.tuple.Tuples;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Very minimal stream partial implementation that defines final methods
 * that are, by spec, overloading or synonyms of the other stream methods
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public abstract class AbstractBasicStream<A> extends AbstractProtoMonad<Stream<A>, Stream, A> implements Stream<A> {

  /* Synonyms */

  @Override
  public final A any() {
    return first();
  }

  @Override
  public final A first() {
    return head();
  }

  @Override
  public final A second() {
    return get(1);
  }

  @Override
  public final A third() {
    return get(2);
  }

  /* Overloadings and very strict fixed equivalences */
  
  @Override
  public final <B> Stream<B> map(Applicable<? super A, ? extends B> function) {
    return map(Functions.from(function));
  }
  
  @Override
  public final boolean equiv(A... elements) {
    return equiv(Arrays.asList(elements));
  }
  
  @Override
  public final boolean equivBy(Evaluable2<? super A, ? super A> equalityTest, A... elements) {
    return equivBy(equalityTest, Arrays.asList(elements));
  }
  
  @Override
  public final <B> boolean equivOn(Applicable<? super A, ? extends B> function, A... elements) {
    return equivOn(function, Arrays.asList(elements));
  }
  
  public final boolean equiv(Iterator<? extends A> other) {
    return equiv(Streams.from(other));
  }

  public final boolean equivBy(Evaluable2<? super A, ? super A> equalityTest, Iterator<? extends A> other) {
    return equivBy(equalityTest, Streams.from(other));
  }

  public final <B> boolean equivOn(Applicable<? super A, ? extends B> function, Iterator<? extends A> other) {
    return equivOn(function, Streams.from(other));
  }
  
  
  public final <B> Stream<Tuple2<A, B>> zip(B... elements) {
    return zipWith(Tuples.<A, B> toTuple2(), elements);
  }

  public final <B> Stream<Tuple2<A, B>> zip(Iterator<B> other) {
    return zipWith(Tuples.<A, B> toTuple2(), other);
  }

  @Override
  public final <B> Stream<Tuple2<A, B>> zip(Iterable<B> iterable) {
    return zipWith(Tuples.<A, B> toTuple2(), iterable);
  }

  public final int findPosition(Evaluable<? super A> predicate) {
    int index = findIndex(predicate);
    if (index == -1)
      throw new NoSuchElementException();
    return index;
  }

  @Override
  public final int positionOf(A element) {
    int index = indexOf(element);
    if (index == -1)
      throw new NoSuchElementException(element.toString());
    return index;
  }
  
  @Override
  public final <B extends Comparable<B>> A maximumOn(Applicable<? super A, B> function) throws NoSuchElementException {
    return maximumBy(Compare.on(function));
  }

  @Override
  public final <B extends Comparable<B>> A minimumOn(Applicable<? super A, B> function) throws NoSuchElementException {
    return minimumBy(Compare.on(function));
  }
  
  @Override
  public final <B> boolean equivOn(Applicable<? super A, ? extends B> function, Iterable<? extends A> iterable) {
    return equivBy(Equiv.on(function), iterable);
  }
  
  public final <B extends Comparable<B>> Stream<A> sortOn(Applicable<? super A, B> function) {
    return sortBy(Compare.on(function));
  }
  
  @Override
  public final void println() {
    try {
      println(System.out);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  /* Deprecations */

  @Override
  public final Stream<A> memorize() {
    return memoize();
  }

  @Override
  public Stream<A> append(final Iterable<A> other) {
    return concat(other);
  }
  

  @Override
  public final Stream<A> append(Thunk<A> element) {
    return delayedAppend(element);
  }
  
  @Override
  public final Stream<A> prepend(Thunk<A> element) {
    return delayedPrepend(element);
  }

  public final <B, C> Stream<C> zip(@NonNull final Iterable<B> iterable,
    @NonNull final Function2<? super A, ? super B, C> function) {
    return zipWith(function, iterable);
  }

}