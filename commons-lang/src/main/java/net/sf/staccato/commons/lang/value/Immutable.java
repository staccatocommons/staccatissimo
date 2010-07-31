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
package net.sf.staccato.commons.lang.value;

/**
 * 
 * {@link Immutable} are objects that are {@link Unmodifiable}, and, also either
 * all their attributes are primitives, immutable objects - {@link String}s,
 * {@link Integer}, or have no attributes at all.
 * 
 * {@link Immutable}s are thread-safe, and grant that any method will produce
 * always the same result.
 * 
 * @author fbulgarelli
 */
public interface Immutable extends Unmodifiable {

}
