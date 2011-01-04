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
package net.sf.staccato.commons.instrument.examples;

import java.math.BigDecimal;

import net.sf.staccato.commons.io.Directory;
import net.sf.staccatocommons.instrument.InstrumentationRunner;
import net.sf.staccatocommons.instrument.config.InstrumenterConfiguration;
import net.sf.staccatocommons.instrument.config.InstrumenterConfigurer;
import net.sf.staccatocommons.instrument.config.SimpleInstrumentationMark;
import net.sf.staccatocommons.instrument.examples.Account2;
import net.sf.staccatocommons.instrument.examples.LogHandler;
import net.sf.staccatocommons.instrument.examples.NonNegativeDecimalHandler;

/**
 * @author flbulgarelli
 * 
 */
public class Test {

	@org.junit.BeforeClass
	public static void before() throws Exception {
		InstrumentationRunner.runInstrumentation(new InstrumenterConfigurer() {
			public void configureInstrumenter(InstrumenterConfiguration instrumenter) {
				instrumenter //
					.addAnnotationHanlder(new LogHandler())
					.addAnnotationHanlder(new NonNegativeDecimalHandler())
					.setInstrumentationMark(new SimpleInstrumentationMark("log-example", "instrumented-1.0"));
			}
		},
			new Directory("target/classes"),
			"");
	}

	@org.junit.Test(expected = IllegalArgumentException.class)
	public void testNegativeAmmount() throws Exception {
		Account2 account2 = new Account2(BigDecimal.valueOf(10));
		account2.resetBalance();
		account2.deposit(BigDecimal.valueOf(-10));
	}
}
