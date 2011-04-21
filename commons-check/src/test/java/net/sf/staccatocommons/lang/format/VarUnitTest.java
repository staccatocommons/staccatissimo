/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang.format;

import static org.junit.Assert.*;
import net.sf.staccatocommons.check.format.Var;

import org.junit.Test;

/**
 * Test for {@link Var}
 * 
 * @author flbulgarelli
 * 
 */
public class VarUnitTest {
	/***/
	@Test
	public void testFormatStringObject() {
		assertNotNull(Var.format("myVar", "hello"));
	}

	/***/
	@Test
	public void testFormatStringStringObjectString() {
		assertNotNull(Var.format("Hello, ", "myVar", (Object) "hello"));
	}

	/***/
	@Test
	public void testFormatStringStringObject() {
		assertNotNull(Var.format("Hello", "myVar", 5, "end"));
	}

}
