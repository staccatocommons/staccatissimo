package net.sf.staccato.commons.collections.iterable;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static net.sf.staccato.commons.lang.predicate.Predicates.greaterThan;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.predicate.Predicate;

import org.junit.Before;
import org.junit.Test;

public class IterablesUnitTest {

	private List<String> stringsList;

	/*
	 * 
	 * 
	 * @Test
	 * 
	 * @SuppressWarnings("unchecked") public void testAllSatisfy() {
	 * 
	 * assertTrue(Transversables.from(1, 2, 3, 4, 5,
	 * 9).allSatisfy(greaterThan(0))); assertFalse(Transversables.from(1, -9, 3,
	 * 4, 5, 9) // .allSatisfy(greaterThan(0)));
	 * assertFalse(Transversables.from(Collections.<Integer> emptyList()) //
	 * .allSatisfy(greaterThan(0)));
	 * 
	 * }
	 * 
	 * @Test public void testFilter() throws Exception { Transversable<String>
	 * result = Transversables.from(stringsList).filter( new Predicate<String>() {
	 * 
	 * @Override public boolean evaluate(String s) { return s.startsWith("H"); }
	 * }); assertEquals(asList("Hello"), result.asList()); }
	 */
	@Before
	public void setup() {
		stringsList = Arrays.asList("Hello", "World", "everybody");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testAllSatisfy() {

		assertTrue(Iterables.all(
			Arrays.asList(1, 2, 3, 4, 5, 9),
			greaterThan(0)));
		assertFalse(Iterables.all(
			Arrays.asList(1, -9, 3, 4, 5, 9),
			greaterThan(0)));
		assertTrue(Iterables.all((List) emptyList(), greaterThan(0)));

	}

	@Test
	public void testFilter() throws Exception {
		List<String> result = Iterables.filter(
			stringsList,
			new Predicate<String>() {
				@Override
				public boolean eval(String s) {
					return s.startsWith("H");
				}
			});
		assertEquals(asList("Hello"), result);
	}

	@Test
	public void testFind() throws Exception {
		Option<String> result = Iterables.findOrNone(
			stringsList,
			new Predicate<String>() {
				@Override
				public boolean eval(String s) {
					return s.startsWith("H");
				}
			});
		assertEquals("Hello", result.value());
	}

	@Test
	public void testAllSame() {
		Object o1 = new Object();
		Object o2 = new Object();

		assertTrue(Iterables.allSame(emptyList()));
		assertTrue(Iterables.allSame(Arrays.asList(o1)));
		assertTrue(Iterables.allSame(Arrays.asList(o1, o1, o1)));
		assertFalse(Iterables.allSame(Arrays.asList(o1, o1, o2)));
	}

	@Test
	public void testAllEqual() {
		Calendar c1 = new GregorianCalendar(1995, 10, 10);
		Calendar c2 = new GregorianCalendar(1996, 10, 10);
		Calendar c3 = (Calendar) c2.clone();

		assertTrue(Iterables.allEqual(emptyList()));
		assertTrue(Iterables.allEqual(Arrays.asList(c1)));
		assertTrue(Iterables.allEqual(Arrays.asList(c2, c3)));
		assertFalse(Iterables.allEqual(Arrays.asList(c2, c1)));
	}

}
