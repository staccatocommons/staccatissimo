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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.staccato.commons.collections.iterable.Iterables;
import net.sf.staccato.commons.collections.iterable.internal.IterablesInternal;
import net.sf.staccato.commons.collections.stream.internal.ConcatStream;
import net.sf.staccato.commons.collections.stream.internal.FilterStream;
import net.sf.staccato.commons.collections.stream.internal.FlatMapStream;
import net.sf.staccato.commons.collections.stream.internal.MapStream;
import net.sf.staccato.commons.collections.stream.internal.TakeStream;
import net.sf.staccato.commons.collections.stream.internal.TakeWhileStream;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.value.NamedTupleToStringStyle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An abstract implementation of a {@link Stream}. Only it {@link Iterator}
 * method is abstract
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public abstract class AbstractStream<A> implements Stream<A> {

	@Override
	public int size() {
		int size = 0;
		for (@SuppressWarnings("unused")
		A element : this)
			size++;
		return size;
	}

	@Override
	public boolean isEmpty() {
		return IterablesInternal.isEmptyInternal(this);
	}

	@Override
	public boolean contains(A element) {
		return IterablesInternal.containsInternal(this, element);
	}

	@Override
	public Stream<A> filter(final Evaluable<? super A> predicate) {
		return new FilterStream<A>(this, predicate);
	}

	@Override
	public Stream<A> takeWhile(final Evaluable<? super A> predicate) {
		return new TakeWhileStream<A>(this, predicate);
	}

	@Override
	public Stream<A> take(final int amountOfElements) {
		return new TakeStream<A>(this, amountOfElements);
	}

	@Override
	public A reduce(Applicable2<? super A, ? super A, ? extends A> function) {
		return IterablesInternal.reduceInternal(this, function);
	}

	@Override
	public <O> O fold(O initial,
		Applicable2<? super O, ? super A, ? extends O> function) {
		return IterablesInternal.foldInternal(this, initial, function);
	}


	@Override
	public A any() {
		return IterablesInternal.anyInternal(this);
	}

	@Override
	public Option<A> anyOrNone() {
		return IterablesInternal.anyOrNoneInternal(this);
	}

	@Override
	public A anyOrNull() {
		return anyOrNone().valueOrNull();
	}

	@Override
	public A anyOrElse(Provider<A> provider) {
		return anyOrNone().valueOrElse(provider);
	}

	@Override
	public A anyOrElse(A value) {
		return anyOrNone().valueOrElse(value);
	}

	@Override
	public A find(Evaluable<? super A> predicate) {
		return Iterables.find(this, predicate);
	}

	@Override
	public Option<A> findOrNone(Evaluable<? super A> predicate) {
		return Iterables.findOrNone(this, predicate);
	}

	@Override
	public A findOrNull(Evaluable<? super A> predicate) {
		return findOrNone(predicate).valueOrNull();
	}

	@Override
	public A findOrElse(Evaluable<? super A> predicate,
		Provider<? extends A> provider) {
		return findOrNone(predicate).valueOrElse(provider);
	}

	@Override
	public boolean all(Evaluable<? super A> predicate) {
		return Iterables.all(this, predicate);
	}

	@Override
	public boolean any(Evaluable<? super A> predicate) {
		return Iterables.any(this, predicate);
	}

	@Override
	public <B> Stream<B> map(final Applicable<? super A, ? extends B> function) {
		return new MapStream<A, B>(this, function);
	}

	@Override
	public <B, I extends Iterable<? extends B>> Stream<B> flatMap(
		final Applicable<? super A, I> function) {
		return new FlatMapStream<A, B, I>(this, function);
	}

	@Override
	public Stream<A> concat(final Stream<A> other) {
		return new ConcatStream<A>(this, other);
	}

	@Override
	public A first() {
		return get(0);
	}

	@Override
	public A second() {
		return get(1);
	}

	@Override
	public A third() {
		return get(2);
	}

	@Override
	public A last() {
		return get(size() - 1);
	}

	@Override
	public A get(int n) {
		return Iterables.get(this, n);
	}

	@Override
	public Set<A> toSet() {
		return Iterables.asSet(this);
	}

	@Override
	public List<A> toList() {
		return Iterables.asList(this);
	}

	@Override
	public A[] toArray(Class<? extends A> clazz) {
		Collection<A> list = toList();
		return list.toArray((A[]) Array.newInstance(clazz, list.size()));
	}

	@Override
	public String joinStrings(String separator) {
		return StringUtils.join(iterator(), separator);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
			this,
			NamedTupleToStringStyle.getInstance());
	}

}
