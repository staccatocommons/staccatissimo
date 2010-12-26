package net.sf.staccato.commons.lang.lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import net.sf.staccato.commons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link LifecycleManager}
 * 
 * @author flbulgarelli
 */
public class LifecycleManagertUnitTest extends JUnit4MockObjectTestCase {

	LifecycleManager<Integer, Integer> manager;

	Lifecycle<Integer, Integer> lifecycle;

	@Before
	public void setup() {
		lifecycle = mock(Lifecycle.class);
		manager = LifecycleManager.from(lifecycle);
	}

	@Test
	public void testExecute_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, manager.execute());
	}

	@Test
	public void testRun_OK() throws Exception {
		expectNormalLifecycle();
		manager.run();
	}

	@Test
	public void testCall_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, manager.call());
	}

	@Test
	public void testValue_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, manager.value());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecute_Fail_OnInit() throws Exception {
		expectFailLifecycleOnInitialization();
		assertNull(manager.execute());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRun_Fail_Init() throws Exception {
		expectFailLifecycleOnInitialization();
		manager.run();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCall_Fail_OnInit() throws Exception {
		expectFailLifecycleOnInitialization();
		assertNull(manager.call());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValue_Fail_Init() throws Exception {
		expectFailLifecycleOnInitialization();
		assertNull(manager.value());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecute_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		assertNull(manager.execute());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRun_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		manager.run();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCall_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		assertNull(manager.call());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValue_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		assertNull(manager.value());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecute_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		assertNull(manager.execute());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRun_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		manager.run();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCall_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		assertNull(manager.call());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValue_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		assertNull(manager.value());
	}

	private void expectFailLifecycleOnInitialization() throws Exception {
		checking(new Expectations() {
			{
				one(lifecycle).initialize();
				will(throwException(new IndexOutOfBoundsException()));
			}
		});
	}

	private void expectFailLifecycleOnWork() throws Exception {
		checking(new Expectations() {
			{
				Integer managed = 0;
				one(lifecycle).initialize();
				will(returnValue(managed));
				one(lifecycle).doWork(managed);
				will(throwException(new IndexOutOfBoundsException()));

				one(lifecycle).dispose(managed);
			}
		});
	}

	private void expectFailLifecycleOnDispose() throws Exception {
		checking(new Expectations() {
			{
				Integer managed = 0;
				one(lifecycle).initialize();
				will(returnValue(managed));
				one(lifecycle).doWork(managed);
				will(returnValue(6));
				one(lifecycle).dispose(managed);
				will(throwException(new IndexOutOfBoundsException()));
			}
		});
	}

	private void expectNormalLifecycle() throws Exception {
		checking(new Expectations() {
			{
				Integer managed = 0;
				one(lifecycle).initialize();
				will(returnValue(managed));
				one(lifecycle).doWork(managed);
				will(returnValue(6));
				one(lifecycle).dispose(managed);
			}
		});
	}
}
