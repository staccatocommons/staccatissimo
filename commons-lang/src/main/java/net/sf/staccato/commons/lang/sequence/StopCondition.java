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
package net.sf.staccato.commons.lang.sequence;

/**
 * @author flbulgarelli
 *
 */
public interface StopCondition<T> {

	/**
	 * 
	 * @param next
	 * @return if sequencing should stop now, that is, if this element should be
	 *         retrieved or not
	 */
	boolean shouldStop(T next);
}
