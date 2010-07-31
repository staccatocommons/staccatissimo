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
package net.sf.staccato.commons.lang.builder;

/**
 * An constant-state builder.
 * <p>
 * {@link Builder}s are by definition statefull, as they act as a temporal store
 * for all or part of the object under construction, and are thus naturally
 * modifiable. {@link UnmodifiableBuilder}s, on the contrary, are unmodifiable,
 * and thus, per each construction stage finished, a new
 * {@link UnmodifiableBuilder} must be returned. Implementors thus must grant
 * that per each configuration method, a new instance of a builder must be
 * returned.
 * </p>
 * <p>
 * In order to be consistent with it {@link Builder} interface, and be
 * unmodifiable, {@link #build()} method must be able be invoked any number of
 * times, and always
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public interface UnmodifiableBuilder<T> extends ReusableBuilder<T> {

	/**
	 * Returns the object under construction. See {@link ReusableBuilder#build()}
	 * for more details. Returns always the same instance.
	 */
	public T build() throws ObjectUnderConstructionException;

}
