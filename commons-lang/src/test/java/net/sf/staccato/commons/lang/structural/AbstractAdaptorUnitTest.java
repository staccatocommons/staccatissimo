package net.sf.staccato.commons.lang.structural;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class AbstractAdaptorUnitTest {

	@Test
	public void testGetAdaptee() {
		Object o = new Object();
		AbstractAdaptor<Object> adaptor = new AbstractAdaptor<Object>(o) {
		};
		assertSame(o, adaptor.getAdaptee());
	}

}
