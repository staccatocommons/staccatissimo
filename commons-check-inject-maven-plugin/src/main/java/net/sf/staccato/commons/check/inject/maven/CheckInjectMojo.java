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
package net.sf.staccato.commons.check.inject.maven;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.staccato.commons.check.inject.runner.Runner;
import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.lang.SoftException;
import net.sf.staccato.commons.lang.function.Function;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author flbulgarelli
 * @goal inject
 */
public class CheckInjectMojo extends AbstractMojo {

	/**
	 * @required
	 * @parameter expression="${inject.location}"
	 *            default-value="${project.build.directory}/classes"
	 */
	private String location;

	/**
	 * @readonly
	 * @required
	 * @parameter expression="${plugin.artifacts}"
	 */
	protected List<Artifact> pluginArtifactsList;

	/**
	 * @required
	 * @parameter
	 */
	protected List<String> processorClassnames;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Injecting checks to classes located in " + location);
		String extraClasspath = createClassPathString();
		getLog().debug("Using classpath " + extraClasspath);
		try {
			new Runner(location, processorClassnames, extraClasspath).run();
		} catch (Exception e) {
			getLog().error(e.getMessage());
			throw new MojoExecutionException("Unexpected error", e);
		}
		getLog().info("Checks injected sucessfully");
	}

	private String createClassPathString() {
		return StringUtils.join(
			Streams.from(pluginArtifactsList).map(new Function<Artifact, String>() {
				public String apply(Artifact arg) {
					try {
						return arg.getFile().getCanonicalPath();
					} catch (IOException e) {
						throw SoftException.soften(e);
					}
				}
			}).iterator(),
			File.pathSeparator);
	}

}
