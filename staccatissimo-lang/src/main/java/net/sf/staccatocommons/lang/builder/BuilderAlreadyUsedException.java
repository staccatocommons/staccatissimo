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

package net.sf.staccatocommons.lang.builder;

/**
 * Exception that signals that a {@link Builder} has being used more than once,
 * but it does not support it.
 * 
 * @author flbulgarelli
 * 
 */
public class BuilderAlreadyUsedException extends IllegalStateException {

  private static final long serialVersionUID = 231498480728197116L;

  /**
   * Creates an exception with a descriptive message, and no cause
   */
  public BuilderAlreadyUsedException() {
    super("This builder has already being used");
  }

}
