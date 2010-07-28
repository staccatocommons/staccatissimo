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

import java.math.BigInteger;
import java.util.Iterator;

public class BigIntegerInterval extends Interval<BigInteger> {

	private static final long serialVersionUID = -5616430676570520814L;

	public BigIntegerInterval(BigInteger from, BigInteger to, BigInteger step) {
		super(from, to, step);
	}

	public Iterator<BigInteger> iterator() {
		return new IntervalIterator() {
			@Override
			protected BigInteger computeNext(BigInteger previous) {
				return previous.add(getStep());
			}
		};
	}

}
