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

package net.sf.staccatocommons.defs;

/**
 * A {@link Provider} is an object that is capable of returning another one.
 * <p>
 * {@link Provider}s of return type {@link Void} have the semantics compatible
 * with {@link Runnable}
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of provided value
 * 
 * @see Applicative Recomendations for implementing
 */
@Applicative
public interface Provider<T> {

	/**
	 * Returns the value provided.
	 * 
	 * @return the provided object.
	 */
	T value();

}
