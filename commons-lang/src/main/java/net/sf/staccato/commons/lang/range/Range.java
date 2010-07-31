/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.range;

import static net.sf.staccato.commons.lang.Compare.between;
import net.sf.staccato.commons.lang.Compare;
import net.sf.staccato.commons.lang.check.Ensure;
import net.sf.staccato.commons.lang.check.annotation.NonNull;
import net.sf.staccato.commons.lang.collection.ContainsAware;
import net.sf.staccato.commons.lang.collection.EmptyAware;
import net.sf.staccato.commons.lang.value.UnmodifiableObject;

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
public class Range<T extends Comparable<T>> extends UnmodifiableObject
	implements ContainsAware<T>, EmptyAware {

	private static final long serialVersionUID = -1096861117755452369L;

	private final T min;
	private final T max;

	/**
	 * Creates a new {@link Range}
	 * 
	 * @param min
	 *          the lower bound, inclusive. Non null.
	 * @param max
	 *          the upper bound, inclusive. Nun null.
	 */
	public Range(@NonNull T min, @NonNull T max) {
		Ensure.nonNull("min", min);
		Ensure.nonNull("max", max);
		// FIXME Ensure.lowerThan("max", max, min);
		this.min = min;
		this.max = max;
	}

	public boolean contains(T element) {
		return Compare.between(element, min, max);
	}

	/**
	 * 
	 * @param that
	 *          nonNull
	 * @return if the given {@link Range} overlaps with this one
	 */
	public boolean overlaps(@NonNull Range<T> that) {
		Ensure.nonNull("that", that);
		return between(this.getMin(), that.getMin(), that.getMax())
			|| between(this.getMax(), that.getMin(), that.getMax()) //
			|| this.includes(that) //
			|| that.includes(this);
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
		Ensure.nonNull("that", that);
		return this.getMin().compareTo(that.getMin()) <= 0
			&& this.getMax().compareTo(that.getMax()) >= 0;
	}

	/**
	 * @return the inclusive upper bound of this range. Non Null
	 */
	@NonNull
	public T getMax() {
		return max;
	}

	/**
	 * @return the inclusive lower bound of this range. Non Null.
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
	 *          the lower bound, inclusive. Non null.
	 * @param max
	 *          the upper bound, inclusive. Nun null.
	 * @return the new Range. Non null.
	 */
	@NonNull
	public static <T extends Comparable<T>> Range<T> range(@NonNull T min,
		@NonNull T max) {
		return new Range<T>(min, max);
	}
}
