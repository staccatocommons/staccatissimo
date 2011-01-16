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

import static org.junit.Assert.*;

import java.io.IOError;
import java.io.IOException;

import org.junit.Test;

/**
 * Test for {@link SoftException}
 * 
 * @author flbulgarelli
 * 
 */
public class SoftExceptionUnitTest {

	/***/
	@Test
	public void testSoftException() {
		IOException cause = new IOException("Ups, VFS crashed!");
		SoftException softException = new SoftException(cause);
		assertSame(cause, softException.getCause());
	}

	/***/
	@Test
	public void testSoften_Runtime() {
		IllegalArgumentException exception = new IllegalArgumentException("bad input");
		RuntimeException soften = SoftException.soften(exception);
		assertSame(exception, soften);
	}

	/***/
	@Test
	public void testSoften_Checked() {
		IOException exception = new IOException("bad file");
		RuntimeException soften = SoftException.soften(exception);
		assertSame(exception, soften.getCause());
	}

	/** Test for {@link SoftException#harden(RuntimeException)} */
	@Test
	public void testHarden() throws Exception {
		Exception checked = new IOException();
		RuntimeException unchecked = new RuntimeException();
		RuntimeException uncheckdWithNestedError = new RuntimeException(new IOError(checked));

		assertSame(checked, SoftException.harden(SoftException.soften(checked)));
		assertSame(unchecked, SoftException.harden(unchecked));
		assertSame(checked, SoftException.harden(new RuntimeException(checked)));
		assertSame(checked, SoftException.harden(new RuntimeException(new RuntimeException(checked))));
		assertSame(uncheckdWithNestedError, SoftException.harden(uncheckdWithNestedError));
	}
}
