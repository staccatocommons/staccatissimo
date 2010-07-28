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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.staccato.commons.lang.value.UnmodifiableObject;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
// TODO may be like a discrete range
// TODO make interval size-aware. perhaps could change implementation to be
// based on step numbers
// TODO make contains aware
public abstract class Interval<T extends Number & Comparable<T>> extends
	UnmodifiableObject implements Iterable<T> {

	private static final long serialVersionUID = 8811454338704704525L;

	private final Range<T> range;

	private final T step;

	protected final RangeOrder order;

	public Interval(Range<T> range, T step) {
		this.range = range;
		this.step = step;
		this.order = RangeOrder.getOrder(step);
	}

	public Interval(T from, T to, T step) {
		this(new Range<T>(from, to), step);
	}

	public Range<T> getRange() {
		return range;
	}

	public T getFrom() {
		return getRange().getMin();
	}

	public T getTo() {
		return getRange().getMax();
	}

	protected RangeOrder getOrder() {
		return order;
	}

	public T getStep() {
		return step;
	}

	public List<T> asList() {
		List<T> list = new LinkedList<T>();
		for (T i : this)
			list.add(i);
		return list;
	}

	public static Interval<Integer> fromToBy(int from, int to, int step) {
		return new IntegerInterval(from, to, step);
	}

	public static Interval<Long> fromToBy(long from, long to, long step) {
		return new LongInterval(from, to, step);
	}

	public static Interval<Integer> fromTo(int from, int to) {
		return fromToBy(from, to, from < to ? 1 : -1);
	}

	public static Interval<Long> fromTo(long from, long to) {
		return new LongInterval(from, to, from < to ? 1 : -1);
	}

	public static Interval<Integer> fromZeroTo(int to) {
		return new IntegerInterval(0, to, 1);
	}

	public static Interval<Long> fromZeroTo(long to) {
		return new LongInterval(0, to, 1);
	}

	public static Interval<Integer> fromOneTo(int to) {
		return new IntegerInterval(1, to, 1);
	}

	public static Interval<Long> fromOneTo(long to) {
		return new LongInterval(1, to, 1);
	}

	protected abstract class IntervalIterator implements Iterator<T> {

		private T next = getFrom();

		public void remove() {
			throw new UnsupportedOperationException(
				"Can not remove elements from a range");
		}

		public T next() {
			T next = this.next;
			this.next = computeNext(next);
			return next;
		}

		protected abstract T computeNext(T previous);

		public boolean hasNext() {
			return order.hasNext(next, getTo());
		}
	};

	protected enum RangeOrder {
		ASCENDENT {
			@Override
			public <T extends Number> boolean hasNext(Comparable<T> current, T to) {
				return current.compareTo(to) <= 0;
			}
		},
		DESCENDENT {
			@Override
			public <T extends Number> boolean hasNext(Comparable<T> current, T to) {
				return current.compareTo(to) >= 0;
			}
		};
		public abstract <T extends Number> boolean hasNext(Comparable<T> current,
			T to);

		public static RangeOrder getOrder(Number step) {
			return step.longValue() > 0 ? RangeOrder.ASCENDENT
				: RangeOrder.DESCENDENT;
		}

	}

}