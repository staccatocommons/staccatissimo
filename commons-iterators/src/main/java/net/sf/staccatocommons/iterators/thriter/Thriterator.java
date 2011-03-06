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
package net.sf.staccatocommons.iterators.thriter;

import java.util.Iterator;

import net.sf.staccatocommons.defs.EmptyAware;

/**
 * An object that both implements {@link Iterator} and {@link Thriter}
 * 
 * @author flbulgarelli
 * @see AbstractThriterator
 * @param <A>
 *          the type of elements retrieved by this {@link Thriterator}
 */
public interface Thriterator<A> extends Thriter<A>, Iterator<A>, EmptyAware {

}
