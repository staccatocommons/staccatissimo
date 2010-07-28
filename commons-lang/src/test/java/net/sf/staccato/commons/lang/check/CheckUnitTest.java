package net.sf.staccato.commons.lang.check;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

import org.junit.Test;

public class CheckUnitTest {

	private static final String VAR_NAME = "var";

	Check<IllegalArgumentException> c = Ensure.getInstance();

	@Test(expected = IllegalArgumentException.class)
	public void testCheckIsTrueStringBooleanStringObjectArray() {
		c.checkIsTrue(VAR_NAME, false, "should be true");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckIsTrueStringBoolean() {
		c.checkIsTrue(VAR_NAME, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckMatchesStringStringString() {
		c.checkMatches(VAR_NAME, "hello", ".ola.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckMatchesStringStringPattern() {
		c.checkMatches(VAR_NAME, "hello", Pattern.compile(".ola."));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNegativeStringLong() {
		c.checkNonNegative(VAR_NAME, -5L);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNegativeStringInt() {
		c.checkNonNegative(VAR_NAME, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNegativeStringDouble() {
		c.checkNonNegative(VAR_NAME, -5.9);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNegativeStringFloat() {
		c.checkNonNegative(VAR_NAME, -5f);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNegativeStringBigDecimal() {
		c.checkNonNegative(VAR_NAME, BigDecimal.valueOf(-9.69));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNonNegativeStringBigInteger() {
		c.checkNonNegative(VAR_NAME, BigInteger.valueOf(-9));
	}

	@Test
	public void testCheckNonNegativeStringLong_Zero() {
		c.checkNonNegative(VAR_NAME, 0L);
	}

	@Test
	public void testCheckNonNegativeStringInt_Zero() {
		c.checkNonNegative(VAR_NAME, 0);
	}

	@Test
	public void testCheckNonNegativeStringDouble_Zero() {
		c.checkNonNegative(VAR_NAME, 0.0);
	}

	@Test
	public void testCheckNonNegativeStringFloat_Zero() {
		c.checkNonNegative(VAR_NAME, 0.0f);
	}

	@Test
	public void testCheckNonNegativeStringBigDecimal_Zero() {
		c.checkNonNegative(VAR_NAME, BigDecimal.ZERO);
	}

	@Test
	public void testCheckNonNegativeStringBigInteger_Zero() {
		c.checkNonNegative(VAR_NAME, BigInteger.ZERO);
	}

	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringEmptyAware() {
	// fail("Not yet implemented");
	// }

	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringCollectionOfQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringIterableOfQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringMapOfQQ() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringCharSequence() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringObjectArray() {
	// fail("Not yet implemented");
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void testCheckNotEmptyStringIntArray() {
	// fail("Not yet implemented");
	// }
	//
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyStringLongArray() {
		c.checkNotEmpty(VAR_NAME, new long[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyStringByteArray() {
		c.checkNotEmpty(VAR_NAME, new byte[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyStringDoubleArray() {
		c.checkNotEmpty(VAR_NAME, new double[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyStringFloatArray() {
		c.checkNotEmpty(VAR_NAME, new float[0]);
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
	// public void testCheckNonNull() {
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

}
