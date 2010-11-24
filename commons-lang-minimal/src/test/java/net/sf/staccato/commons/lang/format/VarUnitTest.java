package net.sf.staccato.commons.lang.format;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class VarUnitTest {

	@Test
	public void testFormatStringObject() {
		assertNotNull(Var.format("myVar", "hello"));
	}

	@Test
	public void testFormatStringStringObjectString() {
		assertNotNull(Var.format("Hello, ", "myVar", "hello"));
	}

	@Test
	public void testFormatStringStringObject() {
		assertNotNull(Var.format("Hello", "myVar", 5, "end"));
	}

}
