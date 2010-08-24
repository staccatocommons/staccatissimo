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

import java.io.Serializable;

/**
 * 
 * {@link Value} interface describes statefull objects whose identity is not
 * important.
 * <p>
 * Values are {@link Serializable}, and must:
 * <ul>
 * <li>Implement equality so that it is not based in identity, but in its
 * internal state instead. Values must override {@link #equals(Object)}</li>
 * <li>Implement hashCode to be consistent with equals, in order to be compliant
 * with general {@link Object#hashCode()} contract</li>
 * <li>{@link Value}s implement {@link Object#toString()} in order to provide a
 * descriptive representation of the object state</li>
 * </ul>
 * 
 * @author flbulgarelli
 * 
 */
public interface Value extends Serializable {

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
