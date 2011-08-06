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

package net.sf.staccatocommons.io;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringReader;

import net.sf.staccatocommons.lang.Range;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class IOStreamsUnitTest {

  private static final String SAMPLE_TEXT = "this is line 1\n this is line 2 \n this is line three";
  private Reader readable;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    readable = new StringReader(SAMPLE_TEXT);
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.ReadStrategies#readLines()}.
   */
  @Test
  public void testReadLines() {
    assertEquals("this is line 1| this is line 2 | this is line three", IOStreams.fromLines(readable).joinStrings("|"));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.ReadStrategies#readWords()}.
   */
  @Test
  public void testReadWords() {
    assertEquals("this|is|line|1|this|is|line|2|this|is|line|three", IOStreams.fromWords(readable).joinStrings("|"));
  }

  /**
   * Test for {@link IOStreams#fromObjects(java.io.ObjectInput)}
   * 
   * @throws IOException
   */
  @Test
  public void testReadObjects() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream objectOutput = new ObjectOutputStream(out);

    objectOutput.writeObject(Range.from(10, 90));
    objectOutput.writeObject(Range.from(8, 40));
    objectOutput.writeObject(Range.from(9, 10));
    objectOutput.writeObject(Range.from(9, 12));

    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

    assertEquals(
      "[Range(10,90), Range(8,40), Range(9,10), Range(9,12)]",
      IOStreams.<Range<Integer>> fromObjects(new ObjectInputStream(in)).printString());
  }

}
