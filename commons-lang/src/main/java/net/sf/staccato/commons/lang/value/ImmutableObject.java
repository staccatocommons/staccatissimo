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
 * {@link ImmutableObject} is an {@link Immutable} intended to be sublcassed and
 * suggested root class for such objects. It caches caches toString and hashCode
 * result.
 * 
 * @author fbulgarelli
 * 
 */
public class ImmutableObject extends ValueObject implements Immutable {

	private static final long serialVersionUID = -7152890149830790459L;

	private transient String toString = null;

	private transient int hashCode = -1;

	@Override
	public String toString() {
		if (toString == null)
			toString = super.toString();
		return toString;
	}

	@Override
	public int hashCode() {
		if (hashCode == -1)
			hashCode = super.hashCode();
		return hashCode;
	}

}
