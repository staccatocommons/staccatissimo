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

import static net.sf.staccatocommons.testing.mock.FileMock.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

import net.sf.staccatocommons.collections.stream.Stream;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class DirectoryUnitTest {

  /**
	 * 
	 */
  private static final String DIRECTORY_TEST_PATHNAME = "src/test/resources/directory-test";
  private File root;

  /**
   * Creates a mocked directory
   */
  @Before
  public void setup() {
    root = dir("root", //
      dir("root/d1", //
        file("root/d1/f1"),
        file("root/d1/f2")),
      dir("root/d2",//
        dir("root/d2/d3"),
        dir("root/d2/d4",//
          file("root/d2/d4/f3")),
        file("root/d2/f4")),
      file("root/f5"));
  }

  /**
   * Test method for {@link net.sf.staccatocommons.io.Directory#getFile()}.
   */
  @Test
  public void testGetFile() {
    assertEquals(//
      new Directory(DIRECTORY_TEST_PATHNAME).getFile(),
      new Directory(new File(DIRECTORY_TEST_PATHNAME)).getFile());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.Directory#getAbsolutePath()}.
   */
  @Test
  public void testGetAbsolutePath() {
    assertEquals(//
      new Directory(DIRECTORY_TEST_PATHNAME).getAbsolutePath(),
      new File(DIRECTORY_TEST_PATHNAME).getAbsolutePath());
  }

  /**
   * Test method for {@link net.sf.staccatocommons.io.Directory#getFileStream()}
   * .
   */
  @Test
  public void testGetFileStream() {
    new Directory(root).getFileStream().toSet().equals(new HashSet<File>(Arrays.asList(root.listFiles())));
  }

  /**
   * Test that, regardless the traverse strategy, the elements of the file
   * streams are the same
   */
  @Test
  public void testRecurseFileStreamElements() {
    Directory directory = new Directory(root);
    assertEquals(//
      directory.getBreadthFirstFileStream().toSet(),
      directory.getDepthFirstFileStream().toSet());

  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.Directory#getDepthFirstFileStream()}.
   */
  @Test
  public void testGetDepthFirstFileStream() {
    Stream<File> stream = new Directory(root).getDepthFirstFileStream();
    assertEquals(Arrays.asList("f1", "f2", "f3", "f4", "f5"), stream.map(IOFunctions.fileName()).toList());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.Directory#getRecurseFileStream()}.
   */
  @Test
  public void testGetRecurseFileStream() {
    Stream<File> stream = new Directory(root).getBreadthFirstFileStream();
    assertEquals(Arrays.asList("f5", "f1", "f2", "f4", "f3"), stream.map(IOFunctions.fileName()).toList());
  }
}
