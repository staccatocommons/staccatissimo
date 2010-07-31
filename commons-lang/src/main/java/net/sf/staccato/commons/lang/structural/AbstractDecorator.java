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
package net.sf.staccato.commons.lang.structural;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public abstract class AbstractDecorator<T> {

	private final T decorated;

	public AbstractDecorator(T decorated) {
		this.decorated = decorated;
	}

	public T getDecorated() {
		return this.decorated;
	}

	public T getDecorationRoot() {
		if (getDecorated() instanceof AbstractDecorator)
			return ((AbstractDecorator<T>) getDecorated()).getDecorationRoot();

		return getDecorated();
	}

	public boolean isInstanceOf(Class<?> type) {
		if (type.isInstance(this))
			return true;

		if (type.isInstance(getDecorated()))
			return true;

		if (getDecorated() instanceof AbstractDecorator)
			return ((AbstractDecorator<T>) getDecorated()).isInstanceOf(type);

		return false;

	}

	public <TT> TT asInstanceOf(Class<TT> type) {
		if (type.isInstance(this))
			return (TT) this;

		if (type.isInstance(getDecorated()))
			return (TT) this;

		if (getDecorated() instanceof AbstractDecorator)
			return ((AbstractDecorator<T>) getDecorated()).asInstanceOf(type);

		throw new ClassCastException("This decorator does not decorate any "
			+ type.getName());

	}

}
