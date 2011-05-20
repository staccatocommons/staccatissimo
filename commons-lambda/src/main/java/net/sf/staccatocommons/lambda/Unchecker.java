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
package net.sf.staccatocommons.lambda;

/**
 * @author flbulgarelli
 * 
 */
final class Unchecker<E extends Throwable> {

  private static final Unchecker<RuntimeException> INSTANCE = new Unchecker<RuntimeException>();

  private Unchecker() {}

  public void throwUncheckedImpl(Throwable throwable) throws E {
    throw (E) throwable;
  }

  public static void throwUnchecked(Throwable e) {
    INSTANCE.throwUncheckedImpl(e);
  }

}
