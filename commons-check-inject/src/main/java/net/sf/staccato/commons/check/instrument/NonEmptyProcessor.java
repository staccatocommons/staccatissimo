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

import net.sf.staccato.commons.instrument.AnnotatedArgumentContext;
import net.sf.staccato.commons.instrument.AnnotatedMethodContext;
import net.sf.staccato.commons.lang.check.annotation.NonEmpty;

import org.apache.commons.lang.NotImplementedException;

/**
 * @author flbulgarelli
 * 
 */
public class NonEmptyProcessor extends AbstractCheckAnnotationProcessor {

	@Override
	public Class<? extends Annotation> getSupportedAnnotationType() {
		return NonEmpty.class;
	}

	@Override
	protected String createArgumentCheck(Object annotation,
		AnnotatedArgumentContext context) {
		NonEmpty nonEmpty = (NonEmpty) annotation;
		return String.format(
			"notEmpty( \"%s\", %s);",
			parameterName(context.getArgumentNumber(), nonEmpty.var()),
			context.getArgumentName());
	}

	@Override
	protected String createMethodCheck(Object annotation, AnnotatedMethodContext context) {
		throw new NotImplementedException();
	}
}
