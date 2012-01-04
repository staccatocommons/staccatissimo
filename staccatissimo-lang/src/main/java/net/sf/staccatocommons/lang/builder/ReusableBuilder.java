/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang.builder;

/**
 * A {@link ReusableBuilder} is a {@link Builder} whose {@link #build()} method
 * can be invoked more than once, thus the same builder can be used to more than
 * one object
 * 
 * @param <T>
 *          the type of built object
 */
public interface ReusableBuilder<T> extends Builder<T> {

  /**
   * Returns the built object.
   * <p>
   * This method may be invoked more than once. Thus, it will never throw a
   * {@link BuilderAlreadyUsedException}
   * <p>
   * If the built object of type T is modifiable, invocations to this method
   * must return always new instances of the object under construction.
   * Otherwise, and if configuration has not changed between two different
   * invocation the builder that case, is ok to return the same instance than in
   * the previous invocation, but it is not a requirement.
   * 
   * @throws ObjectUnderConstructionException
   *           as defined in {@link Builder}
   * @see Builder#build()
   */
  @Override
  T build() throws ObjectUnderConstructionException;

}
