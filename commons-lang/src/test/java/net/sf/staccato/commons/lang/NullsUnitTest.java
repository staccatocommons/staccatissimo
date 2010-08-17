package net.sf.staccato.commons.lang;

import static org.junit.Assert.*;

import net.sf.staccato.commons.lang.provider.internal.Constant;

import org.junit.Test;

public class NullsUnitTest {

	@Test
	public void testNonNullOrElseTT() {
		assertEquals("Foo", IsNonNull.orElse(null, "Foo"));
		assertEquals("Bar", IsNonNull.orElse("Bar", "Foo"));
	}

	@Test
	public void testNonNullOrElseTProviderOfT() {
		assertEquals("Foo", IsNonNull.orElse(null, new Constant("Foo")));
		assertEquals("Bar", IsNonNull.orElse("Bar", new Constant("Foo")));
	}

	@Test
	public void testNonNullOrNone() {
		assertTrue(IsNonNull.orNone(null).isUndefined());
		assertTrue(IsNonNull.orNone("hello").isDefined());
	}

}
