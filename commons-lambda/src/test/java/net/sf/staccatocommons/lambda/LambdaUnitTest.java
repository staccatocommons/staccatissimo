/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lambda;

import static net.sf.staccatocommons.lambda.Lambda.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.defs.SizeAware;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.Option;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class LambdaUnitTest {

  /**
   * Test method for {@link net.sf.staccatocommons.lambda.Lambda#factory()}.
   */
  @Test
  public void testFactoriesIndependent() {
    LambdaFactory l1 = Lambda.factory();
    EmptyAware stub1 = l1.$(EmptyAware.class);

    LambdaFactory l2 = Lambda.factory();
    SizeAware stub2 = l2.$(SizeAware.class);

    Function<Object, Integer> lambda = l2.lambda(stub2.size());
    Predicate<Object> lambda2 = l1.lambda(stub1.isEmpty());

    assertEquals(1, (int) lambda.apply(Option.some(50)));
    assertFalse(lambda2.eval(Option.some(50)));
  }

  /**
   * Test that the shared lambda factory is thread safe
   */
  @Test
  public void testSharedFactoryThreadSafe() throws Exception {
    ExecutorService service = Executors.newFixedThreadPool(10);
    final AtomicInteger ai = new AtomicInteger(0);
    Runnable runnable = new Runnable() {
      public void run() {
        List<Integer> list = Arrays.asList(10, 20, 30);
        assertFalse(lambda($(Collection.class).isEmpty()).eval(list));
        assertEquals(3, (int) lambda($(Collection.class).size()).apply(list));
        ai.incrementAndGet();
      }
    };
    for (int i = 0; i < 10; i++)
      service.execute(runnable);

    service.shutdown();
    service.awaitTermination(10, TimeUnit.SECONDS);
    assertEquals(10, ai.get());
  }

  /**
   * Test method for {@link LambdaFactory#lambda(boolean)}.
   */
  @Test
  public void testLambdaBoolean() {
    Predicate<Object> lambda = lambda($(Collection.class).isEmpty());
    assertTrue(lambda.eval(new ArrayList<Integer>()));
    assertFalse(lambda.eval(Collections.singleton(10)));
    assertTrue(lambda.eval(Collections.emptyList()));
  }

  /**
   * Test method for {@link Lambda#lambda2(java.lang.Object)}.
   */
  @Test
  public void testLambdaBooleanWithArgs() {
    Predicate<Object> lambda = lambda($(Collection.class).contains(50));
    assertFalse(lambda.eval(Arrays.asList(20, 65)));
    assertTrue(lambda.eval(Arrays.asList(20, 60, 50)));
  }

  /**
   * Test method for {@link Lambda#lambda2(java.lang.Object)}.
   */
  @Test
  public void testLambda2() {
    Function2<Object, Object, Boolean> lambda2 = lambda2($(Collection.class).remove(_));
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(60, 20, 3));

    assertFalse(lambda2.apply(list, 25));
    assertTrue(lambda2.apply(list, 20));
    assertFalse(list.contains(20));
  }

  /***/
  @Test
  public void testLambda2Curried() {
    Function2<Object, Object, List> lambda2 = lambda2($(List.class).subList(_(Integer.class), 3));
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(60, 20, 3));

    assertEquals(3, lambda2.apply(list, 0).size());
    assertEquals(2, lambda2.apply(list, 1).size());
    assertEquals(1, lambda2.apply(list, 2).size());
  }

  /**
   * Test method for {@link LambdaFactory#lambda3(java.lang.Object)}.
   */
  @Ignore
  @Test
  public void testLambda3() {
    fail("Not yet implemented");
  }

  /***/
  public interface Mock {
    /***/
    BigDecimal bd();

    /***/
    BigInteger bi();
  }

}
