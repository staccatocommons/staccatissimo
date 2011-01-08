package net.sf.staccatocommons.check;

import static junit.framework.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.defs.SizeAware;

import org.junit.Test;

/**
 * Test for {@link Check}
 * 
 * @author flbulgarelli
 * 
 */
public class CheckUnitTest {

	private static final String VAR_NAME = "var";

	private Check<IllegalArgumentException> c = Ensure.that();

	@Test(expected = IllegalArgumentException.class)
	public void testCheckIsTrueStringBooleanStringObjectArray() {
		c.isTrue(VAR_NAME, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckIsTrueStringBoolean() {
		c.isTrue(VAR_NAME, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckMatchesStringStringString() {
		c.matches(VAR_NAME, "hello", ".ola.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckMatchesStringStringPattern() {
		c.matches(VAR_NAME, "hello", Pattern.compile(".ola."));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeLong() {
		c.isNotNegative(VAR_NAME, -5L);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeInt() {
		c.isNotNegative(VAR_NAME, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeDouble() {
		c.isNotNegative(VAR_NAME, -5.9);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeFloat() {
		c.isNotNegative(VAR_NAME, -5f);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeBigDecimal() {
		c.isNotNegative(VAR_NAME, BigDecimal.valueOf(-9.69));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeBigInteger() {
		c.isNotNegative(VAR_NAME, BigInteger.valueOf(-9));
	}

	@Test
	public void testisNotNegativeLong_Zero() {
		c.isNotNegative(VAR_NAME, 0L);
	}

	@Test
	public void testisNotNegativeInt_Zero() {
		c.isNotNegative(VAR_NAME, 0);
	}

	@Test
	public void testisNotNegativeDouble_Zero() {
		c.isNotNegative(VAR_NAME, 0.0);
	}

	@Test
	public void testisNotNegativeFloat_Zero() {
		c.isNotNegative(VAR_NAME, 0.0f);
	}

	@Test
	public void testisNotNegativeBigDecimal_Zero() {
		c.isNotNegative(VAR_NAME, BigDecimal.ZERO);
	}

	@Test
	public void testisNotNegativeBigInteger_Zero() {
		c.isNotNegative(VAR_NAME, BigInteger.ZERO);
	}

	/**
	 * Test method for {@link Check#isNotEmpty(String, Iterable)}
	 */
	@Test
	public void testisNotEmpty() {
		c
			.isNotEmpty(VAR_NAME, Arrays.asList("foo", "bar"))
			.isNotEmpty(VAR_NAME, Collections.singleton(6))
			.isNotEmpty(VAR_NAME, "hola")
			.isNotEmpty(VAR_NAME, new EmptyAware() {
				public boolean isEmpty() {
					return false;
				}
			});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotEmptyCharSequence_Fail() {
		c.isNotEmpty(VAR_NAME, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotEmptyCollection_Fail() {
		c.isNotEmpty(VAR_NAME, Collections.emptyList());
	}

	/**
	 * Test for {@link Check#isPositive(String, BigDecimal)} and others
	 */
	@Test
	public void testPositive() {
		c
			.isPositive(VAR_NAME, 9)
			.isPositive(VAR_NAME, 9L)
			.isPositive(VAR_NAME, 9f)
			.isPositive(VAR_NAME, 9.0)
			.isPositive(VAR_NAME, BigDecimal.valueOf(69.62))
			.isPositive(VAR_NAME, BigInteger.valueOf(1200));
	}

	/**
	 * Test for {@link Check#isPositive(String, int)}
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testPositive_Negative() {
		c.isPositive(VAR_NAME, -5);
	}

	/**
	 * Test for {@link Check#isPositive(String, int)}
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testPositive_Zero() {
		c.isPositive(VAR_NAME, 0);
	}

	/**
	 * Test for {@link Check#isPositive(String, int)} on failure
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPositive_IntegerFail() {
		c.isPositive(VAR_NAME, -50);
	}

	/**
	 * Test for {@link Check#isPositive(String, int)} on failure
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPositive_BigIntegerFail() {
		c.isPositive(VAR_NAME, BigInteger.valueOf(-10));
	}

	/**
	 * Test for the isSize family of methods
	 */
	@Test
	public void testSize() {
		c
			.isSize(VAR_NAME, Arrays.asList(9, 96), 2)
			.isSize(VAR_NAME, "Hello", 5)
			.isSize(VAR_NAME, new SizeAware() {
				public int size() {
					return 5;
				}

				public boolean isEmpty() {
					return false;
				}
			}, 5);

	}

	/**
	 * Test for the isEmpty family of methods
	 */
	@Test
	public void testEmpty() {
		c
			.isEmpty(VAR_NAME, Collections.emptyList())
			.isEmpty(VAR_NAME, Collections.<String> emptyList())
			.isEmpty(VAR_NAME, "")
			.isEmpty(VAR_NAME, new EmptyAware() {
				public boolean isEmpty() {
					return true;
				}
			});
	}

	@Test
	public void testIsInstanceOf() {
		c.isInstanceOf(VAR_NAME, 5, Number.class);
	}

	@Test
	public void testIsTrue() {
		c.isTrue(VAR_NAME, true);
	}

	@Test
	public void testMatchesStringStringPattern() {
		c.matches(VAR_NAME, "Hello", ".*ell.");
	}

	@Test
	public void testMatchesStringStringString() {
		c.matches(VAR_NAME, "Hello", Pattern.compile(".*ell."));
	}

	@Test
	public void testNonNegative() {
		c
			.isNotNegative(VAR_NAME, 9)
			.isNotNegative(VAR_NAME, 9L)
			.isNotNegative(VAR_NAME, 9f)
			.isNotNegative(VAR_NAME, 9.0)
			.isNotNegative(VAR_NAME, BigDecimal.valueOf(69.62))
			.isNotNegative(VAR_NAME, BigInteger.valueOf(1200));
	}

	@Test
	public void testNotNull() {
		c.isNotNull(VAR_NAME, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotNull_Fail() {
		c.isNotNull(VAR_NAME, null);
	}

	/**
	 * Test for {@link Check#isNull(String, Object)} on failure
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNull_Fail() {
		c.isNull(VAR_NAME, new Object());
	}

	/**
	 * Test for {@link Check#isNull(String, Object)}
	 */
	@Test
	public void testNull() {
		c.isNull(VAR_NAME, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFail() {
		c.fail(VAR_NAME, "Foo", "Should be palindromic");
	}

	/**
	 * Test for
	 * {@link Check#isBetween(String, Comparable, Comparable, Comparable)} method
	 * on failure
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsBetween_Fail() throws Exception {
		c.isBetween(VAR_NAME, 8, 10, 90);
	}

	/**
	 * Test for
	 * {@link Check#isBetween(String, Comparable, Comparable, Comparable)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsBetween() throws Exception {
		c.isBetween(VAR_NAME, "big", "bang", "!");
	}

	/**
	 * Test for {@link Check#isLessThan(String, Comparable, Comparable)},
	 * {@link Check#isLessThanOrEqualTo(String, Comparable, Comparable)},
	 * {@link Check#isGreaterThan(String, Comparable, Comparable)} and
	 * {@link Check#isGreaterThanOrEqualTo(String, Comparable, Comparable)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCompare() throws Exception {
		c
			.isGreaterThan(VAR_NAME, 90, 50)
			.isGreaterThanOrEqualTo(VAR_NAME, 90, 90)
			.isGreaterThanOrEqualTo(VAR_NAME, 90, 20)
			.isLessThan(VAR_NAME, "foo", "zbar")
			.isLessThanOrEqualTo(VAR_NAME, "baz", "baz")
			.isLessThanOrEqualTo(VAR_NAME, "foobar", "zbaz");

	}

	/**
	 * Test for {@link Check#isGreaterThan(String, Comparable, Comparable)}
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGtCompare_fail() throws Exception {
		c.isGreaterThan(VAR_NAME, 50, 50);
	}

	/**
	 * Test for {@link Check#isLessThan(String, Comparable, Comparable)}
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLtCompare_fail() throws Exception {
		c.isLessThan(VAR_NAME, 50, 50);
	}

	/**
	 * test for {@link Check#contains(String, ContainsAware, Object)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testContains() throws Exception {
		c.contains(VAR_NAME, new ContainsAware<Integer>() {
			public boolean contains(Integer element) {
				assertEquals((Integer) 5, element);
				return true;
			}
		}, 5);
	}

	/** Test for {@link Check#isIn(String, Object, ContainsAware)} */
	@Test
	public void testIn() throws Exception {
		c.isIn(VAR_NAME, 10, new ContainsAware<Integer>() {
			public boolean contains(Integer element) {
				assertEquals((Integer) 10, element);
				return true;
			}
		});
	}

	/** Test for {@link Check#isIn(String, Object, ContainsAware)} */
	@Test(expected = IllegalArgumentException.class)
	public void testIn_Fail() throws Exception {
		c.isIn(VAR_NAME, 10, new ContainsAware<Integer>() {
			public boolean contains(Integer element) {
				return false;
			}
		});
	}
}
