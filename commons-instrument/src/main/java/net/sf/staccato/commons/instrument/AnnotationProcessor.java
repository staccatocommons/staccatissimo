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

import java.util.Collection;
import java.util.LinkedList;

import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.instrument.handler.AnnotationHandler;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.block.Block2;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 */
public class AnnotationProcessor<T extends AnnotationHandler> {

	private final Collection<T> handlers;

	public AnnotationProcessor() {
		this.handlers = new LinkedList<T>();
	}

	public void processUsing(final Object[] annotations, Block2<Object, T> block) {
		for (Object annotation : annotations)
			getHandler(annotation).ifDefined(block.apply(annotation));
	}

	private Option<T> getHandler(final Object annotation) {
		return Streams.from(handlers).findOrNone(//
			new Predicate<T>() {
				public boolean eval(T argument) {
					return argument.getSupportedAnnotationType().isAssignableFrom(annotation.getClass());
				}
			});
	}

	public void addHandler(T handler) {
		this.handlers.add(handler);
	}

}
