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
package net.sf.staccatocommons.check.instrument;

import java.util.Arrays;

import net.sf.staccatocommons.instrument.config.InstrumenterConfiguration;
import net.sf.staccatocommons.instrument.config.InstrumenterConfigurer;

/**
 * @author flbulgarelli
 * 
 */
public class CheckConfigurer implements InstrumenterConfigurer {

	private boolean ignoreReturns;

	/**
	 * Creates a new {@link CheckConfigurer}
	 */
	public CheckConfigurer(boolean ignoreReturns) {
		this.ignoreReturns = ignoreReturns;
	}

	public void configureInstrumenter(InstrumenterConfiguration instrumenter) {

		IgnoreCheckHandler ignoreCheckHandler = new IgnoreCheckHandler();
		ForceChecksHandler forceCheckHandler = new ForceChecksHandler();
		instrumenter.addAnnotationHanlder(ignoreCheckHandler).addAnnotationHanlder(forceCheckHandler);
		for (AbstractCheckAnnotationHandler<?> handler : Arrays.asList(
			new NotNullHandler(ignoreReturns),
			new SizeHandler(ignoreReturns),
			new NotEmptyHandler(ignoreReturns),
			new PositiveHandler(ignoreReturns),
			new MatchesHandler(ignoreReturns),
			new NotNegativeHandler(ignoreReturns))) {
			ignoreCheckHandler.addDeactivable(handler);
			forceCheckHandler.addDeactivable(handler);
			instrumenter.addAnnotationHanlder(handler);
		}
		instrumenter.setInstrumentationMark(CheckInstrumentationMark.INSTANCE);
	}
}
