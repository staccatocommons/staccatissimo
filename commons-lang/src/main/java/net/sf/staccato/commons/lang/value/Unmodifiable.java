/*
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
 * {@link Unmodifiable}s are {@link Value}s that do not expose any method that
 * may modify receiver internal state. However, object's state can still be
 * mutated indirectly, if it holds and or exposes references to object that may
 * be mutated, so {@link Unmodifiable}s are also immutable, but only as long as
 * the references held by them are immutable too.
 * 
 * @author flbulgarelli
 */
public interface Unmodifiable extends Value {

}
