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
package net.sf.staccatocommons.instrument.handler.deactivator;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.LinkedList;

import net.sf.staccatocommons.instrument.handler.AnnotationHandler;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractActivationAnnotationHandler<A extends Annotation> implements
	AnnotationHandler<A> {

	private final Collection<Deactivable> handlers = new LinkedList<Deactivable>();

	public <T extends Deactivable> T addDeactivable(T deactivable) {
		handlers.add(deactivable);
		return deactivable;
	}

	protected void deactivateAll() {
		for (Deactivable deactivable : handlers)
			deactivable.deactivate();
	}

	protected void activateAll() {
		for (Deactivable deactivable : handlers)
			deactivable.activate();
	}

}
