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
package net.sf.staccatocommons.instrument.handler;

import java.lang.annotation.Annotation;

import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * Superinterface for all annotation handlers.
 * <p>
 * Although a concrete handler <strong>may</strong> implement more than one
 * handler interfaces, in order to be capable of processing more than one
 * element type, this interface reinforce that a handler <strong>must</strong>
 * process a single annotation type
 * </p>
 * <p>
 * As instrumentation is performed on a single thread, {@link AnnotationHandler}
 * s have no restrictions regarding synchronization. In particular, they do not
 * need to be statless nor immutable.
 * </p>
 * 
 * @author flbulgarelli
 * @see ArgumentAnnotationHandler
 * @see MethodAnnotationHandler
 * @see ConstructorAnnotationHandler
 * @see ClassAnnotationHandler
 * 
 */
public interface AnnotationHandler<A extends Annotation> {

	/**
	 * Answers the annotation this processor can process
	 * 
	 * @return an annotation type
	 */
	@NonNull
	Class<A> getSupportedAnnotationType();

}
