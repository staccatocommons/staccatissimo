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
package net.sf.staccatocommons.instrument.context.internal;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.instrument.context.ClassAnnotationContext;
import net.sf.staccatocommons.lang.Nulls;
import net.sf.staccatocommons.lang.SoftException;

import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultClassAnnotationContext extends AbstractAnnotationContext implements
	ClassAnnotationContext {

	private final CtClass clazz;

	/**
	 * Creates a new {@link DefaultClassAnnotationContext}
	 */
	public DefaultClassAnnotationContext(@NonNull ClassPool pool, @NonNull Logger logger,
		@NonNull CtClass clazz) {
		super(pool, logger);
		Ensure.isNotNull("clazz", clazz);
		this.clazz = clazz;
	}

	public CtClass getAnnotatedClass() {
		return clazz;
	}

	public CtClass getDeclaringClass() {
		try {
			return Nulls.coalesce(clazz.getDeclaringClass(), clazz);
		} catch (NotFoundException e) {
			throw SoftException.soften(e);
		}
	}

}
