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


package net.sf.staccatocommons.dynamic;

import net.sf.staccatocommons.dynamic.internal.MethodDescriptor;
import net.sf.staccatocommons.dynamic.internal.ReflectiveDynamic;

/**
 * An exception that denotes that a message was not understood by a
 * {@link ReflectiveDynamic}, because the underlying object did not have a
 * method that matches a given {@link MethodDescriptor}
 * 
 * @author flbulgarelli
 * 
 */
public class MessageNotUnderstoodException extends IllegalArgumentException {

  private static final long serialVersionUID = -9164408465437029311L;

  /**
   * Creates a new {@link MessageNotUnderstoodException}
   */
  public MessageNotUnderstoodException(MethodDescriptor expectedMethod) {
    this(expectedMethod.createNotUnderstoodMessage());
  }

  /**
   * Creates a new {@link MessageNotUnderstoodException}
   */
  public MessageNotUnderstoodException(String s) {
    super(s);
  }

}
