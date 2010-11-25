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
package net.sf.staccato.commons.lang;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * A {@link Provider} that is {@link Comparable}, based on a priority attribute.
 * 
 * {@link Prioritized}s are unmodifiable.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          The type of object provided
 * @param <P>
 *          the type of priority object
 */
public class Prioritized<T, P extends Comparable<P>> implements Provider<T>,
	Comparable<Prioritized<T, P>> {

	private final P priority;
	private final T value;

	/**
	 * Creates a new {@link Prioritized}
	 * 
	 * @param value
	 *          the provided - prioritized - value. Nullable.
	 * @param priority
	 *          the priority. Non Null.
	 */
	public Prioritized(T value, @NonNull P priority) {
		this.value = value;
		this.priority = priority;
	}

	public T value() {
		return value;
	}

	public int compareTo(Prioritized<T, P> other) {
		return getPriority().compareTo(other.getPriority());
	}

	/**
	 * @return the priority used to determine order in comparison
	 */
	@NonNull
	public P getPriority() {
		return priority;
	}

	/**
	 * Factory method that creates a {@link Prioritized}
	 * 
	 * @param <T>
	 * @param <P>
	 * @param value
	 *          the provided value. Non Nullable.
	 * @param priority
	 *          Non Null.
	 * @return a new {@link Prioritized}
	 */
	@NonNull
	public static <T, P extends Comparable<P>> Prioritized<T, P> from(T value,
		P priority) {
		return new Prioritized<T, P>(value, priority);
	}

}
