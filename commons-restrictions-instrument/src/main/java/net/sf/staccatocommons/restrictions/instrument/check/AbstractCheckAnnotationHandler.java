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

package net.sf.staccatocommons.restrictions.instrument.check;

import java.lang.annotation.Annotation;

import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.NotFoundException;
import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.instrument.context.AnnotationContext;
import net.sf.staccatocommons.instrument.context.ArgumentAnnotationContext;
import net.sf.staccatocommons.instrument.context.MethodAnnotationContext;
import net.sf.staccatocommons.instrument.handler.ArgumentAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.deactivator.Deactivable;
import net.sf.staccatocommons.instrument.handler.deactivator.StackedDeactivableSupport;
import net.sf.staccatocommons.lang.SoftException;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractCheckAnnotationHandler<T extends Annotation> implements MethodAnnotationHandler<T>,
  ArgumentAnnotationHandler<T>, Deactivable {

  protected static final String ENSURE_FULLY_QUALIFIED_NAME = "net.sf.staccatocommons.check.Ensure.";
  protected static final String ASSERT_FULLY_QUALIFIED_NAME = "net.sf.staccatocommons.check.Assert.";
  private final StackedDeactivableSupport deactivableSupport = new StackedDeactivableSupport();
  private final boolean ignoreReturns;

  /**
   * Creates a new {@link AbstractCheckAnnotationHandler} specifying if check
   * annotation in methods should be interpreted as checks over its return value
   * and processed
   * 
   * @param ignoreReturns
   *          if check annotation over returns should be processed
   */
  public AbstractCheckAnnotationHandler(boolean ignoreReturns) {
    this.ignoreReturns = ignoreReturns;
  }

  @Override
  public void processAnnotatedArgument(T annotation, ArgumentAnnotationContext context) throws CannotCompileException,
    NotFoundException {
    if (!isActive(context))
      return;
    try {
      context.getArgumentBehavior().insertBefore(
        ENSURE_FULLY_QUALIFIED_NAME + createArgumentCheck(annotation, context) + ";");
    } catch (CannotCompileException e) {
      logError(context, context.getArgumentBehavior(), e);
      throw e;
    }
  }

  @Override
  public void preProcessAnnotatedMethod(T annotation, MethodAnnotationContext context) throws NotFoundException {
    if (ignoreReturns || !isActive(context))
      return;
    // TODO handle properly
    Ensure.that(!context.isVoid(), "Context must not be void: %s", context.getMethod().getLongName());
    try {
      context.getMethod().insertAfter(ASSERT_FULLY_QUALIFIED_NAME + createMethodCheck(annotation, context) + ";");
    } catch (CannotCompileException e) {
      logError(context, context.getMethod(), e);
      throw SoftException.soften(e);
    }
  }

  private void logError(AnnotationContext context, CtBehavior be, CannotCompileException e) {
    context.logErrorMessage("Could not insert argument check on method {}: {}", //
      be.getLongName(),
      e.getMessage());
  }

  @Override
  public void postProcessAnnotatedMethod(T annotation, MethodAnnotationContext context) {}

  protected String createArgumentCheck(T annotation, ArgumentAnnotationContext context) throws NotFoundException {
    return createCheckCode(
      getArgumentMnemonic(context, getVarMnemonic(annotation)),
      context.getArgumentIdentifier(),
      annotation,
      context);
  }

  private String getArgumentMnemonic(ArgumentAnnotationContext context, String annotatedVarName) {
    return annotatedVarName.isEmpty() ? "var" + context.getArgumentNumber() : annotatedVarName;
  }

  private String createMethodCheck(T annotation, MethodAnnotationContext context) throws NotFoundException {
    return createCheckCode(
      getReturnName(getVarMnemonic(annotation)),
      context.getReturnIdentifier(),
      annotation,
      context);
  }

  protected String getReturnName(String returnName) {
    return returnName.isEmpty() ? "returnValue" : returnName;
  }

  public final void deactivate() {
    deactivableSupport.deactivate();
  }

  public final void activate() {
    deactivableSupport.activate();
  }

  protected abstract String createCheckCode(String argumentMnemonic, String argumentIdentifier, T annotation,
    AnnotationContext context) throws NotFoundException;

  protected abstract String getVarMnemonic(T annotation);

  protected boolean activeByDefault(AnnotationContext context) {
    return context.isPublic() && !context.getPackage().matches(".+\\.internal(\\..+)?");
  }
  
  private boolean isActive(AnnotationContext context) {
    boolean activeByDefault = activeByDefault(context);
    if (activeByDefault)
      deactivableSupport.activate();
    try {
      return deactivableSupport.isActive();
    } finally {
      if (activeByDefault)
        deactivableSupport.deactivate();
    }
  }

  
}
