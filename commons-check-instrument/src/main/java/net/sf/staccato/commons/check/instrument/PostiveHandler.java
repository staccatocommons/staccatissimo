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

import net.sf.staccato.commons.instrument.context.ArgumentAnnotationContext;
import net.sf.staccato.commons.instrument.context.MethodAnnotationContext;
import net.sf.staccato.commons.lang.check.annotation.Positive;

import org.apache.commons.lang.NotImplementedException;

/**
 * @author flbulgarelli
 * 
 */
public class PostiveHandler extends AbstractCheckAnnotationHandler {

	public Class<? extends Annotation> getSupportedAnnotationType() {
		return Positive.class;
	}

	protected String createArgumentCheck(Object annotation, ArgumentAnnotationContext context) {
		Positive positive = (Positive) annotation;
		return String.format(
			"that().isPositive( \"%s\", %s);",
			argumentName(context.getArgumentNumber(), positive.value()),
			context.getArgumentName());
	}

	protected String createMethodCheck(Object annotation, MethodAnnotationContext context) {
		throw new NotImplementedException();
	}

}
