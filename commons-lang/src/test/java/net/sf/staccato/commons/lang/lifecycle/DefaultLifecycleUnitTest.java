package net.sf.staccato.commons.lang.lifecycle;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DefaultLifecycleUnitTest {

	DefaultLifecycleMock lifecycle = new DefaultLifecycleMock();

	@Test
	public void testDoWork() throws Exception {
		assertNull(lifecycle.doWork(new Object()));
		assertTrue(lifecycle.getProduceResultInvoked());
		assertTrue(lifecycle.getPerformTaskInvoked());
	}

	@Test
	public void testProduceResult() throws Exception {
		assertNull(lifecycle.produceResult(new Object()));
	}

	@Test
	public void testDispose() throws Exception {
		lifecycle.dispose(new Object());
	}

	@Test
	public void testPerformTask() throws Exception {
		lifecycle.performTask(new Object());
	}

	@Test
	public void testCreateManager() {
		assertNotNull(lifecycle.createManager());
		assertSame(lifecycle, lifecycle.createManager().getManagedLifecycle());
	}

	private final class DefaultLifecycleMock extends
		DefaultLifecycle<Object, Void> {
		private boolean produceResultInvoked;
		private boolean performTaskInvoked;

		@Override
		public Object initialize() throws Exception {
			return null;
		}

		@Override
		public void performTask(Object managed) throws Exception {
			performTaskInvoked = true;
			super.performTask(managed);
		}

		@Override
		public Void produceResult(Object managed) throws Exception {
			produceResultInvoked = true;
			return super.produceResult(managed);
		}

		public boolean getProduceResultInvoked() {
			return produceResultInvoked;
		}

		public boolean getPerformTaskInvoked() {
			return performTaskInvoked;
		}
	}
}
