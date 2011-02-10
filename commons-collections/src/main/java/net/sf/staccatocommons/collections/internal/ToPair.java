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

package net.sf.staccatocommons.collections.internal;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public final class ToPair<A, B> extends AbstractFunction2<A, B, Pair<A, B>> {

	private static final AbstractFunction2 INSTANCE = new ToPair();

	@Override
	public Pair<A, B> apply(A arg1, B arg2) {
		return _(arg1, arg2);
	}

	/**
	 * Answers a constant {@link ToPair}
	 * 
	 * @return the instance
	 */
	@Constant
	@NonNull
	public static <A, B> AbstractFunction2<A, B, Pair<A, B>> getInstance() {
		return INSTANCE;
	}
}