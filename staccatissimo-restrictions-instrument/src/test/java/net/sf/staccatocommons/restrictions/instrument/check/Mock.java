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


package net.sf.staccatocommons.restrictions.instrument.check;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import net.sf.staccatocommons.restrictions.check.MaxSize;
import net.sf.staccatocommons.restrictions.check.MinSize;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotEmpty;
import net.sf.staccatocommons.restrictions.check.NotNegative;
import net.sf.staccatocommons.restrictions.check.NotZero;
import net.sf.staccatocommons.restrictions.check.Positive;
import net.sf.staccatocommons.restrictions.check.Size;
import net.sf.staccatocommons.restrictions.processing.EnforceRestrictions;
import net.sf.staccatocommons.restrictions.processing.IgnoreRestrictions;

/**
 * @author flbulgarelli
 * 
 */
public class Mock {
  /***/
  public Mock() {}

  /***/
  public Mock(@NonNull Object argument, @NonNull Integer argument2) {}

  /***/
  @IgnoreRestrictions
  public Mock(@NonNull Object argument, @NonNull Long argument2) {}

  /***/
  @EnforceRestrictions
  public Mock(@NonNull Object argument, @NonNull String argument2) {}

  /***/
  @NonNull
  public Object defaultReturnNonNull() {
    return null;
  }

  /***/
  @EnforceRestrictions
  public void forceChecksNonNullMethodArgument(@NonNull Object argument) {}

  /***/
  @IgnoreRestrictions
  public void ignoreChecksNonNullMethodArgument(@NonNull Object argument) {}

  /***/
  public void defaultNonNullMethodArgument(@NonNull Object argument) {}
  
  /***/
  protected void defaultNonNullProtectedMethodArgument(@NonNull Object argument) {}

  /***/
  public void defaultSizeMethodArgument(@Size(1) List<?> argument) {}

  /***/
  public void defaultNotEmptyMethodArgument(@NotEmpty String argument) {}

  /***/
  public void defaultPositiveMethodArgument(@Positive BigDecimal argument) {}

  /***/
  public void defaultNotNegative(@NotNegative int i) {

  }

  /***/
  public void defaulMinSize(@MinSize(4) List<Integer> asList) {

  }

  /***/
  public void defaulMaxSize(@MaxSize(2) Collection<?> col) {

  }

  /***/
  public void defaulNotZero(@NotZero long l) {

  }
}
