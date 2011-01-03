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
package net.sf.staccato.commons.lang.block;

import org.apache.commons.lang.mutable.MutableInt;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;

/**
 * @author flbulgarelli
 * 
 */
final class IncrementAction extends CustomAction {
	/**
	 * 
	 */
	private final MutableInt mi;

	/**
	 * Creates a new {@link IncrementAction}
	 */
	IncrementAction(MutableInt mi) {
		super("increment");
		this.mi = mi;
	}

	public Object invoke(Invocation invocation) throws Throwable {
		mi.increment();
		return null;
	}
}