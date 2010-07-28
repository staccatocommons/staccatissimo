package net.sf.staccato.commons.lang.tuple;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static net.sf.staccato.commons.lang.tuple.Tuple._;

import java.util.Calendar;

import org.junit.Test;

public class PairUnitTest extends TupleAbstractUnitTest {

	@Override
	public void testComponents() throws Exception {
		Pair<Integer, String> pair = _(9, "Hello");
		assertEquals((Integer) 9, pair.getFirst());
		assertEquals("Hello", pair.getSecond());
	}

	@Override
	public void testComparability() throws Exception {
		assertTrue(_(50, 2).compareTo(_(50, 2)) == 0);
		assertTrue(_(50, 2).compareTo(_(50, 9)) < 0);
		assertTrue(_(50, 2).compareTo(_(100, 1)) < 0);
		assertTrue(_(50, 2).compareTo(_(10, 1)) > 0);
		assertTrue(_(50, 2).compareTo(_(10, 10)) > 0);
	}

	@Override
	public void testEqualty() throws Exception {
		assertEquals(_(5, 90L), _(5, 90L));
		assertFalse(_("Hello", 5).equals(_("World", 5)));
	}

	@Test
	public void testSwap() throws Exception {
		Calendar calendar = Calendar.getInstance();
		assertEquals(_(90, calendar), _(calendar, 90).swap());
	}

	@Override
	public void testToString() throws Exception {
		assertEquals("(90,6)", _(90, 6).toString());
	}

	@Test
	public void testAsEntry_Get() throws Exception {
		assertEquals("Hello", _("Hello", 40).getKey());
		assertEquals("Hello", _(40, "Hello").getValue());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAsEntry_Set() throws Exception {
		_(40, 9).setValue(4);
	}

	@Override
	public void testToArray() throws Exception {
		Pair<Integer, Integer> pair = _(90, 6);
		Object[] a = pair.toArray();
		assertEquals(pair, _(a[0], a[1]));
	}

	@Override
	protected Tuple sampleTuple() {
		return _("", 5);
	}
}
