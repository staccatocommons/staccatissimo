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

/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections;

import java.util.NoSuchElementException;

/**
 * {@link NoSuchElementException} that indicates that an element could not be
 * retrieved or calculated from an iterator or iterable source because that
 * source is empty
 * 
 * @author flbulgarelli
 * @since 2.2
 */
public class EmptySourceException extends NoSuchElementException {

  /**
   * 
   */
  private static final long serialVersionUID = -355442018499465588L;

  /**
   * Creates a new {@link EmptySourceException}
   */
  public EmptySourceException(String message) {
    super(message);
  }

}
