package net.sf.staccato.commons.lang;

import static org.junit.Assert.*;

import org.junit.Test;

public class NullsUnitTest {

	@Test
	public void testNonNullOrElseTT() {
		assertEquals("Foo", IsNonNull.orElse(null, "Foo"));
		assertEquals("Bar", IsNonNull.orElse("Bar", "Foo"));
	}

	@Test
	public void testNonNullOrElseTProviderOfT() {
		assertEquals("Foo", IsNonNull.orElse(null, Constant.from("Foo")));
		assertEquals("Bar", IsNonNull.orElse("Bar", Constant.from("Foo")));
	}

	@Test
	public void testNonNullOrNone() {
		assertTrue(IsNonNull.orNone(null).isUndefined());
		assertTrue(IsNonNull.orNone("hello").isDefined());
	}

}
