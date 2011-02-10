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
package net.sf.staccatocommons.instrument.config;

import net.sf.staccatocommons.instrument.handler.AnnotationHandler;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Instrumenter's interface for configuring it.
 * <p>
 * It is not intended to be implemented by clients. Instead, client code will
 * have access to an implementation of {@link InstrumenterConfiguration} only
 * when executing
 * {@link InstrumenterConfigurer#configureInstrumenter(InstrumenterConfiguration)}
 * </p>
 * 
 * @author flbulgarelli
 * @see InstrumenterConfigurer
 */
public interface InstrumenterConfiguration {

	/**
	 * Sets the instrumentation mark this instrumenter will print in the
	 * instrumented classes.
	 * 
	 * This message <strong>must</strong> be sent at least once from inside
	 * {@link InstrumenterConfigurer#configureInstrumenter(InstrumenterConfiguration)}
	 * , and <strong>should</strong> be sent at most once from inside it.
	 * 
	 * @param instrumentationMark
	 *          the instrumentationMark to set
	 * @return this in order to allow method chaining
	 */
	@NonNull
	InstrumenterConfiguration setInstrumentationMark(@NonNull InstrumentationMark instrumentationMark);

	/**
	 * Register an annotation handler to this instrumenter that will be notified
	 * to process an annotation each time one of the type returned by
	 * {@link AnnotationHandler#getSupportedAnnotationType()} is found.
	 * 
	 * If more than one {@link AnnotationHandler} that supports a certain
	 * annotation type is registered, the instrumenter will notify all those
	 * handlers when an annotation of such type is found, in the order they have
	 * being registered.
	 * 
	 * This message <strong>must</strong> be sent at least once from inside
	 * {@link InstrumenterConfigurer#configureInstrumenter(InstrumenterConfiguration)}
	 * 
	 * @param handler
	 *          the handler to register
	 * @return this in order to allow method chaining
	 */
	@NonNull
	InstrumenterConfiguration addAnnotationHanlder(@NonNull AnnotationHandler<?> handler);

}