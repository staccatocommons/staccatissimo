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

import java.util.Collection;
import java.util.Iterator;

import net.sf.staccato.commons.collections.iterable.internal.UnmodifiableIterator;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public class CollectionStream<T> extends AbstractStream<T> {

	private final Collection<T> collection;

	/**
	 * Creates a new {@link CollectionStream}
	 * 
	 * @param collection
	 *          the collection to wrap
	 */
	public CollectionStream(Collection<T> collection) {
		this.collection = collection;
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public boolean contains(T element) {
		return collection.contains(element);
	}

	@Override
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return new UnmodifiableIterator<T>(collection.iterator());
	}

	protected Collection<T> getCollection() {
		return collection;
	}

}