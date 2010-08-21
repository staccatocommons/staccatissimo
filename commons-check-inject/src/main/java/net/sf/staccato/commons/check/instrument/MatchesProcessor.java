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
import net.sf.staccato.commons.lang.check.annotation.Matches;

import org.apache.commons.lang.NotImplementedException;

/**
 * @author flbulgarelli
 * 
 */
public class MatchesProcessor extends AbstractCheckAnnotationProcessor {

	@Override
	public Class<? extends Annotation> getSupportedAnnotationType() {
		return Matches.class;
	}

	// FIXME improve caching the regexp statically in a class variable
	@Override
	protected String createArgumentCheck(Object annotation,
		AnnotatedArgumentContext context) {
		Matches matches = (Matches) annotation;
		return String.format(
			"matches( \"%s\", %s, \"%s\");",
			parameterName(context.getArgumentNumber(), matches.var()),
			context.getArgumentName(),
			matches.value());
	}

	@Override
	protected String createMethodCheck(Object annotation, AnnotatedMethodContext context) {
		throw new NotImplementedException();
	}

}