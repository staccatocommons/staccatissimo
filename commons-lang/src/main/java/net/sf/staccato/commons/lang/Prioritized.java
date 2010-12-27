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

import java.io.Serializable;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.internal.ToString;
import net.sf.staccato.commons.lang.value.BasicEquals;
import net.sf.staccato.commons.lang.value.ConditionallyImmutable;
import net.sf.staccato.commons.lang.value.ConditionallySerializable;
import net.sf.staccato.commons.lang.value.Value;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A {@link Provider} that is {@link Comparable}, based on a priority attribute.
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
public class Prioritized<T, P extends Comparable<P>> implements Provider<T>,
	Comparable<Prioritized<T, P>>, Serializable {

	private static final long serialVersionUID = 7131041003021112454L;

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
		BasicEquals be = BasicEquals.from(this, obj);
		if (be.isEqualsDone())
			return be.toEquals();
		Prioritized<T, P> that = (Prioritized<T, P>) obj;
		return new EqualsBuilder()//
			.append(priority, that.priority)
			.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder()//
			.append(priority)
			.toHashCode();
	}

	public String toString() {
		return ToString.toString(this);
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
