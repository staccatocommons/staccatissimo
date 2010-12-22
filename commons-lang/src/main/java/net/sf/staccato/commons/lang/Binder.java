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
package net.sf.staccato.commons.lang;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * 
 * {@link Binder}s are objects that can transform one or argument into a result,
 * if possible, or not perform any transformation, if binding can not succeed.
 * 
 * {@link Binder}s <strong>should not</strong> have side effects
 * 
 * @author flbulgarelli
 * 
 * @param <I>
 *          the binding argument
 * @param <B>
 *          the binding result
 */
public interface Binder<I, B> {

	/**
	 * Tries to bind argument to an object of type B. Returns {@link Some} if
	 * succeeds, or {@link None} if argument can not be bound. This method
	 * <strong>should not</strong> have side effects.
	 * 
	 * @param arg
	 *          the binding argument
	 * @return the binding result, if binding succeeded, or none, otherwise
	 */
	@NonNull
	Option<B> bind(I arg);

}
