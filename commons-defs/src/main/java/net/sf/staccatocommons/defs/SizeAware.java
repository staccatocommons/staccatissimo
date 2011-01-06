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
package net.sf.staccatocommons.defs;

/**
 * Interface for objects that understand {@link #size()} message. It extends the
 * {@link EmptyAware} interface.
 * 
 * @author flbulgarelli
 * 
 */
public interface SizeAware extends EmptyAware {

	/**
	 * Answers the size of this size aware. It <strong>should</strong> be a non
	 * negative int.
	 * 
	 * @return the size
	 */
	int size();

	/**
	 * Answers if this {@link SizeAware} is empty. It <strong>should</strong> be
	 * true that
	 * 
	 * <code>aSizeAware.isEmpty() == (aSizeAware.size() == 0)</code>
	 */
	@Override
	public boolean isEmpty();
}
