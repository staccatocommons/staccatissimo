package net.sf.staccato.commons.instrument.examples;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import net.sf.staccato.commons.instrument.context.MethodAnnotationContext;
import net.sf.staccato.commons.instrument.handler.MethodAnnotationHandler;

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

/**
 * @author flbulgarelli
 * 
 */
public class LogHandler implements MethodAnnotationHandler<Loggeable> {

	public Class<Loggeable> getSupportedAnnotationType() {
		return Loggeable.class;
	}

	public void preProcessAnnotatedMethod(Loggeable annotation, MethodAnnotationContext context)
		throws CannotCompileException {
		if (!hasLogger(context)) {
			CtClass declaringClass = context.getMethod().getDeclaringClass();
			CtField field = CtField.make(
				"private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(\""
					+ declaringClass.getName() + "\");",
				declaringClass);
			declaringClass.addField(field);
		}
	}

	public void postProcessAnnotatedMethod(Loggeable annotation, MethodAnnotationContext context)
		throws CannotCompileException {
		context.getMethod().insertAfter("logger.warning(\"" + context.getMethod().getName() + "\");");
		context.getMethod().addCatch("logger.severe($e.getMessage());throw $e;", getThrowable(context));
	}

	private boolean hasLogger(MethodAnnotationContext context) {
		try {
			context.getMethod().getDeclaringClass().getField("logger");
			return true;
		} catch (NotFoundException e) {
			return false;
		}
	}

	private CtClass getThrowable(MethodAnnotationContext context) {
		try {
			return context.getClassPool().get(Throwable.class.getCanonicalName());
		} catch (NotFoundException e) {
			// can never happen
			throw new AssertionError(e);
		}
	}
}
