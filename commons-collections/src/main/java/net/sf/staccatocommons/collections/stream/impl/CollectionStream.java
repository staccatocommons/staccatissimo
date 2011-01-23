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
package net.sf.staccatocommons.collections.stream.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.UnmodifiableIterator;
import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.AbstractStream;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public class CollectionStream<A> extends AbstractStream<A> {

	private final Collection<? extends A> collection;

	/**
	 * Creates a new {@link CollectionStream}
	 * 
	 * @param collection
	 *          the collection to wrap
	 */
	public CollectionStream(@NonNull Collection<? extends A> collection) {
		this.collection = collection;
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public boolean contains(A element) {
		return collection.contains(element);
	}

	@Override
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	@Override
	public Iterator<A> iterator() {
		return new UnmodifiableIterator<A>(collection.iterator());
	}

	@Override
	public List<A> toList() {
		return Iterables.toList(getCollection());
	}

	public A[] toArray(Class<? extends A> clazz) {
		return toArray(clazz, getCollection());
	}

	protected Collection<A> getCollection() {
		return (Collection<A>) collection;
	}

}