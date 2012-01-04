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

import net.sf.staccatocommons.instrument.context.AnnotationContext;
import net.sf.staccatocommons.restrictions.check.Matches;

/**
 * @author flbulgarelli
 * 
 */
public class MatchesHandler extends AbstractCheckAnnotationHandler<Matches> {

  /**
   * Creates a new {@link MatchesHandler}
   */
  public MatchesHandler(boolean ignoreReturns) {
    super(ignoreReturns);
  }

  @Override
  public Class<Matches> getSupportedAnnotationType() {
    return Matches.class;
  }

  protected String createCheckCode(String argumentMnemonic, String argumentIdentifier, Matches annotation,
    AnnotationContext context) {
    return String.format(
      "that().matches( \"%s\", %s, \"%s\")",
      argumentMnemonic,
      argumentIdentifier,
      annotation.value());
  }

  protected String getVarMnemonic(Matches annotation) {
    return annotation.var();
  }

}
