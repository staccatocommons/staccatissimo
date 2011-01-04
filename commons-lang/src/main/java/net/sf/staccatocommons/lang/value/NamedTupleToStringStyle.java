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
package net.sf.staccatocommons.lang.value;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A {@link ToStringStyle} that formats an object like a named-tuple, were the
 * name is the object's class, and its components are its attributes.
 * 
 * For example, using this style, an instance of following class:
 * 
 * <pre>
 * class Foo {
 *  private String  bar;
 *  private int foobar;
 *   ....
 * }
 * </pre>
 * 
 * with bar = "hello" and foobar = 10, will be formatted as
 * 
 * <pre>
 * Foo(hello, 10)
 * </pre>
 * 
 * 
 * @author flbulgarelli
 * 
 */
public class NamedTupleToStringStyle extends ToStringStyle {
	private static final long serialVersionUID = -7126928068442420366L;

	private static final ToStringStyle INSTANCE = new NamedTupleToStringStyle();

	/**
	 * Creates a new {@link NamedTupleToStringStyle}
	 */
	public NamedTupleToStringStyle() {
		setUseFieldNames(false);
		setUseIdentityHashCode(false);
		setUseShortClassName(true);
		setContentStart("(");
		setContentEnd(")");
		setNullText("null");
	}

	/**
	 * @return the instance
	 */
	public static ToStringStyle getInstance() {
		return INSTANCE;
	}

}
