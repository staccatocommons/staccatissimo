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

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.instrument.InstrumentationRunner;

/**
 * Interface for configuring an instrumenter.
 * <p>
 * It declares a single message
 * {@link #configureInstrumenter(InstrumenterConfiguration)} that will send by
 * the {@link InstrumentationRunner} after it has created the instrumenter, and
 * before running the instrumentation
 * </p>
 * 
 * 
 * @author flbulgarelli
 * 
 */
public interface InstrumenterConfigurer {

	/**
	 * Configures the given <code>instrumenter</code>.
	 * <p>
	 * Implementors <strong>must</strong> set an instrumentation mark to the
	 * instrumenter and add at least one handler to it
	 * </p>
	 * 
	 * @param instrumenter
	 *          the instrumenter to configure
	 */
	void configureInstrumenter(@NonNull InstrumenterConfiguration instrumenter);

}
