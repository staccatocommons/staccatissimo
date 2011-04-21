/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.number.NumberTypeAware;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * NumberTypeFunctions, override {@link AbstractFunction2#apply(Object)} so that
 * the resulting function is flipped and it implements
 * {@link NumberTypeAware} too
 * 
 * @author flbulgarelli
 * 
 */
public abstract class NumberTypeFunction2<A> extends AbstractFunction2<A, A, A> implements
	NumberTypeAware<A> {

	private final NumberType<A> type;

	/**
	 * Creates a new {@link NumberTypeFunction2}
	 */
	public NumberTypeFunction2(@NonNull NumberType<A> type) {
		this.type = type;
	}

	public final NumberType<A> numberType() {
		return type;
	}

	public Function<A, A> apply(final A arg0) {
		abstract class NumberTypeFunction extends AbstractFunction<A, A> implements
			NumberTypeAware<A> {
			public NumberType<A> numberType() {
				return NumberTypeFunction2.this.numberType();
			}
		}
		return new NumberTypeFunction() {
			public A apply(A arg1) {
				return NumberTypeFunction2.this.apply(arg1, arg0);
			}
		};
	}

}