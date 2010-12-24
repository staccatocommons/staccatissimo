package net.sf.staccato.commons.lang;

import static net.sf.staccato.commons.lang.Option.none;
import static net.sf.staccato.commons.lang.Option.nullToNone;
import static net.sf.staccato.commons.lang.Option.some;
import static net.sf.staccato.commons.lang.Option.someNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import net.sf.staccato.commons.lang.Option.UndefinedOptionException;
import net.sf.staccato.commons.lang.provider.Providers;
import net.sf.staccato.commons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Test;

/**
 * Test for {@link Option}
 * 
 * @author flbulgarelli
 */
public class OptionUnitTest extends JUnit4MockObjectTestCase {

	@Test
	public void testValue_defined() {
		Integer i = 5;
		assertSame(i, some(i).value());
	}

	@Test(expected = UndefinedOptionException.class)
	public void testValue_undefined() {
		none().value();
	}

	@Test
	public void testIsDefined() {
		assertTrue(some(5).isDefined());
		assertTrue(some(null).isDefined());
		assertFalse(none().isDefined());
	}

	@Test
	public void testIsUndefined() {
		assertFalse(some(5).isUndefined());
		assertFalse(some(null).isUndefined());
		assertTrue(none().isUndefined());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(some(5).isEmpty());
		assertFalse(some(null).isEmpty());
		assertTrue(none().isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(1, some(5).size());
		assertEquals(0, none().size());
	}

	@Test
	public void testNullToNone() {
		assertNull(none().valueOrNull());
		assertNotNull(some("Hello").valueOrNull());
	}

	/**
	 * Test for {@link Option#valueOrElse(Object)} and
	 * {@link Option#valueOrElse(Provider)}
	 * 
	 * @throws Exception
	 */
	public void testValueOrElse() throws Exception {
		assertEquals(4, (int) Option.some(4).valueOrElse(8));
		assertEquals(4, (int) Option.some(4).valueOrElse(Providers.constant(9)));
		assertEquals(9, (int) Option.<Integer> none().valueOrElse(Providers.constant(9)));
		assertEquals(9, (int) Option.<Integer> none().valueOrElse(Providers.constant(9)));
	}

	@Test
	public void testNoneToNull() {
		assertEquals(none(), nullToNone(null));
		assertEquals(some("String"), nullToNone("String"));
	}

	@Test
	public void testEqualty() throws Exception {
		assertEquals(some(5), some(5));
		assertSame(none(), none());
		assertSame(some(null), someNull());
		assertEquals(new Some<Integer>(5), new Some<Integer>(5));
		assertEquals(new Some<Integer>(null), new Some<Integer>(null));
	}

	/**
	 * Test for {@link Option#ifDefined(Executable)}
	 * 
	 * @throws Exception
	 */
	public void testIfDefined() throws Exception {
		final Executable<String> block = mock(Executable.class);
		checking(new Expectations() {
			{
				one(block).exec("foo");
			}
		});
		Option.some("foo").ifDefined(block);
		Option.<String> none().ifDefined(block);
	}
}
