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

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

/**
 * @author flbulgarelli
 * 
 */
public class ClassNames {

	public static String getClassName(File classDirectory, File classFile) {
		String classFileAbsolutePath = classFile.getAbsolutePath();
		String classDirectoryAbsolutePath = classDirectory.getAbsolutePath();
		return FilenameUtils //
			.removeExtension(makeRelativeTo(classDirectoryAbsolutePath, classFileAbsolutePath))
			.replace(File.separator, ".");
	}

	private static String makeRelativeTo(String absoluteNormalizedBaseDirectoryName,
		String absoluteNormalizedFileName) {
		return absoluteNormalizedFileName//
			.replaceFirst(Pattern //
				.quote(absoluteNormalizedBaseDirectoryName + File.separator), "");
	}

}
