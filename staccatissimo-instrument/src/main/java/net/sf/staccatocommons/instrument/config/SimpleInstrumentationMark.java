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

package net.sf.staccatocommons.instrument.config;

import java.nio.charset.Charset;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Implementation of an {@link InstrumentationMark} where key and values are
 * provided as attributes
 * 
 * @author flbulgarelli
 */
public final class SimpleInstrumentationMark implements InstrumentationMark {

  private String markName;
  private String markValue;

  /**
   * Creates a new {@link SimpleInstrumentationMark}
   */
  public SimpleInstrumentationMark(@NonNull String markName, @NonNull String markValue) {
    this.markName = markName;
    this.markValue = markValue;
  }

  /**
   * @return The version number, as a class file version attribute value
   */
  public byte[] getMarkAttributeValue() {
    return markValue.getBytes(Charset.forName("UTF-8"));
  }

  public String getMarkAttributeName() {
    return markName;
  }
}