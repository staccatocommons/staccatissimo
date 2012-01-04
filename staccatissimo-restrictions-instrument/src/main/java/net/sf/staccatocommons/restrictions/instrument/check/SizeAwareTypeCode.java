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

import javassist.CtClass;
import javassist.NotFoundException;
import net.sf.staccatocommons.check.internal.SizeAwareTypes;
import net.sf.staccatocommons.defs.type.SizeAwareType;
import net.sf.staccatocommons.instrument.context.AnnotationContext;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class SizeAwareTypeCode {

  private static final String SIZE_AWARE_TYPES = "net.sf.staccatocommons.check.internal.SizeAwareTypes.";

  /**
   * Answers a code snippet that returns a {@link SizeAwareType} for the given
   * annotated argument
   * 
   * @param context
   * @return the code snippet that switches over the annotated argument type.
   *         The fallback of such switch is {@link SizeAwareTypes#SIZE_AWARE}
   * @throws NotFoundException
   */
  public static String getSizeAwareCode(@NonNull AnnotationContext context) throws NotFoundException {
    return SIZE_AWARE_TYPES + getSizeAwareCodeInternal(context);
  }

  private static String getSizeAwareCodeInternal(AnnotationContext context) throws NotFoundException {
    CtClass ctClass = context.getElementType();
    if (ctClass.isArray())
      return "ARRAY";

    if (ctClass.getName().equals("java.lang.String"))
      return "CHAR_SEQUENCE";

    String classname = "java.util.Collection";
    if (ctClass.subtypeOf(context.getClass(classname)))
      return "COLLECTION";

    if (ctClass.subtypeOf(context.getClass("java.util.Map")))
      return "MAP";

    if (ctClass.subtypeOf(context.getClass("java.lang.CharSequence")))
      return "CHAR_SEQUENCE";

    return "SIZE_AWARE";
  }

}
