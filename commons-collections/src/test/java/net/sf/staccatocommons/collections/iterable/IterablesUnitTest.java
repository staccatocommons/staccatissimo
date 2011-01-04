package net.sf.staccatocommons.collections.iterable;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static net.sf.staccatocommons.lang.predicate.Predicates.greaterThan;
import static net.sf.staccatocommons.lang.tuple.Tuple._;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.lang.tuple.Pair;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link Iterables}
 * 
 * @author flbulgarelli
 * 
 */
public class IterablesUnitTest {

	private List<Integer> integersList = Arrays.asList(4, 5, 6);
	private List<String> stringsList;

	/**
	 * Setups the test
	 */
	@Before
	public void setup() {
		stringsList = Arrays.asList("Hello", "World", "everybody");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testAllSatisfy() {

		assertTrue(Iterables.all(Arrays.asList(1, 2, 3, 4, 5, 9), greaterThan(0)));
		assertFalse(Iterables.all(Arrays.asList(1, -9, 3, 4, 5, 9), greaterThan(0)));
		assertTrue(Iterables.all((List) emptyList(), greaterThan(0)));

	}

	/**
	 * Test for
	 * {@link Iterables#filter(Iterable, net.sf.staccatocommons.lang.Evaluable)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFilter() throws Exception {
		List<String> result = Iterables.filter(stringsList, new Predicate<String>() {
			@Override
			public boolean eval(String s) {
				return s.startsWith("H");
			}
		});
		assertEquals(asList("Hello"), result);
	}

	/**
	 * Test for
	 * {@link Iterables#map(Collection, net.sf.staccatocommons.lang.Applicable)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMap() throws Exception {
		assertEquals(
			Arrays.asList(false, true, false),
			Iterables.map(integersList, Predicates.equal(5)));
		assertEquals(
			Arrays.asList(false, true, false),
			Iterables.map((Iterable<Integer>) integersList, Predicates.equal(5)));
	}

	/**
	 * Test method for {@link Iterables#flatMap(Iterable, Applicable)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFlatMap() throws Exception {
		assertEquals(
			Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6),
			Iterables.flatMap(Sequence.fromTo(4, 6), new Function<Integer, Iterable<Integer>>() {
				public Iterable<Integer> apply(Integer arg) {
					return Sequence.fromTo(1, arg);
				}
			}));
	}

	@Test
	public void testFind() throws Exception {
		Option<String> result = Iterables.findOrNone(stringsList, new Predicate<String>() {
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

	/**
	 * Test for {@link Iterables#indexOf(Iterable, Object)}
	 */
	@Test
	public void testIndexOf() {
		List<Object> list = Arrays.<Object> asList("hola", 80, 20, 'a');
		assertEquals(3, Iterables.indexOf(list, 'a'));
		assertEquals(0, Iterables.indexOf(list, "hola"));
		assertEquals(-1, Iterables.indexOf(list, 13));
	}

	/**
	 * Test for {@link Iterables#isBefore(Iterable, Object, Object)}
	 */
	@Test
	public void testIsBefore() {
		List<Object> list = Arrays.<Object> asList("hola", 80, 20, 'a');
		assertTrue(Iterables.isBefore(list, 80, 20));
		assertTrue(Iterables.isBefore(list, "hola", 'a'));
		assertFalse(Iterables.isBefore(list, 20, 20));
		assertFalse(Iterables.isBefore(list, 80, "hola"));
		assertFalse(Iterables.isBefore(list, 20, 40));
		assertFalse(Iterables.isBefore(list, 90, 10));
		assertFalse(Iterables.isBefore(list, 90, 20));
	}

	/**
	 * Test method for {@link Iterables#toList(java.util.Collection)}
	 */
	@Test
	public void testToList() throws Exception {
		assertSame(Iterables.toList(integersList), integersList);
		assertTrue(Iterables.toList(new HashSet<Integer>(integersList)).containsAll(integersList));
	}

	/**
	 * Test method for {@link Iterables#toSet(java.util.Collection)}
	 */
	@Test
	public void testToSet() throws Exception {
		HashSet<Integer> set = new HashSet<Integer>();

		assertTrue(Iterables.toSet(integersList).containsAll(integersList));
		assertSame(Iterables.toSet(set), set);
	}

	/**
	 * Test for {@link Iterables#isNullOrEmpty(Collection)} and
	 * {@link Iterables#isNullOrEmpty(Iterable)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsNullOrEmpty() throws Exception {
		assertTrue(Iterables.isNullOrEmpty((Collection) null));
		assertTrue(Iterables.isNullOrEmpty((Iterable) null));
		assertTrue(Iterables.isNullOrEmpty(Collections.emptyList()));
		assertTrue(Iterables.isNullOrEmpty((Iterable) Collections.emptyList()));
		assertFalse(Iterables.isNullOrEmpty(Collections.singleton(5)));
		assertFalse(Iterables.isNullOrEmpty((Iterable) Collections.singleton(5)));
	}

	/**
	 * Test for {@link Iterables#single(Collection)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSingle() throws Exception {
		assertEquals(90, (int) Iterables.single(Collections.singleton(90)));
	}

	/**
	 * Test for {@link Iterables#single(Collection)} on failure
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSingle_Failrue() throws Exception {
		Iterables.single(integersList);
	}

	/**
	 * Test method for
	 * {@link Iterables#partition(Iterable, net.sf.staccatocommons.lang.Evaluable)}
	 */
	@Test
	public void testPartition() throws Exception {
		Pair<List<Integer>, List<Integer>> partition = Iterables.partition(
			Sequence.fromTo(10, 20),
			new Predicate<Integer>() {
				public boolean eval(Integer argument) {
					return argument % 2 == 0;
				}
			});

		assertEquals(Iterables.toList(Sequence.fromToBy(10, 20, 2)), partition._1());
		assertEquals(Iterables.toList(Sequence.fromToBy(11, 20, 2)), partition._2());
	}

	/**
	 * Test method for {@link Iterables#take(Iterable, int)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTake() throws Exception {
		assertEquals(Arrays.asList(11, 12, 13, 14), Iterables.take(Sequence.fromBy(11, 1), 4));
	}

	@Test
	public void testZip() throws Exception {
		assertEquals(
			Arrays.asList(_(10, 8), _(12, 7), _(14, 6)),
			Iterables.zip(Arrays.asList(10, 12, 14, 23), Arrays.asList(8, 7, 6)));

	}
}
