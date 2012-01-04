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


package net.sf.staccatocommons.dynamic.internal;

import net.sf.staccatocommons.dynamic.AbstractDynamic;
import net.sf.staccatocommons.dynamic.Dynamic;

import org.apache.commons.proxy.Invoker;

/**
 * @author flbulgarelli
 * 
 */
public class NullDynamic extends AbstractDynamic {

  public <T> T send(String selector, Object... args) {
    return null;
  }

  public Dynamic chainingSend(String selector, Object... args) {
    return this;
  }

  public Object value() {
    return null;
  }

  protected boolean valueIsInstanceOf(Class<?> clazz) {
    return false;
  }

  protected Invoker getInvoker() {
    return NULL_INVOKER;
  }

}
