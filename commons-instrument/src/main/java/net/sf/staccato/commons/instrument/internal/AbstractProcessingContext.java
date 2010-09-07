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
package net.sf.staccato.commons.instrument.internal;

import java.util.Set;

import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.instrument.processor.AnnotatedContext;
import net.sf.staccato.commons.lang.function.Function;

import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class AbstractProcessingContext implements AnnotatedContext {

	private final Logger logger;

	private Set<String> presentAnnotations;

	public AbstractProcessingContext(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	@Override
	public void logDebugMessage(String message, Object... arguments) {
		getLogger().debug(message, arguments);
	}

	@Override
	public void logInfoMessage(String message, Object... arguments) {
		getLogger().info(message, arguments);
	}

	@Override
	public void logWarnMessage(String message, Object... arguments) {
		getLogger().warn(message, arguments);
	}

	@Override
	public void logErrorMessage(String message, Object... arguments) {
		getLogger().error(message, arguments);
	}

	/**
	 * @return the presentAnnotations
	 */
	public Set<String> getPresentAnnotationsTypes(Object[] annotations) {
		if (presentAnnotations == null) {
			presentAnnotations = Streams
				.from(annotations)
				.map(new Function<Object, String>() {
					public String apply(Object arg) {
						return arg.getClass().getName();
					}
				})
				.toSet();
		}
		return presentAnnotations;
	}
}
