package net.sf.staccato.commons.lang;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ConstantUnitTest {

	@Test
	public void testValue() {
		String value = "String";
		Provider<String> constant = new Constant(value);
		assertSame(value, constant.value());
	}

}
