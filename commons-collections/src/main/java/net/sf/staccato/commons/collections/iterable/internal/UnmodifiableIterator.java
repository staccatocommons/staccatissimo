package net.sf.staccato.commons.collections.iterable.internal;

import java.util.Iterator;

public class UnmodifiableIterator<T> implements Iterator<T> {

	private final Iterator<T> iter;

	public UnmodifiableIterator(Iterator<T> iter) {
		this.iter = iter;
	}

	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public T next() {
		return iter.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("This Iterator is unmodifiable");
	}

}
