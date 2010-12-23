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
package net.sf.staccato.commons.instrument.context;

import javassist.CtBehavior;

/**
 * The context of an annotated argument
 * 
 * @author flbulgarelli
 * 
 */
public interface ArgumentAnnotationContext extends AnnotationContext {

	/**
	 * @return the behavior whose parameter is annotated
	 */
	CtBehavior getArgumentBehavior();

	/**
	 * @return if the annotated argument is a constructor or method parameter
	 */
	boolean isConstructorArgument();

	/**
	 * @return the annotated argument number, zero based
	 */
	int getArgumentNumber();

	/**
	 * @return the annotated argument name
	 */
	String getArgumentIdentifier();

}