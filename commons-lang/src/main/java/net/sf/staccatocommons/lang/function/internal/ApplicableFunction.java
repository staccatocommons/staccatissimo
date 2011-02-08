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
package net.sf.staccatocommons.lang.function.internal;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.lang.function.AbstractFunction;

/**
 * @author flbulgarelli
 * 
 */
public class ApplicableFunction<A, B> extends AbstractFunction<A, B> {

	private Applicable<? super A, ? extends B> applicable;

	/**
	 * Creates a new {@link ApplicableFunction}
	 */
	public ApplicableFunction(Applicable<? super A, ? extends B> applicable) {
		this.applicable = applicable;
	}

	public B apply(A arg) {
		return applicable.apply(arg);
	}

}
