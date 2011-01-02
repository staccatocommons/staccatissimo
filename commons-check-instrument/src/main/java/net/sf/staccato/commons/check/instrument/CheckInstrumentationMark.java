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
package net.sf.staccato.commons.check.instrument;

import net.sf.staccato.commons.instrument.config.InstrumentationMark;
import net.sf.staccato.commons.instrument.config.SimpleInstrumentationMark;

/**
 * @author flbulgarelli
 * 
 */
public class CheckInstrumentationMark {

	/**
	 * The instrumention mark for commons-check-instrument
	 */
	public static final InstrumentationMark INSTANCE = new SimpleInstrumentationMark(
		"commons-check-instrument",
		"instrumented");

}
