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

public class LongInterval extends Interval<Long> {

	private static final long serialVersionUID = -473647460682173025L;

	public LongInterval(long from, long to, long step) {
		super(from, to, step);
	}

	public Iterator<Long> iterator() {
		return new IntervalIterator() {

			@Override
			protected Long computeNext(Long previous) {
				return previous + getStep();
			}
		};
	}
}