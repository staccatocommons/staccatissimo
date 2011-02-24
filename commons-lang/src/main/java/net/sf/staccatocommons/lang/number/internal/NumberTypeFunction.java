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
package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.number.ImplicitNumberType;

/**
 * A {@link Function} that implements {@link ImplicitNumberType}
 * 
 * @author flbulgarelli
 * 
 * @param <B>
 */
public abstract class NumberTypeFunction<A, B> extends AbstractFunction<A, B> implements
	ImplicitNumberType<B> {

	/**
	 * 
	 */
	private final NumberType<B> numberType;

	/**
	 * Creates a new {@link NumberTypeFunction}
	 */
	public NumberTypeFunction(NumberType<B> abstractNumberType) {
		numberType = abstractNumberType;
	}

	public NumberType<B> numberType() {
		return numberType;
	}
}