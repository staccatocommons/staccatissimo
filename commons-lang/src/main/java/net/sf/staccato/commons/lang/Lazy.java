/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang;

/**
 * 
 * A {@link Lazy} is an abstract wrapper around a value initialization code that
 * lets it postpone it creation, or even avoid it if not necessary. Client code
 * creating the lazy value just need to extend {@link Lazy} and implement
 * {@link #init()} method. Client code that needs to consume its value, must
 * call {@link #value()}
 * 
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 * 
 */
public abstract class Lazy<T> implements Provider<T> {

	private Option<T> lazyValue = Option.none();

	public T value() {
		if (lazyValue.isUndefined())
			lazyValue = Option.some(init());
		return lazyValue.value();
	}

	/**
	 * Initializes the lazy value. This code will never be executed, if
	 * {@link #value()} is never called, or executed only once, if
	 * {@link #value()} is called at least one time.
	 * 
	 * @return the initialized value. May be null.
	 */
	protected abstract T init();

	@Override
	public String toString() {
		return value().toString();
	}

}
