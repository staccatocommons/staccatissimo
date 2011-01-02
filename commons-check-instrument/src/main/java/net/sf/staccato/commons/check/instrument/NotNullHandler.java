/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.check.instrument;

import java.lang.annotation.Annotation;

import javassist.CannotCompileException;
import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.instrument.context.ArgumentAnnotationContext;

/**
 * @author flbulgarelli
 * 
 */
public class NotNullHandler extends AbstractCheckAnnotationHandler<NonNull> {

	/**
	 * Creates a new {@link NotNullHandler}
	 */
	public NotNullHandler(boolean ignoreReturns) {
		super(ignoreReturns);
	}

	@Override
	public Class<? extends Annotation> getSupportedAnnotationType() {
		return NonNull.class;
	}

	@Override
	protected String getVarMnemonic(NonNull nonNull) {
		return nonNull.value();
	}

	public void processAnnotatedArgument(Object annotation, ArgumentAnnotationContext context)
		throws CannotCompileException {
		if (context.isConstructorArgument()) {
			super.processAnnotatedArgument(annotation, context);
		} else {
			deactivate();
			super.processAnnotatedArgument(annotation, context);
			activate();
		}
	}

	@Override
	protected String createCheckCode(String argumentMnemonic, String argumentIdentifier,
		NonNull annotation) {
		return String.format("isNotNull( \"%s\", %s)", argumentMnemonic, argumentIdentifier);
	}

}
