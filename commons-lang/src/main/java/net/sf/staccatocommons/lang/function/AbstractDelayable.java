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
import net.sf.staccatocommons.defs.Delayable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractDelayable<A, B> implements Applicable<A, B>, Delayable<A, B> {

	@Override
	public Thunk<B> delayed(final A arg) {
		return new Thunk<B>() {
			public B value() {
				return AbstractDelayable.this.apply(arg);
			}
		};
	}

	@NonNull
	@ForceRestrictions
	@Override
	public Thunk<B> delayedValue(@NonNull final Thunk<? extends A> thunk) {
		return new Thunk<B>() {
			public B value() {
				return apply(thunk.value());
			}
		};
	}

}
