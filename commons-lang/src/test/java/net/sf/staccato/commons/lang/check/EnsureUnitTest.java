package net.sf.staccato.commons.lang.check;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import net.sf.staccato.commons.lang.range.Range;

import org.junit.Test;

public class EnsureUnitTest {

	private static final String VAR_NAME = "myVar";

	@Test
	public void testEmpty() {
		Ensure.empty(VAR_NAME, Collections.emptyList());
		Ensure.empty(VAR_NAME, Collections.emptyMap());
		Ensure.empty(VAR_NAME, Collections.<String> emptyList());
	}

	@Test
	public void testIsInstanceOf() {
		Ensure.isInstanceOf(VAR_NAME, 5, Number.class);
	}

	@Test
	public void testIsTrue() {
		Ensure.isTrue(VAR_NAME, true);
	}

	@Test
	public void testMatchesStringStringPattern() {
		Ensure.matches(VAR_NAME, "Hello", ".*ell.");
	}

	@Test
	public void testMatchesStringStringString() {
		Ensure.matches(VAR_NAME, "Hello", Pattern.compile(".*ell."));
	}

	@Test
	public void testNonNegative() {
		Ensure.nonNegative(VAR_NAME, 9);
		Ensure.nonNegative(VAR_NAME, 9L);
		Ensure.nonNegative(VAR_NAME, 9f);
		Ensure.nonNegative(VAR_NAME, 9.0);
		Ensure.nonNegative(VAR_NAME, BigDecimal.valueOf(69.62));
		Ensure.nonNegative(VAR_NAME, BigInteger.valueOf(1200));
	}

	@Test
	public void testNotEmptyCollection() {
		Ensure.notEmpty(VAR_NAME, Collections.singleton(6));
		Ensure.notEmpty(VAR_NAME, new byte[1]);
		Ensure.notEmpty(VAR_NAME, "A word");
		Ensure.notEmpty(VAR_NAME, new double[2]);
		Ensure.notEmpty(VAR_NAME, new float[2]);
		Ensure.notEmpty(VAR_NAME, new int[6]);
		Ensure.notEmpty(VAR_NAME, new long[52]);
		Ensure.notEmpty(VAR_NAME, new Object[2]);
		Ensure.notEmpty(VAR_NAME, Range.from(5, 6));
	}

	@Test
	public void testNotEmptyStringMapOfQQ() {
		Ensure.notEmpty(VAR_NAME, Collections.singletonMap("Hello", "World"));
	}

	@Test
	public void testNotNull() {
		Ensure.nonNull(VAR_NAME, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFail() {
		Ensure.fail(VAR_NAME, "Foo", "Should be palindromic");
	}

	@Test
	public void testPositive() {
		Ensure.positive(VAR_NAME, 9);
		Ensure.positive(VAR_NAME, 9L);
		Ensure.positive(VAR_NAME, 9f);
		Ensure.positive(VAR_NAME, 9.0);
		Ensure.positive(VAR_NAME, BigDecimal.valueOf(69.62));
		Ensure.positive(VAR_NAME, BigInteger.valueOf(1200));
	}

	@Test
	public void testSize() {
		Ensure.size(VAR_NAME, Arrays.asList(9, 96), 2);
		Ensure.size(VAR_NAME, "Hello", 5);
		Ensure.size(VAR_NAME, new double[] { 5.5, 9 }, 2);
		Ensure.size(VAR_NAME, new float[] { 63.0f }, 1);
		Ensure.size(VAR_NAME, new int[] { 9, 93, 23, 6, 0 }, 5);
		Ensure.size(VAR_NAME, new long[] { 9, 93, 23, 6, 0 }, 5);
		Ensure.size(VAR_NAME, new Object[] { 9, 93, 23, 6, 0 }, 5);

	}
}
