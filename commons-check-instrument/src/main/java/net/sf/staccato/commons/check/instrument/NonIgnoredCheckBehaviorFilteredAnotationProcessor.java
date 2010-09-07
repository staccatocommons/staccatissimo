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

import javassist.CtMember;
import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.instrument.processor.AnnotatedArgumentContext;
import net.sf.staccato.commons.instrument.processor.AnnotatedConstructorContext;
import net.sf.staccato.commons.instrument.processor.AnnotatedMethodContext;
import net.sf.staccato.commons.instrument.processor.AnnotationProcessor;
import net.sf.staccato.commons.instrument.processor.FilteredAnnotationProcessor;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.check.annotation.IgnoreChecks;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 */
public class NonIgnoredCheckBehaviorFilteredAnotationProcessor extends
	FilteredAnnotationProcessor {

	public NonIgnoredCheckBehaviorFilteredAnotationProcessor(
		AnnotationProcessor annotationProcessor) {
		super(annotationProcessor);
	}

	private final static class Ignored extends Predicate<Object> {
		public static Evaluable<? super Object> instance = new Ignored();

		@Override
		public boolean eval(Object argument) {
			return (argument instanceof IgnoreChecks);
		}
	}

	// TODO improve performance
	protected boolean canProcess(CtMember member) {
		return !Streams
			.from(member.getAvailableAnnotations())
			.any(Ignored.instance);
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
