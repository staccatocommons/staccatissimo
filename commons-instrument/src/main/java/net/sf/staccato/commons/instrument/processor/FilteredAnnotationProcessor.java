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
package net.sf.staccato.commons.instrument.processor;

import java.lang.annotation.Annotation;


/**
 * @author flbulgarelli
 * 
 */
public abstract class FilteredAnnotationProcessor implements
	AnnotationProcessor {

	private final AnnotationProcessor annotationProcessor;

	public FilteredAnnotationProcessor(AnnotationProcessor annotationProcessor) {
		this.annotationProcessor = annotationProcessor;
	}

	public Class<? extends Annotation> getSupportedAnnotationType() {
		return annotationProcessor.getSupportedAnnotationType();
	}

	/**
	 * @param annotation
	 * @param context
	 * @see net.sf.staccato.commons.instrument.processor.AnnotationProcessor#processAnnotatedMethod(java.lang.Object,
	 *      net.sf.staccato.commons.instrument.processor.AnnotatedMethodContext)
	 */
	public void processAnnotatedMethod(Object annotation,
		AnnotatedMethodContext context) {
		if (canProcessAnnotatedMethod(annotation, context))
			annotationProcessor.processAnnotatedMethod(annotation, context);
	}

	/**
	 * @param annotation
	 * @param context
	 * @return
	 */
	protected abstract boolean canProcessAnnotatedMethod(Object annotation,
		AnnotatedMethodContext context);

	/**
	 * @param annotation
	 * @param context
	 * @see net.sf.staccato.commons.instrument.processor.AnnotationProcessor#processAnnotatedConstructor(java.lang.Object,
	 *      net.sf.staccato.commons.instrument.processor.AnnotatedConstructorContext)
	 */
	public void processAnnotatedConstructor(Object annotation,
		AnnotatedConstructorContext context) {
		if (canProcessAnnotatedConstructor(annotation, context))
			annotationProcessor.processAnnotatedConstructor(annotation, context);
	}

	/**
	 * @param annotation
	 * @param context
	 * @return
	 */
	protected abstract boolean canProcessAnnotatedConstructor(Object annotation,
		AnnotatedConstructorContext context);

	/**
	 * @param annotation
	 * @param context
	 * @see net.sf.staccato.commons.instrument.processor.AnnotationProcessor#processAnnotatedArgument(java.lang.Object,
	 *      net.sf.staccato.commons.instrument.processor.AnnotatedArgumentContext)
	 */
	public void processAnnotatedArgument(Object annotation,
		AnnotatedArgumentContext context) {
		if (canProcessAnnotatedArgument(annotation, context))
			annotationProcessor.processAnnotatedArgument(annotation, context);
	}

	/**
	 * @param annotation
	 * @param context
	 * @return
	 */
	protected abstract boolean canProcessAnnotatedArgument(Object annotation,
		AnnotatedArgumentContext context);

}
