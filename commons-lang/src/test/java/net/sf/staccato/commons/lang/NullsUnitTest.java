package net.sf.staccato.commons.lang;

import static org.junit.Assert.assertEquals;
import net.sf.staccato.commons.lang.provider.internal.Constant;

import org.junit.Test;

public class NullsUnitTest {

	@Test
	public void testNonNullOrElseTT() {
		assertEquals("Foo", Nulls.coalesce(null, "Foo"));
		assertEquals("Bar", Nulls.coalesce("Bar", "Foo"));
	}

	@Test
	public void testNonNullOrElseTProviderOfT() {
		assertEquals("Foo", Nulls.coalesce(null, new Constant("Foo")));
		assertEquals("Bar", Nulls.coalesce("Bar", new Constant("Foo")));
	}

}
