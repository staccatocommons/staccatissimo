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
package net.sf.staccato.commons.instrument.internal;

import javassist.CtBehavior;
import javassist.Modifier;
import net.sf.staccato.commons.instrument.processor.AnnotatedArgumentContext;
import net.sf.staccato.commons.instrument.processor.AnnotatedConstructorContext;
import net.sf.staccato.commons.instrument.processor.AnnotatedMethodContext;
import net.sf.staccato.commons.instrument.processor.AnnotationProcessor;
import net.sf.staccato.commons.instrument.processor.FilteredAnnotationProcessor;

/**
 * @author flbulgarelli
 * 
 */
public class PublicBehaviourFilteredAnnotationProcessor extends
	FilteredAnnotationProcessor {

	/**
	 * Creates a new {@link PublicBehaviourFilteredAnnotationProcessor}
	 */
	public PublicBehaviourFilteredAnnotationProcessor(
		AnnotationProcessor annotationProcessor) {
		super(annotationProcessor);
	}

	protected boolean canProcess(CtBehavior argument) {
		return Modifier.isPublic(argument.getModifiers());
	}

	@Override
	protected boolean canProcessAnnotatedMethod(Object annotation,
		AnnotatedMethodContext context) {
		return canProcess(context.getMethod());
	}

	@Override
	protected boolean canProcessAnnotatedConstructor(Object annotation,
		AnnotatedConstructorContext context) {
		return canProcess(context.getConstructor());
	}

	@Override
	protected boolean canProcessAnnotatedArgument(Object annotation,
		AnnotatedArgumentContext context) {
		return canProcess(context.getArgumentBehavior());
	}

}
