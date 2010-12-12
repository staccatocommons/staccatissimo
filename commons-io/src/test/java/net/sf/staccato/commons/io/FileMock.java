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
package net.sf.staccato.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * A dummy, read only, unexecutable, existent {@link File} mock
 * 
 * Methods supported are:
 * <ul>
 * <li>{@link #exists()} : returns <code>true</code></li>
 * <li>{@link #listFiles()} : returns a provided list of files, if mock was
 * created as a file. Otherwise, <code>null</code>, in order to be consistent
 * with File interface</li>
 * <li>{@link #isDirectory()} : returns if the mock was created as a file</li>
 * <li>{@link #isFile()} : returns !{@link #isDirectory()}</li>
 * <li>{@link #canExecute()} : returns <code>false</code></li>
 * <li>{@link #canRead()} : returns <code>true</code></li>
 * <li>{@link #canWrite()} : returns <code>false</code></li>
 * <li>{@link #getName()} : same behavior of {@link File}</li>
 * <li>{@link #getPath()} : same behavior of {@link File}</li>
 * <li>{@link #toString()} : same behavior of {@link File}</li>
 * </ul>
 * 
 * @author flbulgarelli
 * 
 */
public class FileMock extends File {
	private static final long serialVersionUID = -4997788227815563688L;
	private final File[] contents;

	public FileMock(String pathname, File... contents) {
		super(pathname);
		this.contents = contents;
	}

	public boolean exists() {
		return true;
	}

	public File[] listFiles() {
		return contents;
	}

	public boolean isDirectory() {
		return contents != null;
	}

	public boolean canExecute() {
		return false;
	}

	public boolean canRead() {
		return true;
	}

	public boolean canWrite() {
		return true;
	}

	public boolean isFile() {
		return !isDirectory();
	}

	public static FileMock dir(String pathname, File... contents) {
		return new FileMock(pathname, contents);
	}

	public static FileMock file(String pathname) {
		return new FileMock(pathname, (File[]) null);
	}

	public String getAbsolutePath() {
		// TODO Auto-generated method stub
		return super.getAbsolutePath();
	}

	public File getCanonicalFile() throws IOException {
		// TODO Auto-generated method stub
		return super.getCanonicalFile();
	}

	public String getCanonicalPath() throws IOException {
		// TODO Auto-generated method stub
		return super.getCanonicalPath();
	}

	public String getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}

	public File getParentFile() {
		// TODO Auto-generated method stub
		return super.getParentFile();
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public String[] list() {
		// TODO Auto-generated method stub
		return super.list();
	}

	public String[] list(FilenameFilter filter) {
		// TODO Auto-generated method stub
		return super.list(filter);
	}

	public File[] listFiles(FileFilter filter) {
		throw new UnsupportedOperationException();
	}

	public File getAbsoluteFile() {
		throw new UnsupportedOperationException();
	}

	public long getFreeSpace() {
		throw new UnsupportedOperationException();
	}

	public long getTotalSpace() {
		throw new UnsupportedOperationException();
	}

	public long getUsableSpace() {
		throw new UnsupportedOperationException();
	}

	public boolean isAbsolute() {
		throw new UnsupportedOperationException();
	}

	public boolean isHidden() {
		throw new UnsupportedOperationException();
	}

	public long lastModified() {
		throw new UnsupportedOperationException();
	}

	public long length() {
		throw new UnsupportedOperationException();
	}

	public int compareTo(File pathname) {
		throw new UnsupportedOperationException();
	}

	public boolean createNewFile() throws IOException {
		throw new UnsupportedOperationException();
	}

	public boolean delete() {
		throw new UnsupportedOperationException();
	}

	public void deleteOnExit() {
		throw new UnsupportedOperationException();
	}

	public boolean mkdir() {
		throw new UnsupportedOperationException();
	}

	public boolean mkdirs() {
		throw new UnsupportedOperationException();
	}

	public boolean renameTo(File dest) {
		throw new UnsupportedOperationException();
	}

	public boolean setExecutable(boolean executable, boolean ownerOnly) {
		throw new UnsupportedOperationException();
	}

	public boolean setExecutable(boolean executable) {
		throw new UnsupportedOperationException();
	}

	public boolean setLastModified(long time) {
		throw new UnsupportedOperationException();
	}

	public boolean setReadOnly() {
		throw new UnsupportedOperationException();
	}

	public boolean setReadable(boolean readable, boolean ownerOnly) {
		throw new UnsupportedOperationException();
	}

	public boolean setReadable(boolean readable) {
		throw new UnsupportedOperationException();
	}

	public boolean setWritable(boolean writable, boolean ownerOnly) {
		throw new UnsupportedOperationException();
	}

	public boolean setWritable(boolean writable) {
		throw new UnsupportedOperationException();
	}

	public URI toURI() {
		throw new UnsupportedOperationException();
	}

	public URL toURL() throws MalformedURLException {
		throw new UnsupportedOperationException();
	}

}