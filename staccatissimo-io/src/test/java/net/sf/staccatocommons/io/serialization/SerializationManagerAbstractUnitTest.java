/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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

package net.sf.staccatocommons.io.serialization;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.GregorianCalendar;

import org.apache.commons.lang.SerializationException;
import org.junit.Before;
import org.junit.Test;

/***
 * Test for {@link SerializationManager}
 * 
 * @author flbulgarelli
 * 
 */
public abstract class SerializationManagerAbstractUnitTest {

  private SerializationManager serializationManager;

  /** Setups the test */
  @Before
  public void setUp() {
    serializationManager = createSerializationManager();
  }

  protected abstract SerializationManager createSerializationManager();

  /***/
  @Test
  public void testSerializeDeserialize() {
    GregorianCalendar valueObject = new GregorianCalendar(2011, 5, 6);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    serializationManager.serialize(valueObject, out);
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    assertEquals(valueObject, serializationManager.deserialize(in));
  }

  /***/
  @Test(expected = SerializationException.class)
  public void testDeserializeIOFails() {
    serializationManager.serialize(new Object(), new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        throw new IOException();
      }
    });
  }

  /***/
  @Test(expected = SerializationException.class)
  public void testSerializeIOFails() {
    serializationManager.deserialize(new InputStream() {
      @Override
      public int read() throws IOException {
        throw new IOException();
      }
    });
  }
}