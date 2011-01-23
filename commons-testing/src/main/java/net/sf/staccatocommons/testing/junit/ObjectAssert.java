/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.testing.junit;

import static org.junit.Assert.*;

/**
 * @author flbulgarelli
 * 
 */
public class ObjectAssert {

	public static void assertInstanceof(Object object, Class<?> clazz) {
		assertTrue(
			String.format("Object %s must be instanceof class %s", object, clazz),
			clazz.isAssignableFrom(object.getClass()));

	}

}
