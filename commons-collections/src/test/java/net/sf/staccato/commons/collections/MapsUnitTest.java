package net.sf.staccato.commons.collections;

import static net.sf.staccatocommons.testing.junit.Assert.assertIn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;

import net.sf.staccato.commons.lang.MapBuilder;
import net.sf.staccato.commons.lang.Option;

import org.junit.Test;

/**
 * Test for {@link Maps}
 * 
 * @author flbulgarelli
 * 
 */
public class MapsUnitTest {

	private Map<String, Integer> map = MapBuilder
		.hashMapWith("Foo", 50)
		.with("Bar", 90)
		.with("Foobar", 120)
		.build();

	/**
	 * Test method for {@link Maps#anyKey(Map)}
	 */
	@Test
	public void testAnyKey() {
		assertIn(Maps.anyKey(map).value(), "Foo", "Bar", "Foobar");
		assertTrue(Maps.anyKey(Collections.emptyMap()).isUndefined());
	}

	/**
	 * Test method for {@link Maps#anyKeyOrNull(Map)}
	 */
	@Test
	public void testAnyKeyOrNull() {
		assertIn(Maps.anyKeyOrNull(map), "Foo", "Bar", "Foobar");
		assertNull(Maps.anyKeyOrNull(Collections.emptyMap()));
	}

	/**
	 * Test method for {@link Maps#anyKeyValue(Map)}
	 */
	@Test
	public void testAnyValue() {
		assertIn(Maps.anyValue(map).value(), 50, 90, 120);
		assertNull(Maps.anyValueOrNull(Collections.emptyMap()));
	}

	/**
	 * Test method for {@link Maps#get(Map, Object)}
	 */
	@Test
	public void testGet() throws Exception {
		assertEquals(50, (int) Maps.get(map, "Foo").value());
		assertTrue(Maps.get(map, "FOO").isUndefined());
		assertEquals(Option.someNull(), Maps.get(MapBuilder.hashMapWith(10, null).build(), 10));

	}
}
