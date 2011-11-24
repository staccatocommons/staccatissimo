/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.io.internal.lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Thunk;
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
  private Thunk<String> init;
  private Applicable<String, Integer> use;
  private Executable<String> dispose;

  /** setups the test */
  @Before
  public void setup() {
    init = mock(Thunk.class);
    use = mock(Applicable.class);
    dispose = mock(Executable.class);

    lifecycle = new Lifecycle<String, Integer>() {
      @Override
      protected String initialize() throws Exception {
        return init.value();
      }

      @Override
      protected Integer doWork(String resource) throws Exception {
        return use.apply(resource);
      }

      @Override
      protected void dispose(String resource) throws Exception {
        dispose.exec(resource);
      }
    };
  }

  /**
   * Test for {@link Lifecycle#throwing(Class)}
   * 
   * @throws Exception
   */
  @Test
  public void testExecuteThrowingOK() throws Exception {
    expectNormalLifecycle();
    assertEquals((Integer) 6, lifecycle.throwing(IOException.class));
  }

  /**
   * Test for {@link Lifecycle#throwing(Class, Class)}
   * 
   * @throws Exception
   */
  @Test
  public void testExecuteThrowing2OK() throws Exception {
    expectNormalLifecycle();
    assertEquals((Integer) 6, lifecycle.throwing(IOException.class, InterruptedException.class));
  }

  /** Test for {@link Lifecycle#call()} */
  @Test
  public void testCallOK() throws Exception {
    expectNormalLifecycle();
    assertEquals((Integer) 6, lifecycle.call());
  }

  /** Test for {@link Lifecycle#value()} */
  @Test
  public void testValueOK() throws Exception {
    expectNormalLifecycle();
    assertEquals((Integer) 6, lifecycle.value());
  }

  /** Test for {@link Lifecycle#value()} */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testValueFailOnInit() throws Exception {
    expectFailLifecycleOnInitialization();
    assertNull(lifecycle.value());
  }

  /** Test for {@link Lifecycle#call()} */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testCallFailOnInit() throws Exception {
    expectFailLifecycleOnInitialization();
    assertNull(lifecycle.call());
  }

  /**
   * Test method for {@link Lifecycle#throwing(Class)} when an exception of
   * class other than the given one occurs
   * 
   * @throws Exception
   */
  @Test(expected = IOException.class)
  public void testExecuteThrowingFailCheckedOnInit() throws Exception {
    lifecycle = new Lifecycle<String, Integer>() {
      @Override
      protected String initialize() throws Exception {
        throw new IOException();
      }
    };
    lifecycle.throwing(IOException.class);
  }

  /**
   * Test method for {@link Lifecycle#throwing(Class, Class)} when an exception
   * of the first one given
   * 
   * @throws Exception
   */
  @Test(expected = IOException.class)
  public void testExecuteThrowing2FailCheckedOnInitException1() throws Exception {
    lifecycle = new Lifecycle<String, Integer>() {
      @Override
      protected String initialize() throws Exception {
        throw new IOException();
      }
    };
    lifecycle.throwing(IOException.class, InterruptedException.class);
  }

  /**
   * Test method for {@link Lifecycle#throwing(Class, Class)} when an exception
   * of the second one given
   * 
   * @throws Exception
   */
  @Test(expected = InterruptedException.class)
  public void testExecuteThrowing2FailCheckedOnInitException2() throws Exception {
    lifecycle = new Lifecycle<String, Integer>() {
      @Override
      protected String initialize() throws Exception {
        throw new InterruptedException();
      }
    };
    lifecycle.throwing(IOException.class, InterruptedException.class);
  }

  /**
   * Test method for {@link Lifecycle#throwing(Class)} when an exception of the
   * given class occurs
   * 
   * @throws Exception
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testExecuteThrowingFailOnDispose() throws Exception {
    expectFailLifecycleOnDispose();
    lifecycle.throwing(IOException.class);
  }

  /**
   * Test method for {@link Lifecycle#throwing(Class, Class)} when an exception
   * of the given class occurs
   * 
   * @throws Exception
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testExecuteThrowing2FailOnDispose() throws Exception {
    expectFailLifecycleOnDispose();
    lifecycle.throwing(IOException.class, InterruptedException.class);
  }

  /***/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testCallFailOnDispose() throws Exception {
    expectFailLifecycleOnDispose();
    assertNull(lifecycle.call());
  }

  /***/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testValueFailOnDispose() throws Exception {
    expectFailLifecycleOnDispose();
    assertNull(lifecycle.value());
  }

  /***/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testExecuteFailOnWork() throws Exception {
    expectFailLifecycleOnWork();
    assertNull(lifecycle.value());
  }

  /***/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testCallFailOnWork() throws Exception {
    expectFailLifecycleOnWork();
    assertNull(lifecycle.call());
  }

  /***/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testValueFailOnWork() throws Exception {
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
