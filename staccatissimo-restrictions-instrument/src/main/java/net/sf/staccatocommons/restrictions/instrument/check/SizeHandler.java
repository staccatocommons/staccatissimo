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

import javassist.NotFoundException;
import net.sf.staccatocommons.instrument.context.AnnotationContext;
import net.sf.staccatocommons.restrictions.check.Size;

/**
 * @author flbulgarelli
 * 
 */
public class SizeHandler extends AbstractCheckAnnotationHandler<Size> {

  /**
   * Creates a new {@link SizeHandler}
   */
  public SizeHandler(boolean ignoreReturns) {
    super(ignoreReturns);
  }

  public Class<Size> getSupportedAnnotationType() {
    return Size.class;
  }

  protected String createCheckCode(String argumentMnemonic, String argumentIdentifier, Size annotation,
    AnnotationContext context) throws NotFoundException {
    return String.format(
      "that().isSize(\"%s\", %s, %s, %s)",
      argumentMnemonic,
      argumentIdentifier,
      annotation.value(),
      SizeAwareTypeCode.getSizeAwareCode(context));
  }

  protected String getVarMnemonic(Size annotation) {
    return annotation.var();
  }

}
