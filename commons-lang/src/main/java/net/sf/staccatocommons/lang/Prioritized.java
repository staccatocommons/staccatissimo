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
package net.sf.staccatocommons.lang;

import java.io.Serializable;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.restriction.ConditionallyImmutable;
import net.sf.staccatocommons.defs.restriction.ConditionallySerializable;
import net.sf.staccatocommons.defs.restriction.Value;
import net.sf.staccatocommons.lang.value.RelevantState;

/**
 * A {@link Thunk} that is {@link Comparable}, based on a priority attribute.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          The type of object provided
 * @param <P>
 *          the type of priority object
 */
@Value
@ConditionallyImmutable
@ConditionallySerializable
public class Prioritized<T, P extends Comparable<P>> implements Comparable<Prioritized<T, P>>,
	Thunk<T>, Serializable {

	private static final long serialVersionUID = 7131041003021112454L;
	private static final RelevantState<Prioritized> state = new RelevantState<Prioritized>(1) {
		protected void collectState(Prioritized object, StateCollector s) {
			s.add(object.priority);
		}
	};
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

	/**
	 * @return the priority used to determine order in comparison
	 */
	@NonNull
	public P getPriority() {
		return priority;
	}

	public int compareTo(Prioritized<T, P> other) {
		return getPriority().compareTo(other.getPriority());
	}

	/**
	 * Test for equalty. In order to be consistent with
	 * {@link #compareTo(Prioritized)}, this method will only consider the
	 * priority attribute in the test
	 */
	public boolean equals(Object obj) {
		return state.equals(this, obj);
	}

	public int hashCode() {
		return state.hashCode(this);
	}

	public String toString() {
		return state.toString(this);
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
	public static <T, P extends Comparable<P>> Prioritized<T, P> from(T value, P priority) {
		return new Prioritized<T, P>(value, priority);
	}

}
