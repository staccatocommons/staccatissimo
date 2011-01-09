/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.number;

import net.sf.staccatocommons.defs.type.NumberType;

import org.apache.commons.lang.mutable.MutableInt;

/**
 * @author flbulgarelli
 * 
 */
public class MutableNumberTypes {

	public static final NumberType<MutableInt> MUTABLE_INT = new AbstractNumberType<MutableInt>() {

		public MutableInt add(MutableInt n0, MutableInt n1) {
			n0.add(n1);
			return n0;
		}

		public MutableInt multiply(MutableInt n0, MutableInt n1) {
			n0.setValue(n0.intValue() * n1.intValue());
			return n0;
		}

		public MutableInt divide(MutableInt n0, MutableInt n1) {
			n0.setValue(n0.intValue() / n1.intValue());
			return n0;
		}

		public MutableInt negate(MutableInt n) {
			n.setValue(-n.intValue());
			return n;
		}

		public MutableInt increment(MutableInt n) {
			n.increment();
			return n;
		}

		public MutableInt decrement(MutableInt n) {
			n.decrement();
			return n;
		}

		public MutableInt zero() {
			return new MutableInt(0);
		}

		public MutableInt one() {
			return new MutableInt(1);
		}

	};

}
