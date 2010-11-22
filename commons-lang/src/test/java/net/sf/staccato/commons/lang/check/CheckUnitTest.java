package net.sf.staccato.commons.lang.check;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import org.junit.Test;

public class CheckUnitTest {

	private static final String VAR_NAME = "var";

	Check<IllegalArgumentException> c = Ensure.that();

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
	public void testisNotNegativeStringLong() {
		c.isNotNegative(VAR_NAME, -5L);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeStringInt() {
		c.isNotNegative(VAR_NAME, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeStringDouble() {
		c.isNotNegative(VAR_NAME, -5.9);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeStringFloat() {
		c.isNotNegative(VAR_NAME, -5f);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeStringBigDecimal() {
		c.isNotNegative(VAR_NAME, BigDecimal.valueOf(-9.69));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotNegativeStringBigInteger() {
		c.isNotNegative(VAR_NAME, BigInteger.valueOf(-9));
	}

	@Test
	public void testisNotNegativeStringLong_Zero() {
		c.isNotNegative(VAR_NAME, 0L);
	}

	@Test
	public void testisNotNegativeStringInt_Zero() {
		c.isNotNegative(VAR_NAME, 0);
	}

	@Test
	public void testisNotNegativeStringDouble_Zero() {
		c.isNotNegative(VAR_NAME, 0.0);
	}

	@Test
	public void testisNotNegativeStringFloat_Zero() {
		c.isNotNegative(VAR_NAME, 0.0f);
	}

	@Test
	public void testisNotNegativeStringBigDecimal_Zero() {
		c.isNotNegative(VAR_NAME, BigDecimal.ZERO);
	}

	@Test
	public void testisNotNegativeStringBigInteger_Zero() {
		c.isNotNegative(VAR_NAME, BigInteger.ZERO);
	}

	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringEmptyAware() {
	// fail("Not yet implemented");
	// }

	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringCollectionOfQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringIterableOfQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringMapOfQQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringCharSequence() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringObjectArray() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotEmptyStringIntArray() {
	// fail("Not yet implemented");
	// }
	//
	@Test(expected = IllegalArgumentException.class)
	public void testisNotEmptyStringLongArray() {
		c.isNotEmpty(VAR_NAME, new long[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotEmptyStringByteArray() {
		c.isNotEmpty(VAR_NAME, new byte[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotEmptyStringDoubleArray() {
		c.isNotEmpty(VAR_NAME, new double[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testisNotEmptyStringFloatArray() {
		c.isNotEmpty(VAR_NAME, new float[0]);
	}

	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckEmptyStringCollectionOfQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckEmptyStringIterableOfQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckEmptyStringMapOfQQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckEmptyStringEmptyAware() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testisNotNull() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckPositiveStringLong() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckPositiveStringInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckPositiveStringDouble() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckPositiveStringFloat() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckPositiveStringBigDecimal() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckPositiveStringBigInteger() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckIsInstanceOf() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringCollectionOfQInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringCharSequenceInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringObjectArrayInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringIntArrayInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringLongArrayInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringByteArrayInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringDoubleArrayInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringFloatArrayInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckSizeStringSizeAwareInt() {
	// fail("Not yet implemented");
	// }

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

	@Test
	public void testSize() {
		c
			.isSize(VAR_NAME, Arrays.asList(9, 96), 2)
			.isSize(VAR_NAME, "Hello", 5)
			.isSize(VAR_NAME, new double[] { 5.5, 9 }, 2)
			.isSize(VAR_NAME, new float[] { 63.0f }, 1)
			.isSize(VAR_NAME, new int[] { 9, 93, 23, 6, 0 }, 5)
			.isSize(VAR_NAME, new long[] { 9, 93, 23, 6, 0 }, 5)
			.isSize(VAR_NAME, new Object[] { 9, 93, 23, 6, 0 }, 5);

	}

	@Test
	public void testEmpty() {
		c
			.isEmpty(VAR_NAME, Collections.emptyList())
			.isEmpty(VAR_NAME, Collections.emptyMap())
			.isEmpty(VAR_NAME, Collections.<String> emptyList());
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

	// @Test
	// public void tes tNotEmptyCollection() {
	// c
	// .isNotEmpty(VAR_NAME, Collections.singleton(6))
	// .isNotEmpty(VAR_NAME, new byte[1])
	// .isNotEmpty(VAR_NAME, "A word")
	// .isNotEmpty(VAR_NAME, new double[2])
	// .isNotEmpty(VAR_NAME, new float[2])
	// .isNotEmpty(VAR_NAME, new int[6])
	// .isNotEmpty(VAR_NAME, new long[52])
	// .isNotEmpty(VAR_NAME, new Object[2])
	// .isNotEmpty(VAR_NAME, Range.from(5, 6));
	// }

	@Test
	public void testNotEmptyStringMapOfQQ() {
		c.isNotEmpty(VAR_NAME, Collections.singletonMap("Hello", "World"));
	}

	@Test
	public void testNotNull() {
		c.isNotNull(VAR_NAME, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFail() {
		c.fail(VAR_NAME, "Foo", "Should be palindromic");
	}
}
