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
import net.sf.staccato.commons.lang.collection.ContainsAware;
import net.sf.staccato.commons.lang.collection.EmptyAware;
import net.sf.staccato.commons.lang.value.UnmodifiableObject;

public class Range<T extends Comparable<T>> extends UnmodifiableObject
	implements ContainsAware<T>, EmptyAware {

	private static final long serialVersionUID = -1096861117755452369L;

	private final T min;
	private final T max;

	// TODO validate min <= max
	public Range(T min, T max) {
		this.min = min;
		this.max = max;
	}

	public boolean contains(T element) {
		return Compare.between(element, min, max);
	}

	public boolean overlaps(Range<T> that) {
		return between(this.getMin(), that.getMin(), that.getMax())
			|| between(this.getMax(), that.getMin(), that.getMax()) //
			|| this.includes(that) // 
			|| that.includes(this);
	}

	public boolean includes(Range<T> that) {
		return this.getMin().compareTo(that.getMin()) <= 0
			&& this.getMax().compareTo(that.getMax()) >= 0;
	}

	public T getMax() {
		return max;
	}

	public T getMin() {
		return min;
	}

	@Override
	public boolean isEmpty() {
		return getMin().compareTo(getMax()) == 0;
	}

	public static <T extends Comparable<T>> Range<T> range(T min, T max) {
		return new Range<T>(min, max);
	}
}
