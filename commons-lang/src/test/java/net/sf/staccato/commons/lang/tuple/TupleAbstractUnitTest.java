package net.sf.staccato.commons.lang.tuple;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public abstract class TupleAbstractUnitTest {

	@Test
	public abstract void testEqualty() throws Exception;

	@Test
	public abstract void testComparability() throws Exception;

	@Test
	public abstract void testComponents() throws Exception;

	@Test
	public abstract void testToString() throws Exception;

	@Test
	public abstract void testToArray() throws Exception;

	@Test
	public void testToList() throws Exception {
		Tuple sampleTuple = sampleTuple();
		assertEquals(sampleTuple.toList(), Arrays.asList(sampleTuple.toArray()));
	}

	protected abstract Tuple sampleTuple();

}
