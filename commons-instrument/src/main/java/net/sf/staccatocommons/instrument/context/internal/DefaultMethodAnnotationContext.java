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

package net.sf.staccatocommons.instrument.context.internal;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import net.sf.staccatocommons.instrument.context.MethodAnnotationContext;

import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultMethodAnnotationContext extends AbstractAnnotationContext implements
  MethodAnnotationContext {

  private CtMethod method;

  /**
   * Creates a new {@link DefaultMethodAnnotationContext}
   */
  public DefaultMethodAnnotationContext(ClassPool pool, Logger logger) {
    super(pool, logger);
  }

  /**
   * @return the method
   */
  public CtMethod getMethod() {
    return method;
  }

  /**
   * @param method
   *          the method to set
   */
  public void setMethod(CtMethod method) {
    this.method = method;
  }

  @Override
  public String getReturnIdentifier() {
    return "$_";
  }

  public CtClass getDeclaringClass() {
    return getMethod().getDeclaringClass();
  }

  @Override
  public boolean isVoid() {
    try {
      return getMethod().getReturnType().getName().equals("void");
    } catch (NotFoundException e) {
      return false;
    }
  }

  public CtClass getElementType() throws NotFoundException {
    return getMethod().getReturnType();
  }

  @Override
  public boolean isPublic() {
    return Modifier.isPublic(getMethod().getModifiers());
  }
}
