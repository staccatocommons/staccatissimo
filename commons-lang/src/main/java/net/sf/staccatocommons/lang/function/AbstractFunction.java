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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceChecks;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public abstract class AbstractFunction<A, B> extends AbstractDelayable<A, B> implements
	Function<A, B> {

	@NonNull
	@ForceChecks
	public Thunk<B> of(@NonNull final Thunk<? extends A> thunk) {
		return new Thunk<B>() {
			public B value() {
				return AbstractFunction.this.apply(thunk.value());
			}
		};
	}

	@NonNull
	@ForceChecks
	public <C> Function<C, B> of(@NonNull final Applicable<? super C, ? extends A> other) {
		return new AbstractFunction<C, B>() {
			public B apply(C arg) {
				return AbstractFunction.this.apply(other.apply(arg));
			}
		};
	}

	@NonNull
	@ForceChecks
	public <Tp1, Tp2> Function2<Tp1, Tp2, B> of(
		@NonNull final Applicable2<Tp1, Tp2, ? extends A> other) {
		return new AbstractFunction2<Tp1, Tp2, B>() {
			public B apply(Tp1 arg1, Tp2 arg2) {
				return AbstractFunction.this.apply(other.apply(arg1, arg2));
			}
		};
	}

	@NonNull
	@ForceChecks
	public <Tp1, Tp2, Tp3> Function3<Tp1, Tp2, Tp3, B> of(
		@NonNull final Applicable3<Tp1, Tp2, Tp3, ? extends A> other) {
		return new AbstractFunction3<Tp1, Tp2, Tp3, B>() {
			public B apply(Tp1 arg1, Tp2 arg2, Tp3 arg3) {
				return AbstractFunction.this.apply(other.apply(arg1, arg2, arg3));
			}
		};
	}

	@NonNull
	public Function<A, B> nullSafe() {
		return new AbstractFunction<A, B>() {
			public B apply(A arg) {
				if (arg == null)
					return null;
				return AbstractFunction.this.apply(arg);
			}
		};
	}

	public String toString() {
		return "Function";
	}

}
