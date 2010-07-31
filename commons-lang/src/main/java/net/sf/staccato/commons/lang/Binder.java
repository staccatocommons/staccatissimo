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

/**
 * @author flbulgarelli
 * 
 * @param <I>
 * @param <B>
 */
public interface Binder<I, B> {

	/**
	 * Tries to bind argument to an object of type B. Returns {@link Some} if
	 * succeeds, or {@link None} if argument can not be bound
	 * 
	 * @param arg
	 * @return
	 */
	Option<B> bind(I arg);

}
