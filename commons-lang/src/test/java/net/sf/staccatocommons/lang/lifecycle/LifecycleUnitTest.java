/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link Lifecycle}
 * 
 * @author flbulgarelli
 */
public class LifecycleUnitTest extends JUnit4MockObjectTestCase {

	private Lifecycle<String, Integer> lifecycle;
	private Provider<String> init;
	private Applicable<String, Integer> use;
	private Executable<String> dispose;

	/** setups the test */
	@Before
	public void setup() {
		init = mock(Provider.class);
		use = mock(Applicable.class);
		dispose = mock(Executable.class);

		lifecycle = new Lifecycle<String, Integer>() {
			protected String initialize() throws Exception {
				return init.value();
			}

			protected Integer doWork(String resource) throws Exception {
				return use.apply(resource);
			}

			protected void dispose(String resource) throws Exception {
				dispose.exec(resource);
			}
		};
	}

	@Test
	public void testExecute_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, lifecycle.execute());
	}

	/**
	 * Test for {@link Lifecycle#executeThrowing(Class)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExecuteThrowing_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, lifecycle.executeThrowing(IOException.class));
	}

	/**
	 * Test for {@link Lifecycle#executeThrowing(Class, Class)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExecuteThrowing2_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals(
			(Integer) 6,
			lifecycle.executeThrowing(IOException.class, InterruptedException.class));
	}

	@Test
	public void testRun_OK() throws Exception {
		expectNormalLifecycle();
		lifecycle.run();
	}

	@Test
	public void testCall_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, lifecycle.call());
	}

	@Test
	public void testValue_OK() throws Exception {
		expectNormalLifecycle();
		assertEquals((Integer) 6, lifecycle.value());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecute_Fail_OnInit() throws Exception {
		expectFailLifecycleOnInitialization();
		assertNull(lifecycle.execute());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRun_Fail_Init() throws Exception {
		expectFailLifecycleOnInitialization();
		lifecycle.run();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCall_Fail_OnInit() throws Exception {
		expectFailLifecycleOnInitialization();
		assertNull(lifecycle.call());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValue_Fail_Init() throws Exception {
		expectFailLifecycleOnInitialization();
		assertNull(lifecycle.value());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecute_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		lifecycle.execute();
	}

	/**
	 * Test method for {@link Lifecycle#executeThrowing(Class)} when an exception
	 * of class other than the given one occurs
	 * 
	 * @throws Exception
	 */
	@Test(expected = IOException.class)
	public void testExecuteThrowing_FailChecked_OnInit() throws Exception {
		lifecycle = new Lifecycle<String, Integer>() {
			protected String initialize() throws Exception {
				throw new IOException();
			}
		};
		lifecycle.executeThrowing(IOException.class);
	}

	/**
	 * Test method for {@link Lifecycle#executeThrowing(Class, Class)} when an
	 * exception of the first one given
	 * 
	 * @throws Exception
	 */
	@Test(expected = IOException.class)
	public void testExecuteThrowing2_FailChecked_OnInit_Exception1() throws Exception {
		lifecycle = new Lifecycle<String, Integer>() {
			protected String initialize() throws Exception {
				throw new IOException();
			}
		};
		lifecycle.executeThrowing(IOException.class, InterruptedException.class);
	}

	/**
	 * Test method for {@link Lifecycle#executeThrowing(Class, Class)} when an
	 * exception of the second one given
	 * 
	 * @throws Exception
	 */
	@Test(expected = InterruptedException.class)
	public void testExecuteThrowing2_FailChecked_OnInit_Exception2() throws Exception {
		lifecycle = new Lifecycle<String, Integer>() {
			protected String initialize() throws Exception {
				throw new InterruptedException();
			}
		};
		lifecycle.executeThrowing(IOException.class, InterruptedException.class);
	}

	/**
	 * Test method for {@link Lifecycle#executeThrowing(Class)} when an exception
	 * of the given class occurs
	 * 
	 * @throws Exception
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecuteThrowing_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		lifecycle.executeThrowing(IOException.class);
	}

	/**
	 * Test method for {@link Lifecycle#executeThrowing(Class, Class)} when an
	 * exception of the given class occurs
	 * 
	 * @throws Exception
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecuteThrowing2_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		lifecycle.executeThrowing(IOException.class, InterruptedException.class);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRun_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		lifecycle.run();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCall_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		assertNull(lifecycle.call());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValue_Fail_OnDispose() throws Exception {
		expectFailLifecycleOnDispose();
		assertNull(lifecycle.value());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExecute_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		assertNull(lifecycle.execute());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRun_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		lifecycle.run();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCall_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		assertNull(lifecycle.call());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValue_Fail_OnWork() throws Exception {
		expectFailLifecycleOnWork();
		assertNull(lifecycle.value());
	}

	private void expectFailLifecycleOnInitialization() throws Exception {
		checking(new Expectations() {
			{
				one(init).value();
				will(throwException(new IndexOutOfBoundsException()));
			}
		});
	}

	private void expectFailLifecycleOnWork() throws Exception {
		checking(new Expectations() {
			{
				String managed = "5";
				one(init).value();
				will(returnValue(managed));
				one(use).apply(managed);
				will(returnValue(6));
				will(throwException(new IndexOutOfBoundsException()));
				one(dispose).exec(managed);
			}
		});
	}

	private void expectFailLifecycleOnDispose() throws Exception {
		checking(new Expectations() {
			{
				String managed = "5";
				one(init).value();
				will(returnValue(managed));
				one(use).apply(managed);
				will(returnValue(6));
				one(dispose).exec(managed);
				will(throwException(new IndexOutOfBoundsException()));
			}
		});
	}

	private void expectNormalLifecycle() throws Exception {
		checking(new Expectations() {
			{
				String managed = "5";
				one(init).value();
				will(returnValue(managed));
				one(use).apply(managed);
				will(returnValue(6));
				one(dispose).exec(managed);
			}
		});
	}
}
