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

import java.io.Serializable;

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.function.Function2;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractNumberType<A extends Number & Comparable> implements NumberType<A>,
	Serializable {

	private static final long serialVersionUID = -2727245678088637829L;

	private transient Function2<A, A, A> add = new Add();

	private transient Function2<A, A, A> multiply = new Multiply();

	public boolean isZero(A n) {
		return compare(n, zero()) == 0;
	}

	public boolean isNegative(A n) {
		return compare(n, zero()) < 0;
	}

	public boolean isPositive(A n) {
		return compare(n, zero()) > 0;
	}

	public A subtract(A n0, A n1) {
		return add(n0, negate(n1));
	}

	public int compare(A o1, A o2) {
		return o1.compareTo(o2);
	}

	public A increment(A n) {
		return add(n, one());
	}

	public A decrement(A n) {
		return subtract(n, one());
	}

	@Override
	public Function2<A, A, A> add() {
		return add;
	}

	@Override
	public Function2<A, A, A> multiply() {
		return multiply;
	}

	public Function<A, A> add(A n) {
		return add().apply(n);
	}

	private final class Add extends NumberTypeFunction2 {
		@Override
		public A apply(A arg1, A arg2) {
			return add(arg1, arg2);
		}
	}

	private final class Multiply extends NumberTypeFunction2 {
		@Override
		public A apply(A arg1, A arg2) {
			return multiply(arg1, arg2);
		}
	}

	/**
	 * NumberTypeFunctions, override {@link Function2#apply(Object)} so that the
	 * resulting function is flipped and it implements {@link ImplicitNumberType}
	 * too
	 * 
	 * @author flbulgarelli
	 * 
	 */
	private abstract class NumberTypeFunction2 extends Function2<A, A, A> implements
		ImplicitNumberType<A> {
		public final NumberType<A> numberType() {
			return AbstractNumberType.this;
		}

		public Function<A, A> apply(final A arg1) {
			abstract class NumberTypeFunction extends Function<A, A> implements ImplicitNumberType<A> {
				public NumberType<A> numberType() {
					return NumberTypeFunction2.this.numberType();
				}
			}
			return new NumberTypeFunction() {
				public A apply(A arg) {
					return NumberTypeFunction2.this.apply(arg, arg1);
				}
			};
		}

	}
}