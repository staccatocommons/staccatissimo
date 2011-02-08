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
package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 */
public abstract class AbstractFunction2<A, B, C> extends AbstractDelayable2<A, B, C> implements
	Function2<A, B, C> {

	@NonNull
	public Function<B, C> apply(final A arg1) {
		return new AbstractFunction<B, C>() {
			public C apply(B arg2) {
				return AbstractFunction2.this.apply(arg1, arg2);
			}
		};
	}

	public Function2<B, A, C> flip() {
		return new AbstractFunction2<B, A, C>() {
			public C apply(B arg2, A arg1) {
				return AbstractFunction2.this.apply(arg1, arg2);
			}
		};
	}

	public final Function2<A, B, C> nullSafe() {
		return new AbstractFunction2<A, B, C>() {
			public C apply(A arg1, B arg2) {
				if (arg1 == null || arg2 == null)
					return null;
				return AbstractFunction2.this.apply(arg1, arg2);
			}
		};
	}

	public String toString() {
		return "Function2";
	}

}