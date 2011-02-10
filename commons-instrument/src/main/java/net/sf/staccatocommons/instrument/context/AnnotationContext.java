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
package net.sf.staccatocommons.instrument.context;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Base interface for an annotated element context
 * 
 * @author flbulgarelli
 * 
 */
public interface AnnotationContext {

	/**
	 * @param message
	 * @param arguments
	 */
	void logDebugMessage(String message, Object... arguments);

	/**
	 * @param message
	 * @param arguments
	 */
	void logInfoMessage(String message, Object... arguments);

	/**
	 * @param message
	 * @param arguments
	 */
	void logWarnMessage(String message, Object... arguments);

	/**
	 * @param message
	 * @param arguments
	 */
	void logErrorMessage(String message, Object... arguments);

	/**
	 * Answers the class were the annotated element is declared. If the annotated
	 * element is already a top level or anonymous class, returns it.
	 * 
	 * @return the declaring class of the annotated element
	 * @throws NotFoundException
	 *           if such class is not available
	 */
	@NonNull
	CtClass getDeclaringClass() throws NotFoundException;

	/**
	 * Returns the class pool in use by the instrumenter
	 * 
	 * @return a {@link ClassPool}
	 */
	@NonNull
	ClassPool getClassPool();

	/** Shortcut for <code>getClassPoll().get(className)</code> **/
	@NonNull
	CtClass getClass(@NonNull String className) throws NotFoundException;

	/**
	 * Returns the type of the element annotated:
	 * <ul>
	 * <li>When the annotated element is a class, the type is that class</li>
	 * <li>When the annotated element is a constructor, the type is the class that
	 * declared such constructor</li>
	 * <li>When the annotated element is a method, the type is the return type of
	 * the method</li>
	 * <li>Then the annotated element is an argument, the type is argument type</li>
	 * <ul>
	 * 
	 * @return a {@link CtClass} that represents the type of the annotated element
	 * @throws NotFoundException
	 *           if such type is not available
	 */
	@NonNull
	CtClass getElementType() throws NotFoundException;

}
