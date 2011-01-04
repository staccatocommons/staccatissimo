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
package net.sf.staccatocommons.lang;

import static org.junit.Assert.assertSame;
import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.lang.provider.internal.Constant;

import org.junit.Test;

public class ConstantUnitTest {

	@Test
	public void testValue() {
		String value = "String";
		Provider<String> constant = new Constant(value);
		assertSame(value, constant.value());
	}

}
