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
 * 
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of provided value
 */
public interface Provider<T> {

	/**
	 * Returns the value provided. This interfaces does not make any assumptions
	 * respect of the source of this object nor its nature. It may return always
	 * the same instance or different one, the same or different implementations,
	 * etc. This interface does not make any assumptions about the kind of
	 * {@link RuntimeException} this method could throw.
	 * 
	 * @return the provided object. Nullable, but implementors may guarantee it is
	 *         non null.
	 * 
	 */
	T value();

}
