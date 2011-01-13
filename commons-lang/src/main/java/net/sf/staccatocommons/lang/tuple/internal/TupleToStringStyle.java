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
package net.sf.staccatocommons.lang.tuple.internal;

import net.sf.staccatocommons.lang.value.NamedTupleToStringStyle;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author flbulgarelli
 */
public class TupleToStringStyle extends NamedTupleToStringStyle {

	private static final long serialVersionUID = -6109911670151971997L;
	private static final ToStringStyle INSTANCE = new TupleToStringStyle();

	/**
	 * Creates a new {@link TupleToStringStyle}
	 */
	public TupleToStringStyle() {
		super();
		setUseClassName(false);
	}

	public static ToStringStyle getInstance() {
		return INSTANCE;
	}

}
