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
package net.sf.staccatocommons.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.staccatocommons.testing.mock.FileMock;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class FilesUnitTest {

  /**
   * Test method for {@link net.sf.staccatocommons.io.Files#fileName()}.
   */
  @Test
  public void testFileName() {
    assertEquals("foo.bar", Files.fileName().apply(FileMock.file("/home/user/foo.bar")));
    assertEquals("foo.bar", Files.fileName().apply(FileMock.file("C:/home/user/foo.bar")));
  }

  /**
   * Test method for {@link net.sf.staccatocommons.io.Files#filePath()}.
   */
  @Test
  public void testFilePath() {
    assertEquals("/home/user/foo.bar", Files.filePath().apply(FileMock.file("/home/user/foo.bar")));
    assertEquals("C:/home/user/foo.bar", Files.filePath().apply(FileMock.file("C:/home/user/foo.bar")));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.Files#suffix(java.lang.String[])}.
   */
  @Test
  public void testSuffix() {
    assertTrue(Files.suffix(".bar", ".baz").apply(FileMock.file("C:/home/user/foo.bar")));
    assertFalse(Files.suffix(".bin", ".exe").apply(FileMock.file("C:/home/user/foo.bar")));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.Files#openReader(java.io.File)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOpenReaderDir() {
    Files.openReader(FileMock.dir("/etc"));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.Files#openReader(java.io.File)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOpenReaderReadOnly() {
    Files.openReader(FileMock.unreadableFile("/dev/sda25"));
  }

}
