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


package net.sf.staccatocommons.restrictions.instrument;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 */
public class Mock {

  private static Date today = new Date();

  /***/
  @Constant
  public static Date getDateFieldAcess() {
    return today;
  }

  /***/
  @Constant
  public static Date getDateInvokationWithArg() {
    return java.sql.Date.valueOf("2011-01-01");
  }

  /***/
  @Constant
  public static Date getDateInvokationWithNoArgs() {
    return getDateNoConstant();
  }

  private static Date getDateNoConstant() {
    return new Date();
  }

  /****/
  @Constant
  public static Date getDateConstructor() {
    return new Date();
  }

  /***/
  @Constant
  public BigDecimal getBigDecimal() {
    return new BigDecimal(this.hashCode());
  }
}
