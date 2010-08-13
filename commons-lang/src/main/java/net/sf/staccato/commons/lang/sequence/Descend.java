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


/**
 * @author flbulgarelli
 * @param <T>
 */
public class Descend<T extends Comparable<T>> implements
	StopCondition<T> {

	private final T stopValue;

	public Descend(T stopValue) {
		this.stopValue = stopValue;
	}

	@Override
	public boolean shouldStop(T next) {
		return next.compareTo(stopValue) < 0;
	}

	public static <T extends Comparable<T>> Descend<T> upTo(T value) {
		return new Descend<T>(value);
	}

}
