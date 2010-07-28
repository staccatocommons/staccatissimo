package net.sf.staccato.commons.lang.protocollection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.sf.staccato.commons.lang.collection.StringList;

import org.junit.Test;

public class StringListUnitTest {

	@Test
	public void testSize() {
		assertEquals(5, new StringList("Hello").size());
		assertEquals(4, new StringList("かたかな").size());
		assertEquals(3, new StringList("日本語").size());
	}

	@Test
	public void testGetInt() {
		assertEquals('e', (char) new StringList("Hello").get(1));
	}

	@Test
	public void testContainsObject() {
		assertTrue(new StringList("Buenas!").contains('!'));
	}

}
