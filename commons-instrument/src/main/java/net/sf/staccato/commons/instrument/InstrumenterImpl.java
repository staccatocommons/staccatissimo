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
import net.sf.staccato.commons.instrument.context.DefaultArgumentAnnotationContext;
import net.sf.staccato.commons.instrument.context.DefaultConstructorAnnotationContext;
import net.sf.staccato.commons.instrument.context.DefaultMethodAnnotationContext;
import net.sf.staccato.commons.instrument.handler.AnnotationHandler;
import net.sf.staccato.commons.instrument.handler.ArgumentAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.ClassAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.ConstructorAnnotationHandler;
import net.sf.staccato.commons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccato.commons.lang.block.Block2;
import net.sf.staccato.commons.lang.check.Validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author flbulgarelli
 */
public class InstrumenterImpl implements InstrumenterConfiguration, Instrumenter {

	private final Logger handlersLogger = LoggerFactory.getLogger("Processor-Logger");

	private final AnnotationProcessor<ClassAnnotationHandler> classProcessor;
	private final AnnotationProcessor<MethodAnnotationHandler> methodProcessor;
	private final AnnotationProcessor<ArgumentAnnotationHandler> argumentProcessor;
	private final AnnotationProcessor<ConstructorAnnotationHandler> constructorProcessor;
	private InstrumentationMark instrumentationMark;

	/**
	 * Creates a new {@link InstrumenterImpl}
	 * 
	 * @param processors
	 */
	public InstrumenterImpl() {
		this.classProcessor = new AnnotationProcessor();
		this.methodProcessor = new AnnotationProcessor();
		this.argumentProcessor = new AnnotationProcessor();
		this.constructorProcessor = new AnnotationProcessor();
	}

	/**
	 * @param instrumentationMark
	 *          the instrumentationMark to set
	 * @return this
	 */
	public InstrumenterConfiguration setInstrumentationMark(InstrumentationMark instrumentationMark) {
		this.instrumentationMark = instrumentationMark;
		return this;
	}

	public InstrumenterConfiguration addAnnotationHanlder(AnnotationHandler handler) {
		if (handler instanceof ClassAnnotationHandler)
			classProcessor.addHandler((ClassAnnotationHandler) handler);

		if (handler instanceof ArgumentAnnotationHandler)
			argumentProcessor.addHandler((ArgumentAnnotationHandler) handler);

		if (handler instanceof ConstructorAnnotationHandler)
			constructorProcessor.addHandler((ConstructorAnnotationHandler) handler);

		if (handler instanceof MethodAnnotationHandler)
			methodProcessor.addHandler((MethodAnnotationHandler) handler);
		return this;
	}

	public void ensureConfigured() {
		Validate.throwing(IllegalStateException.class) //
			.isNotNull("instrumentarionMark", instrumentationMark);
	}

	/**
	 * @param clazz
	 * @throws ClassNotFoundException
	 * @throws CannotCompileException
	 */
	public void instrumentClass(final CtClass clazz) throws CannotCompileException,
		ClassNotFoundException {
		if (clazz.isInterface())
			return;

		if (alreadyProcessed(clazz))
			return;

		classProcessor.processUsing(
			clazz.getAnnotations(),
			new Block2<Object, ClassAnnotationHandler>() {
				protected void softExec(Object annotation, ClassAnnotationHandler handler) throws Exception {
					handler.preProcessAnnotatedClass(annotation);
				}
			});

		for (CtMethod method : clazz.getDeclaredMethods())
			if (!Modifier.isAbstract(method.getModifiers()))
				instrumentMethod(method);

		for (CtConstructor constructor : clazz.getDeclaredConstructors())
			instrumentConstructor(constructor);

		classProcessor.processUsing(
			clazz.getAnnotations(),
			new Block2<Object, ClassAnnotationHandler>() {
				protected void softExec(Object annotation, ClassAnnotationHandler handler) throws Exception {
					handler.postProcessAnnotatedClass(annotation);
				}
			});

		markAsProcessed(clazz);
	}

	private void markAsProcessed(CtClass clazz) {
		clazz.setAttribute(instrumentationMark.getMarkAttributeName(), //
			instrumentationMark.getMarkAttributeValue());
	}

	private boolean alreadyProcessed(CtClass clazz) {
		return clazz.getAttribute(instrumentationMark.getMarkAttributeName()) != null;
	}

	private void instrumentMethod(CtMethod method) throws CannotCompileException,
		ClassNotFoundException {
		final DefaultMethodAnnotationContext methodContext = new DefaultMethodAnnotationContext(
			handlersLogger);
		methodContext.setMethod(method);
		Object[] availableAnnotations = method.getAvailableAnnotations();

		methodProcessor.processUsing(
			availableAnnotations,
			new Block2<Object, MethodAnnotationHandler>() {
				protected void softExec(Object annotation, MethodAnnotationHandler handler)
					throws Exception {
					handler.preProcessAnnotatedMethod(annotation, methodContext);
				}
			});

		instrumentArguments(method);

		methodProcessor.processUsing(
			availableAnnotations,
			new Block2<Object, MethodAnnotationHandler>() {
				protected void softExec(Object annotation, MethodAnnotationHandler handler)
					throws Exception {
					handler.postProcessAnnotatedMethod(annotation, methodContext);
				}
			});
	}

	/**
	 * @param constructor
	 * @throws CannotCompileException
	 * @throws ClassNotFoundException
	 */
	private void instrumentConstructor(CtConstructor constructor) throws CannotCompileException,
		ClassNotFoundException {
		final DefaultConstructorAnnotationContext context = new DefaultConstructorAnnotationContext(
			handlersLogger);
		context.setConstructor(constructor);
		Object[] availableAnnotations = constructor.getAvailableAnnotations();

		constructorProcessor.processUsing(
			availableAnnotations,
			new Block2<Object, ConstructorAnnotationHandler>() {
				protected void softExec(Object annotation, ConstructorAnnotationHandler handler)
					throws Exception {
					handler.preProcessAnnotatedConstructor(annotation, context);
				}
			});

		instrumentArguments(constructor);

		constructorProcessor.processUsing(
			availableAnnotations,
			new Block2<Object, ConstructorAnnotationHandler>() {
				protected void softExec(Object annotation, ConstructorAnnotationHandler handler)
					throws Exception {
					handler.postProcessAnnotatedConstructor(annotation, context);
				}
			});
	}

	private void instrumentArguments(CtBehavior behaviour) throws CannotCompileException {
		Object[][] parameterAnnotations = behaviour.getAvailableParameterAnnotations();
		final DefaultArgumentAnnotationContext argumentContext = new DefaultArgumentAnnotationContext(
			handlersLogger);
		argumentContext.setBehavior(behaviour);
		for (int i = 0; i < parameterAnnotations.length; i++) {
			argumentContext.setParameterNumber(i);
			argumentProcessor.processUsing(
				parameterAnnotations[i],
				new Block2<Object, ArgumentAnnotationHandler>() {
					protected void softExec(Object annotation, ArgumentAnnotationHandler handler)
						throws Exception {
						handler.processAnnotatedArgument(annotation, argumentContext);
					}
				});
		}
	}

}