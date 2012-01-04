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

package net.sf.staccatocommons.instrument.internal;

import static net.sf.staccatocommons.testing.mock.FileMock.*;
import static org.junit.Assert.*;

import java.io.File;

import net.sf.staccatocommons.io.Directory;

import org.junit.Test;

/**
 * Test for {@link ClassNames}
 * 
 * @author flbulgarelli
 * 
 */
public class ClassNamesUnitTest {

  /**
   * Test method for {@link ClassNames#getClassName(Directory, File)}
   */
  @Test
  public void testGetClassNameAbsolutePath() {
    assertEquals("com.foo.Foo", //
      ClassNames.getClassName(//
        new Directory(dir("/home/user/classes")),
        new File("/home/user/classes/com/foo/Foo.class")));
  }

  /**
   * Test method for {@link ClassNames#getClassName(Directory, File)}
   */
  @Test
  public void testGetClassNameAbsolutePathWithEndSeparator() {
    assertEquals("com.foo.Foo", //
      ClassNames.getClassName(//
        new Directory(dir("/home/user/classes/")),
        new File("/home/user/classes/com/foo/Foo.class")));
  }

  /**
   * Test method for {@link ClassNames#getClassName(Directory, File)}
   */
  @Test
  public void testGetClassNameRelativePaths() {
    assertEquals("com.foo.Foo", //
      ClassNames.getClassName(//
        new Directory(dir("classes")),
        new File("classes/com/foo/Foo.class")));
  }

  /**
   * Test method for {@link ClassNames#getClassName(Directory, File)}
   */
  @Test
  public void testGetClassNameInnerClasses() {
    assertEquals("com.foo.Foo$Internal", //
      ClassNames.getClassName(//
        new Directory(dir("classes")),
        new File("classes/com/foo/Foo$Internal.class")));
  }

}
