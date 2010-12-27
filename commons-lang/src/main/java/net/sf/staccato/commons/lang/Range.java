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
import net.sf.staccato.commons.lang.collection.ContainsAware;
import net.sf.staccato.commons.lang.collection.EmptyAware;
import net.sf.staccato.commons.lang.value.ConditionallyImmutable;
import net.sf.staccato.commons.lang.value.ConditionallySerializable;
import net.sf.staccato.commons.lang.value.Value;
import net.sf.staccato.commons.lang.value.ValueObject;

/**
 * A {@link Range} represents a an inclusive lower and upper bound of
 * {@link Comparable} objects.
 * 
 * {@link Range}s are unmodifiable.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
@Value
@ConditionallyImmutable
@ConditionallySerializable
public class Range<T extends Comparable<T>> extends ValueObject implements ContainsAware<T>,
	EmptyAware {

	private static final long serialVersionUID = -1096861117755452369L;

	private final T min;
	private final T max;

	/**
	 * Creates a new {@link Range}
	 * 
	 * @param min
	 *          the lower bound, inclusive.
	 * @param max
	 *          the upper bound, inclusive.
	 */
	public Range(@NonNull T min, @NonNull T max) {
		// Ensure.that().isGreaterThanOrEqualTo("max", max, min);
		this.min = min;
		this.max = max;
	}

	/**
	 * Tests if a given element is between this range lower and upper bounds
	 */
	public boolean contains(@NonNull T element) {
		return Compare.between(element, min, max);
	}

	/**
	 * 
	 * @param that
	 *          nonNull
	 * @return if the given {@link Range} overlaps with this one
	 */
	public boolean overlaps(@NonNull Range<T> that) {
		return this.contains(that.getMin()) //
			|| this.contains(that.getMin()) //
			|| that.contains(this.getMin());
	}

	/**
	 * Tests that a given range lower bounds is equal or greater to this one, and
	 * that its upper bound are equal or lower to this one
	 * 
	 * @param that
	 *          another range to test for inclusion
	 * @return if the given range is included in the receptor
	 */
	public boolean includes(@NonNull Range<T> that) {
		return this.getMin().compareTo(that.getMin()) <= 0
			&& this.getMax().compareTo(that.getMax()) >= 0;
	}

	/**
	 * Answers the upper bound
	 * 
	 * @return the inclusive upper bound of this range.
	 */
	@NonNull
	public T getMax() {
		return max;
	}

	/**
	 * Answers the lower bound
	 * 
	 * @return the inclusive lower bound of this range.
	 */
	@NonNull
	public T getMin() {
		return min;
	}

	/**
	 * @return if range min and max are equal, based on
	 *         {@link Comparable#compareTo(Object)}
	 */
	@Override
	public boolean isEmpty() {
		return getMin().compareTo(getMax()) == 0;
	}

	/**
	 * Factory method that creates a new {@link Range}
	 * 
	 * @param <T>
	 *          the type of range
	 * @param min
	 *          the lower bound, inclusive.
	 * @param max
	 *          the upper bound, inclusive.
	 * @return the new Range.
	 */
	@NonNull
	public static <T extends Comparable<T>> Range<T> from(@NonNull T min, @NonNull T max) {
		return new Range<T>(min, max);
	}

}
