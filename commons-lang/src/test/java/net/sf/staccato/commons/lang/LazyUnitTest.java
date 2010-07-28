package net.sf.staccato.commons.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LazyUnitTest {

	private int var;

	@Test
	public void testValue() {

		var = 0;

		Lazy<Integer> lazyInteger = new Lazy<Integer>() {
			@Override
			protected Integer init() {
				return var;
			}
		};

		var = 1;

		assertEquals((Integer) 1, lazyInteger.value());

		var = 2;

		assertEquals((Integer) 1, lazyInteger.value());
	}

}
