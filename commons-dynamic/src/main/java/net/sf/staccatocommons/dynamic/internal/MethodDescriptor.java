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
package net.sf.staccatocommons.dynamic.internal;

import java.util.Arrays;

/**
 * A message and its receptor class
 * 
 * @author flbulgarelli
 */
public final class MethodDescriptor {
  private final Class<?> receptor;
  private final String selector;
  private final Class<?>[] argTypes;

  /**
   * Creates a new {@link MethodDescriptor}
   */
  public MethodDescriptor(Class<?> receptor, String name, Class<?>[] argTypes) {
    this.receptor = receptor;
    this.selector = name;
    this.argTypes = argTypes;
  }

  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(argTypes);
    result = prime * result + (selector == null ? 0 : selector.hashCode());
    result = prime * result + (receptor == null ? 0 : receptor.hashCode());
    return result;
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    MethodDescriptor other = (MethodDescriptor) obj;
    if (!Arrays.equals(argTypes, other.argTypes)) {
      return false;
    }
    if (selector == null) {
      if (other.selector != null) {
        return false;
      }
    } else if (!selector.equals(other.selector)) {
      return false;
    }
    if (receptor == null) {
      if (other.receptor != null) {
        return false;
      }
    } else if (!receptor.equals(other.receptor)) {
      return false;
    }
    return true;
  }

  /**
   * @return the selector
   */
  public String getSelector() {
    return selector;
  }

  /**
   * @return the argTypes
   */
  public Class<?>[] getArgTypes() {
    return argTypes;
  }

  /**
   * Answers a String that signals that a message was not understood
   */
  public String createNotUnderstoodMessage() {
    return String.format(
      "Message %s(%s) not understood by instance of class %s",
      selector,
      toString(argTypes),
      receptor);
  }

  private static String toString(Class<?>[] argumentTypes) {
    if (argumentTypes.length == 0)
      return "";
    StringBuilder sb = new StringBuilder(argumentTypes[0].getName());
    for (int i = 1; i < argumentTypes.length; i++) {
      sb.append(", ");
      sb.append(argumentTypes[i].getName());
    }
    return sb.toString();
  }
}