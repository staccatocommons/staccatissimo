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
	// TODO make generic

	public Cloneable clone();
}