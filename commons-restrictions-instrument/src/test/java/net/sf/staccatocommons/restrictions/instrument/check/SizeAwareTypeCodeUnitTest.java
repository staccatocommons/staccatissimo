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
package net.sf.staccatocommons.restrictions.instrument.check;

import static org.junit.Assert.*;

import java.util.Arrays;

import javassist.ClassPool;
import net.sf.staccatocommons.instrument.context.internal.DefaultClassAnnotationContext;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class SizeAwareTypeCodeUnitTest extends JUnit4MockObjectTestCase {

  private Logger logger;
  private ClassPool pool;

  /**
   * 
   */
  @Before
  public void setup() {
    logger = mock(Logger.class);
    pool = ClassPool.getDefault();
  }

  /** Test for collection context */
  @Test
  public void testGetSizeAwareCodeConcreteCollection() throws Exception {
    String code = SizeAwareTypeCode.getSizeAwareCode(//
      new DefaultClassAnnotationContext(pool, logger, //
        pool.get(Arrays.asList(4).getClass().getName())));

    assertTrue("Should use collection:" + code, code.endsWith("COLLECTION"));
  }

  /** Test for string context */
  @Test
  public void testGetSizeAwareCodeString() throws Exception {
    String code = SizeAwareTypeCode.getSizeAwareCode(//
      new DefaultClassAnnotationContext(pool, logger, //
        pool.get(String.class.getName())));

    assertTrue("Should use String:" + code, code.endsWith("CHAR_SEQUENCE"));
  }

  /** Test for array context */
  @Test
  public void testGetSizeAwareCodeArray() throws Exception {
    String code = SizeAwareTypeCode.getSizeAwareCode(//
      new DefaultClassAnnotationContext(pool, logger, //
        pool.get("int[]")));

    assertTrue("Should use Array:" + code, code.endsWith("ARRAY"));
  }

  /** Test for array context */
  @Test
  public void testGetSizeAwareCodeConcreteMap() throws Exception {
    String code = SizeAwareTypeCode.getSizeAwareCode(//
      new DefaultClassAnnotationContext(pool, logger, //
        pool.get("java.util.HashMap")));

    assertTrue("Should use Map:" + code, code.endsWith("MAP"));
  }

  /** Test for array context */
  @Test
  public void testGetSizeAwareCodeMap() throws Exception {
    String code = SizeAwareTypeCode.getSizeAwareCode(//
      new DefaultClassAnnotationContext(pool, logger, //
        pool.get("java.util.Map")));

    assertTrue("Should use Map:" + code, code.endsWith("MAP"));
  }
}
