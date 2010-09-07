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
package net.sf.staccato.commons.instrument;

import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.Modifier;
import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.instrument.internal.DefaultAnnotatedMethodContext;
import net.sf.staccato.commons.instrument.internal.DefaultArgumentContext;
import net.sf.staccato.commons.instrument.internal.DefaultConstructorContext;
import net.sf.staccato.commons.instrument.processor.AnnotationProcessor;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.block.Block;
import net.sf.staccato.commons.lang.predicate.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author flbulgarelli
 */
public class DefaultClassInstrumenter implements ClassInstrumenter {

	private final Iterable<AnnotationProcessor> processors;

	private final Logger processorsLogger = LoggerFactory
		.getLogger("Processor-Logger");

	private final DefaultArgumentContext argumentContext = new DefaultArgumentContext(
		processorsLogger);

	private final DefaultAnnotatedMethodContext methodContext = new DefaultAnnotatedMethodContext(
		processorsLogger);

	private DefaultConstructorContext constructorContext = new DefaultConstructorContext(
		processorsLogger);

	public DefaultClassInstrumenter(Iterable<AnnotationProcessor> processors) {
		this.processors = processors;
	}

	/**
	 * @param clazz
	 * @throws ClassNotFoundException
	 * @throws CannotCompileException
	 */
	public void instrumentClass(CtClass clazz) throws CannotCompileException,
		ClassNotFoundException {

		if (clazz.isInterface())
			return;

		for (CtMethod method : clazz.getDeclaredMethods())
			if (!Modifier.isAbstract(method.getModifiers()))
				instrumentMethod(method);

		for (CtConstructor constructor : clazz.getDeclaredConstructors())
			instrumentConstructor(constructor);
	}

	private void instrumentMethod(CtMethod method) throws CannotCompileException,
		ClassNotFoundException {
		instrumentArguments(method);
		methodContext.setMethod(method);
		for (final Object annotation : method.getAvailableAnnotations())
			processUsing(annotation, new Block<AnnotationProcessor>() {
				public void exec(AnnotationProcessor argument) {
					argument.processAnnotatedMethod(annotation, methodContext);
				}
			});
	}

	/**
	 * @param constructor
	 * @throws CannotCompileException
	 * @throws ClassNotFoundException
	 */
	private void instrumentConstructor(CtConstructor constructor)
		throws CannotCompileException, ClassNotFoundException {
		instrumentArguments(constructor);
		constructorContext.setConstructor(constructor);
		for (final Object annotation : constructor.getAvailableAnnotations())
			processUsing(annotation, new Block<AnnotationProcessor>() {
				public void exec(AnnotationProcessor argument) {
					argument.processAnnotatedConstructor(annotation, constructorContext);
				}
			});
	}

	private void instrumentArguments(CtBehavior behaviour)
		throws CannotCompileException {
		Object[][] parameterAnnotations = behaviour
			.getAvailableParameterAnnotations();
		argumentContext.setBehavior(behaviour);
		for (int i = 0; i < parameterAnnotations.length; i++)
			for (final Object annotation : parameterAnnotations[i]) {
				argumentContext.setParameterNumber(i);
				processUsing(annotation, new Block<AnnotationProcessor>() {
					public void exec(AnnotationProcessor argument) {
						argument.processAnnotatedArgument(annotation, argumentContext);
					}
				});
			}
	}

	private void processUsing(final Object annotation,
		Executable<AnnotationProcessor> block) {
		Streams.from(processors).findOrNone(//
			new Predicate<AnnotationProcessor>() {
				@Override
				public boolean eval(AnnotationProcessor argument) {
					return argument.getSupportedAnnotationType().isAssignableFrom(
						annotation.getClass());
				}
			})
			.ifDefined(block);
	}

}
