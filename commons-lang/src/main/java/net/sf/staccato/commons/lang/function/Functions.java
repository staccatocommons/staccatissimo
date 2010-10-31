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

import net.sf.staccato.commons.lang.function.internal.Constant;
import net.sf.staccato.commons.lang.function.internal.Identity;

/**
 * Class factory methods for some common {@link Function}s
 * 
 * @author flbulgarelli
 */
public class Functions {

	/**
	 * Returns the identity function, that is, a {@link Function} that takes a non
	 * nullable argument and returns it.
	 * 
	 * @param <I>
	 * @return the singleton identity function
	 */
	public static <I> Function<I, I> identity() {
		return Identity.getInstance();
	}

	public static <I, O> Function<I, O> constant(final O value) {
		return new Constant<I, O>(value);
	}

	public static Function2<Integer, Integer, Integer> integerSum() {
		return new Function2<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer arg1, Integer arg2) {
				return arg1 + arg2;
			}
		};
	}

}
