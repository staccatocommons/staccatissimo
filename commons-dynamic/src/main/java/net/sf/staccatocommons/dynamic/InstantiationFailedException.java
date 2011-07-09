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
package net.sf.staccatocommons.dynamic;

/**
 * @author flbulgarelli
 * 
 */
public class InstantiationFailedException extends RuntimeException {

  private static final long serialVersionUID = -7964159756322761305L;

  /**
   * Creates a new {@link InstantiationFailedException}
   */
  public InstantiationFailedException(Throwable cause) {
    super(cause);
  }

}
