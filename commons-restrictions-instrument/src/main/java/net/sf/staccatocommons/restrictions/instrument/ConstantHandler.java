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
package net.sf.staccatocommons.restrictions.instrument;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import net.sf.staccatocommons.instrument.context.MethodAnnotationContext;
import net.sf.staccatocommons.instrument.handler.MethodAnnotationHandler;
import net.sf.staccatocommons.instrument.handler.deactivator.Deactivable;
import net.sf.staccatocommons.instrument.handler.deactivator.StackedDeactivableSupport;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 */
public class ConstantHandler implements MethodAnnotationHandler<Constant>, Deactivable {

	private static final String METHOD_TEMPLATE = "return %s;";
	private static final String FIELD_TEMPLATE = "public static final %s %s;";
	private static final String INITIALIZER_NAME_TEMPLATE = "%sInitializer";
	private StackedDeactivableSupport deactivableSupport = new StackedDeactivableSupport();

	public Class<Constant> getSupportedAnnotationType() {
		return Constant.class;
	}

	public void preProcessAnnotatedMethod(Constant annotation, MethodAnnotationContext context)
		throws CannotCompileException {}

	public void postProcessAnnotatedMethod(Constant annotation, MethodAnnotationContext context)
		throws CannotCompileException, NotFoundException {

		if (!deactivableSupport.isActive())
			return;

		CtMethod originalMethod = context.getMethod();
		if (!Modifier.isStatic(originalMethod.getModifiers())) {
			context.logInfoMessage("{}: this processor does not support @Constant processing "
				+ "on non static methods", context.getMethod().getLongName());
			return;
		}

		if (originalMethod.getParameterTypes().length > 0) {
			context.logInfoMessage("{}: has one or more parameters. Not processing", context
				.getMethod()
				.getLongName());
			return;
		}

		CodeAttribute codeAttribute = originalMethod.getMethodInfo().getCodeAttribute();
		if (codeAttribute == null || codeAttribute.getMaxStack() < 2) {
			context.logInfoMessage("{}: may be already constant. Not processing", context
				.getMethod()
				.getLongName());
			return;
		}

		CtClass clazz = originalMethod.getDeclaringClass();
		String methodName = originalMethod.getName();
		String fieldType = originalMethod.getReturnType().getName();
		String fieldName = toJavaConstantString(methodName);
		CtField field = CtField.make(String.format(FIELD_TEMPLATE, fieldType, fieldName), clazz);

		CtMethod initializer = CtNewMethod.copy(
			originalMethod,
			String.format(INITIALIZER_NAME_TEMPLATE, methodName),
			clazz,
			null);
		initializer.setModifiers(Modifier.PRIVATE | Modifier.STATIC);
		clazz.addMethod(initializer);
		clazz.addField(field, Initializer.byCall(clazz, initializer.getName()));

		originalMethod.setBody(String.format(METHOD_TEMPLATE, fieldName));
	}

	public final void deactivate() {
		deactivableSupport.deactivate();
	}

	public final void activate() {
		deactivableSupport.activate();
	}

	/** Converts a method name into a constant name */
	public static String toJavaConstantString(String original) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i != 0) {
					sb.append('_');
				}
				sb.append(c);
			} else {
				sb.append(Character.toUpperCase(c));
			}
			if (sb.length() > 4 && sb.substring(0, 4).equals("GET_")) {
				sb.replace(0, 4, "");
			}
			if (sb.length() > 3 && sb.substring(0, 3).equals("IS_")) {
				sb.replace(0, 3, "");
			}
		}
		return sb.toString();
	}
}
