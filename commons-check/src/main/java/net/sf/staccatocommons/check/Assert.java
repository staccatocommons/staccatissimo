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

package net.sf.staccatocommons.check;

/**
 * {@link Assert} is a utility class for performing postcondition checks
 * 
 * @author flbulgarelli
 * 
 */
public final class Assert {

  private static final Check<AssertionError> check = new Check<AssertionError>() {
    protected AssertionError createException(Failure failure) {
      return new AssertionError(failure.createMessage());
    }
  };

  private Assert() {}

  /**
   * Shortcut to <code>Assert.that().fail(varName,var,message,args)</code>
   * 
   * @see Check#fail(String, Object, String, Object...)
   */
  public static void fail(String varName, Object var, String message) {
    that().fail(varName, var, message);
  }

  /**
   * Shortcut to <code>Assert.that().fail(message,args)</code>
   * 
   * @see Check#fail(String, Object, String, Object...)
   */
  public static void fail(String message, Object... args) {
    that().fail(message, args);
  }

  /**
   * Shortcut to
   * <code>Assert.that().is(varName,var,condition,message,args)</code>
   * 
   * @see Check#that(String, Object, boolean, String, Object...)
   */
  public static void that(String varName, Object var, boolean condition, String message, Object... messageArgs) {
    that().that(varName, var, condition, message, messageArgs);
  }

  /**
   * Shortcut to <code>Assert.that().is(condition,message,args)</code>
   * 
   * @see Check#that(boolean, String, Object...)
   */
  public static void that(boolean condition, String message, Object... messageArgs) {
    that().that(condition, message, messageArgs);
  }

  /**
   * Shortcut to <code>Assert.that().isNotNull(variableName, variable)</code>
   * 
   * @see Check#isNotNull(String, Object)
   */
  public static void isNotNull(String variableName, Object variable) {
    that().isNotNull(variableName, variable);
  }

  /**
   * Shortcut to <code>Assert.that().isNull(variableName, variable)</code>
   * 
   * @see Check#isNull(String, Object)
   */
  public static void isNull(String variableName, Object variable) {
    that().isNull(variableName, variable);
  }

  /**
   * @return a singleton {@link Check} that throws {@link AssertionError}s on
   *         check failure
   */
  public static Check<AssertionError> that() {
    return check;
  }
}
