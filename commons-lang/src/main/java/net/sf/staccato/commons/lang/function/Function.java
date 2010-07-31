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
package net.sf.staccato.commons.lang.function;

import net.sf.staccato.commons.lang.Applicable;

/**
 * A one argument function.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          function argument type
 * @param <R>
 *          function return type
 */
public abstract class Function<T, R> implements Applicable<T, R> {

	@Override
	public abstract R apply(T arg);

	public <Rp> Function<T, Rp> compose(final Applicable<? super R, Rp> other) {
		return new Function<T, Rp>() {
			public Rp apply(T arg) {
				return other.apply(Function.this.apply(arg));
			}
		};
	}

	public <Tp> Function<Tp, R> o(final Function<Tp, ? extends T> other) {
		return other.compose(this);
	}

	/*
	 * 
	 * 
	 * @Override public O apply(I input) { try { return super.apply(input); }
	 * catch (RuntimeException e) { if (exceptionType.isInstance(e)) return
	 * catchFunction.apply(input, (E) e); throw e; } }
	 * 
	 * public O apply(I input) { try { return super.apply(input); } finally {
	 * this.finallyBlock.exec(input); } }
	 */
}
