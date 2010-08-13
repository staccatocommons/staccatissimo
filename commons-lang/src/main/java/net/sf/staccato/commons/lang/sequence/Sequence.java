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
package net.sf.staccato.commons.lang.sequence;

import java.util.Iterator;

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
// TODO make them possibly infinite
public class Sequence<T> extends UnmodifiableObject implements Iterable<T> {

	private static final long serialVersionUID = 8811454338704704525L;

	private final T start;

	private final Step<T> step;

	private final StopCondition<T> stopCondition;

	public Sequence(T start, Step<T> step, StopCondition<T> stopCondition) {
		this.start = start;
		this.step = step;
		this.stopCondition = stopCondition;
	}

	/**
	 * @return the start
	 */
	public T getStart() {
		return start;
	}

	/**
	 * @return the step
	 */
	public Step<T> getStep() {
		return step;
	}

	/**
	 * @return the stopCondition
	 */
	public StopCondition<T> getStopCondition() {
		return stopCondition;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private T next = getStart();

			@Override
			public boolean hasNext() {
				return !getStopCondition().shouldStop(next);
			}

			@Override
			public T next() {
				T next = this.next;
				this.next = getStep().next(next);
				return next;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Unmodifiable interator");
			}
		};
	}

	public static <T> Sequence<T> from(T start, Step<T> step,
		StopCondition<T> stopCondition) {
		return new Sequence<T>(start, step, stopCondition);
	}

	public static Sequence<Integer> fromBy(int from, int step) {
		return new Sequence(
			from,
			new IntegerIncrement(step),
			Endless.getInstance());
	}

	public static Sequence<Integer> fromToBy(int from, int to, int step) {
		return new Sequence(
			from,
			new IntegerIncrement(step),
			step < 0 ? Descend.upTo(to) : Ascend.upTo(to));
	}

	public static Sequence<Integer> fromTo(int from, int to) {
		return fromToBy(from, to, from < to ? 1 : -1);
	}

	public static Sequence<Integer> fromZeroTo(int to) {
		return fromToBy(0, to, 1);
	}

	public static Sequence<Integer> fromOneTo(int to) {
		return fromToBy(1, to, 1);
	}
}