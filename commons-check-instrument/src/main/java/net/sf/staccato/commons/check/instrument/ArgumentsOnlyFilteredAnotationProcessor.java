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

import net.sf.staccato.commons.instrument.processor.AnnotatedArgumentContext;
import net.sf.staccato.commons.instrument.processor.AnnotatedConstructorContext;
import net.sf.staccato.commons.instrument.processor.AnnotatedMethodContext;
import net.sf.staccato.commons.instrument.processor.AnnotationProcessor;
import net.sf.staccato.commons.instrument.processor.FilteredAnnotationProcessor;

/**
 * @author flbulgarelli
 * 
 */
public class ArgumentsOnlyFilteredAnotationProcessor extends
	FilteredAnnotationProcessor {

	public ArgumentsOnlyFilteredAnotationProcessor(
		AnnotationProcessor annotationProcessor) {
		super(annotationProcessor);
	}

	@Override
	protected boolean canProcessAnnotatedMethod(Object annotation,
		AnnotatedMethodContext context) {
		return false;
	}

	@Override
	protected boolean canProcessAnnotatedConstructor(Object annotation,
		AnnotatedConstructorContext context) {
		return false;
	}

	@Override
	protected boolean canProcessAnnotatedArgument(Object annotation,
		AnnotatedArgumentContext context) {
		return true;
	}
}
