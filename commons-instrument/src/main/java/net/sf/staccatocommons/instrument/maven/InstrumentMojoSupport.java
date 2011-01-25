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
package net.sf.staccatocommons.instrument.maven;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.instrument.InstrumentationRunner;
import net.sf.staccatocommons.instrument.config.InstrumenterConfigurer;
import net.sf.staccatocommons.io.Directory;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.predicate.Predicates;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author flbulgarelli
 * 
 */
public abstract class InstrumentMojoSupport {

	private final String location;

	private final Artifact artifact;

	private final List<Artifact> pluginArtifactsList;

	private final Mojo mojo;

	/**
	 * Creates a new {@link InstrumentMojoSupport}
	 */
	public InstrumentMojoSupport(@NonNull Mojo mojo, @NonNull String location,
		@NonNull Artifact artifact, List<Artifact> pluginArtifactsList) {
		Ensure.isNotNull("mojo", mojo);
		Ensure.isNotNull("location", location);
		Ensure.isNotNull("artifact", artifact);
		Ensure.isNotNull("pluginArtifactsList", pluginArtifactsList);
		this.mojo = mojo;
		this.location = location;
		this.artifact = artifact;
		this.pluginArtifactsList = pluginArtifactsList;
	}

	/**
	 * Executes the mojo
	 * 
	 * @throws MojoExecutionException
	 * @throws MojoFailureException
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		mojo.getLog().info("Instrumenting checks to classes located in " + location);
		String extraClasspath = createClassPathString();
		mojo.getLog().debug("Using classpath " + extraClasspath);
		try {
			InstrumentationRunner.runInstrumentation(//
				createConfigurer(),
				new Directory(location),
				extraClasspath);
		} catch (Exception e) {
			mojo.getLog().error(e.getMessage());
			throw new MojoExecutionException("Unexpected error", e);
		}
		mojo.getLog().info("Checks instrumented sucessfully");
	}

	private String createClassPathString() {
		return Streams //
			.from(pluginArtifactsList)
			.filter(Predicates.equal(artifact).not())
			.map(new Function<Artifact, String>() {
				public String apply(Artifact arg) {
					try {
						return arg.getFile().getCanonicalPath();
					} catch (IOException e) {
						throw SoftException.soften(e);
					}
				}
			})
			.joinStrings(File.pathSeparator);
	}

	/**
	 * Create the instrumenter configurer
	 */
	@NonNull
	protected abstract InstrumenterConfigurer createConfigurer();

}
