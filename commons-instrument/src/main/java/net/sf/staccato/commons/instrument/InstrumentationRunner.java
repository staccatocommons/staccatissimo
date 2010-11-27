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

import java.io.File;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.sf.staccato.commons.instrument.config.InstrumenterConfigurer;
import net.sf.staccato.commons.instrument.internal.ClassNames;
import net.sf.staccato.commons.instrument.internal.Instrumenter;
import net.sf.staccato.commons.instrument.internal.InstrumenterImpl;
import net.sf.staccato.commons.io.Directory;
import net.sf.staccato.commons.lang.block.Block2;

/**
 * @author flbulgarelli
 */
public class InstrumentationRunner {

	private InstrumentationRunner() {
	}

	public static void runInstrumentation(InstrumenterConfigurer configurer,
		Directory processDirectory, String extraPath) throws Exception {
		InstrumenterImpl instrumenter = new InstrumenterImpl();
		configurer.configureInstrumenter(instrumenter);
		new InstrumentationContext(instrumenter, processDirectory, extraPath).doInstrument();
	}

	private static class InstrumentationContext {
		public Directory processDirectory;
		public ClassPool classPool;
		public Instrumenter classInstrumenter;

		private InstrumentationContext(Instrumenter classInstrumenter, Directory processDirectory,
			String extraPath) throws NotFoundException {
			this.classInstrumenter = classInstrumenter;
			this.processDirectory = processDirectory;
			this.classPool = ClassPool.getDefault();
			this.classPool.appendPathList(extraPath);
			this.classPool.appendClassPath(processDirectory.getAbsolutePath());
		}

		private void processAndWriteClass(File baseDir, File classfile) throws Exception {
			CtClass clazz = classPool.get(ClassNames.getClassName(baseDir, classfile));
			classInstrumenter.instrumentClass(clazz);
			clazz.detach();
			if (clazz.isModified())
				clazz.writeFile(processDirectory.getAbsolutePath());
		}

		private void doInstrument() {
			processDirectory.forEachFileRecursively(new Block2<File, File>() {
				public void softExec(File baseDir, File classfile) throws Exception {
					processAndWriteClass(baseDir, classfile);
				}
			});
		}
	}

}
