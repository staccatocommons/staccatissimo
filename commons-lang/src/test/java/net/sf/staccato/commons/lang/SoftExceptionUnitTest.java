package net.sf.staccato.commons.lang;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

public class SoftExceptionUnitTest {

	@Test
	public void testSoftException() {
		IOException cause = new IOException("Ups, VFS crashed!");
		SoftException softException = new SoftException(cause);
		assertSame(cause, softException.getCause());
	}

	@Test
	public void testSoften_Runtime() {
		IllegalArgumentException exception = new IllegalArgumentException(
			"bad input");
		RuntimeException soften = SoftException.soften(exception);
		assertSame(exception, soften);
	}

	@Test
	public void testSoften_Checked() {
		IOException exception = new IOException("bad file");
		RuntimeException soften = SoftException.soften(exception);
		assertSame(exception, soften.getCause());
	}
}
