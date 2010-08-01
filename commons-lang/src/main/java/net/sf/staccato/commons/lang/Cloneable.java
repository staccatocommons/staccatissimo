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
package net.sf.staccato.commons.lang;

/**
 * Interface for objects that publicly understand a clone method. Not like the
 * standard {@link java.lang.Cloneable}, this interface exposes the
 * {@link #clone()} method
 * 
 * @author flbulgarelli
 */
public interface Cloneable extends java.lang.Cloneable {

	/**
	 * <p>
	 * 
	 * Clones this object.
	 * </p>
	 * <p>
	 * Unlike the standard clone mechanism, it has no sense that {@link Cloneable}
	 * s throw a {@link CloneNotSupportedException}, because {@link Cloneable}
	 * interface exposes the {@link #clone()} method.
	 * </p>
	 * <p>
	 * Implementors that rely on the {@link Object#clone()} implementation, should
	 * thus throw an {@link AssertionError} when catching the
	 * {@link CloneNotSupportedException} that <code>super</code> may throw.
	 * </p>
	 * 
	 * 
	 * @return a copy of this instance.
	 * @see Object#clone()
	 */
	public Cloneable clone();

}