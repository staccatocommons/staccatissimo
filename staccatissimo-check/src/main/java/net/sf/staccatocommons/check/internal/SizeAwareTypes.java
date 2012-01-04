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
package net.sf.staccatocommons.check.internal;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import net.sf.staccatocommons.defs.partial.SizeAware;
import net.sf.staccatocommons.defs.type.SizeAwareType;

/**
 * 
 * {@link SizeAwareType}s for internal usage
 * 
 * @author flbulgarelli
 * 
 */
public class SizeAwareTypes {

  private SizeAwareTypes() {}

  /**
   * {@link SizeAwareType} for {@link SizeAware}s
   */
  public static final SizeAwareType<SizeAware> SIZE_AWARE = new AbstractSizeAwareType<SizeAware>() {
    public int size(SizeAware sizeAware) {
      return sizeAware.size();
    }
  };

  /**
   * {@link SizeAwareType} for {@link CharSequence}s
   */
  public static final SizeAwareType<CharSequence> CHAR_SEQUENCE = new AbstractSizeAwareType<CharSequence>() {
    public int size(CharSequence sizeAware) {
      return sizeAware.length();
    }
  };

  /**
   * {@link SizeAwareType} for {@link Collection}s
   */
  public static final SizeAwareType<Collection<?>> COLLECTION = new SizeAwareType<Collection<?>>() {
    public int size(Collection<?> sizeAware) {
      return sizeAware.size();
    }

    public boolean isEmpty(Collection<?> emptyAware) {
      return emptyAware.isEmpty();
    }
  };

  /**
   * {@link SizeAwareType} for {@link Map}s
   */
  public static final SizeAwareType<Map<?, ?>> MAP = new SizeAwareType<Map<?, ?>>() {
    public int size(Map<?, ?> sizeAware) {
      return sizeAware.size();
    }

    public boolean isEmpty(Map<?, ?> emptyAware) {
      return emptyAware.isEmpty();
    }
  };

  /**
   * A {@link SizeAwareType} for arrays
   */
  public static final SizeAwareType<Object> ARRAY = new AbstractSizeAwareType<Object>() {
    public int size(Object sizeAware) {
      return Array.getLength(sizeAware);
    }
  };

}
