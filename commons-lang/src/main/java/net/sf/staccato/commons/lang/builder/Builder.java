/*
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
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          The type of built object
 */
public interface Builder<T> {

	/**
	 * 
	 * Returns the built object.
	 * <p>
	 * Builders are not meant to be used more than once. So this method can only
	 * be invoked successfully one time. Subsequent invocations after a successful
	 * return will throw an {@link BuilderAlreadyUsedException}. However,
	 * implementors may relax this, and be reusable, that is being able to be used
	 * more than once; such implementors should document that feature.
	 * <p>
	 * Builders may impose restriction to the state of the object under
	 * construction. If such constraints are not met while invoking this method, a
	 * {@link ObjectUnderConstructionException} exception must be thrown.
	 * <p/>
	 * 
	 * @return The built object.
	 * @throws ObjectUnderConstructionException
	 *           If the object building process has not finished yet
	 * @throws BuilderAlreadyUsedException
	 *           If the building process has finished, but this method has already
	 *           being invoked without throwing an exception.
	 */
	T build() throws ObjectUnderConstructionException,
		BuilderAlreadyUsedException;

}