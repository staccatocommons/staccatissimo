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
package net.sf.staccato.commons.check.inject.runner;

import java.util.Arrays;
import java.util.List;

import javassist.NotFoundException;
import net.sf.staccato.commons.instrument.AnnotationProcessor;
import net.sf.staccato.commons.instrument.AnnotationsParser;
import net.sf.staccato.commons.instrument.ClassPathInstrumenter;
import net.sf.staccato.commons.instrument.internal.ClassNames;
import net.sf.staccato.commons.io.Directory;

import org.apache.commons.lang.StringUtils;

/**
 * Check-Inject tool entry point.
 * 
 * @author flbulgarelli
 */
public class Runner {

	private final ClassPathInstrumenter classPathInstrumenter;

	public Runner(String injectionDirectoryName,
		List<String> processorsClassNames, String extraPath)
		throws NotFoundException {
		classPathInstrumenter = new ClassPathInstrumenter(//
			new AnnotationsParser(//
				ClassNames.<AnnotationProcessor> //
					newInstanceFromClassNames(processorsClassNames)),
			new Directory(injectionDirectoryName),
			extraPath);
	}

	public void run() throws Exception {
		classPathInstrumenter.instrument();
	}

	// TODO improve cli handling
	public static void main(String[] args) throws Exception {
		new Runner(
			args[0],
			Arrays.asList(StringUtils.split(args[1], ",")),
			args.length > 2 ? args[2] : "").run();
	}
}
