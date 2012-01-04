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

package net.sf.staccatocommons.restrictions.instrument;

import javassist.CannotCompileException;
import net.sf.staccatocommons.instrument.context.ClassAnnotationContext;
import net.sf.staccatocommons.instrument.context.ConstructorAnnotationContext;
import net.sf.staccatocommons.instrument.context.MethodAnnotationContext;
import net.sf.staccatocommons.instrument.handler.ClassAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.ConstructorAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.deactivator.AbstractActivationAnnotationHandler;
import net.sf.staccatocommons.restrictions.processing.EnforceRestrictions;

/**
 * @author flbulgarelli
 * 
 */
public class ForceChecksHandler extends AbstractActivationAnnotationHandler<EnforceRestrictions> implements
  ClassAnnotationHandler<EnforceRestrictions>, ConstructorAnnotationHandler<EnforceRestrictions>,
  MethodAnnotationHandler<EnforceRestrictions> {

  /**
   * Creates a new {@link ForceChecksHandler}
   */
  public ForceChecksHandler() {
    super();
  }

  public Class<EnforceRestrictions> getSupportedAnnotationType() {
    return EnforceRestrictions.class;
  }

  public void preProcessAnnotatedMethod(EnforceRestrictions annotation, MethodAnnotationContext context) {
    activateAll();
  }

  public void postProcessAnnotatedMethod(EnforceRestrictions annotation, MethodAnnotationContext context) {
    deactivateAll();
  }

  public void preProcessAnnotatedConstructor(EnforceRestrictions annotation, ConstructorAnnotationContext context) {
    activateAll();
  }

  public void postProcessAnnotatedConstructor(EnforceRestrictions annotation, ConstructorAnnotationContext context) {
    deactivateAll();
  }

  @Override
  public void preProcessAnnotatedClass(EnforceRestrictions annotation, ClassAnnotationContext context)
    throws CannotCompileException {
    activateAll();

  }

  @Override
  public void postProcessAnnotatedClass(EnforceRestrictions annotation, ClassAnnotationContext context)
    throws CannotCompileException {
    deactivateAll();
  }

}
