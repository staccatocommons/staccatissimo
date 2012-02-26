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

import net.sf.staccatocommons.dynamic.internal.Classes;
import net.sf.staccatocommons.dynamic.internal.NullDynamic;
import net.sf.staccatocommons.dynamic.internal.ReflectiveDynamic;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Class methods for creating simple {@link Dynamic}s
 * 
 * @author flbulgarelli
 */
public class Dynamics {

  private static final NullDynamic NULL = new NullDynamic();

  /**
   * Answers a new {@link Dynamic} wrapper for the given {@code object}, that
   * uses reflection to invoke methods, and property descriptors to get
   * 
   * @param object
   * @return a new {@link Dynamic}
   */
  @NonNull
  public static Dynamic from(@NonNull Object object) {
    return new ReflectiveDynamic(object);
  }

  /**
   * Answers a new {@link Dynamic} wrapper for the given {@code object}, if it
   * is not <code>null</code>, or <code>null_()</code>
   * 
   * otherwise
   * 
   * @param object
   * @return a new {@link Dynamic}, or {@link #null_()}, if the object is null
   */
  @NonNull
  public static Dynamic nullSafeFrom(Object object) {
    return object != null ? from(object) : Dynamics.null_();
  }

  /**
   * Answers the null Dynamic, that is, a {@link Dynamic} that understands all
   * messages, by doing nothing and returning <code>null</code>.
   * 
   * Its {@link Dynamic#value()} is <code>null</code>
   * 
   * @return the null dynamic
   */
  @Constant
  public static Dynamic null_() {
    return NULL;
  }

  /**
   * Answer a new {@link Dynamic} that wraps a new instance of a class for its
   * given classname
   * 
   * @param classname
   *          the class name
   * @return a new Dynamic wrapper for a new instance of the given class
   * @throws InstantiationFailedException
   *           if class can not be instantiated
   */
  public static Dynamic fromClassName(@NonNull String classname) {
      return from(Classes.newInstance(classname));
  }



  /**
   * Answer a new {@link Dynamic} that wraps a new instance of the given class
   * 
   * @param clazz
   *          the class to instantiate
   * @return a new Dynamic wrapper for a new instance of the given class
   * @throws InstantiationFailedException
   *           if class can not be instantiated
   */
  @NonNull
  public static Dynamic fromClass(@NonNull Class<?> clazz) {
    return from(Classes.newInstance(clazz));
  }


  /**
   * Answer a new {@link Dynamic} that wraps a new instance of the given class
   * 
   * @param clazz
   *          the class to instantiate
   * @return a new Dynamic wrapper for a new instance of the given class
   * @throws InstantiationFailedException
   *           if class can not be instantiated
   */
  @NonNull
  public static Dynamic fromClass(@NonNull Class<?> clazz, Object... args) {
    return from(Classes.newInstance(clazz, args));
  }
  


}
