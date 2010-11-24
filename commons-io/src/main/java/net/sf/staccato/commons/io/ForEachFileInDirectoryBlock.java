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
import java.util.Iterator;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Executable2;
import net.sf.staccato.commons.lang.block.Block;

import org.apache.commons.io.FileUtils;

/**
 * A block that executes an {@link Executable2} for each file in a directory
 * 
 * @author flbulgarelli
 */
public class ForEachFileInDirectoryBlock extends Block<File> {

	private final Executable2<File, File> block;
	private final boolean recursive;

	/**
	 * Creates a new {@link ForEachFileInDirectoryBlock}
	 * 
	 * @param block
	 *          the {@link Executable2} to execute. Non null.
	 * @param recursive
	 *          if the directory should be explored recursively
	 */
	public ForEachFileInDirectoryBlock(@NonNull Executable2<File, File> block,
		boolean recursive) {
		this.block = block;
		this.recursive = recursive;
	}

	/**
	 * Executes the block for each file in directory, passing as the first
	 * parameter the directory being explored - this method argument - and each
	 * file, for the second argument
	 */
	@Override
	public void exec(File argument) {
		for (Iterator<File> iter = FileUtils//
			.iterateFiles(argument, new String[] { "class" }, recursive); iter
			.hasNext();)
			block.exec(argument, iter.next());
	}

	/**
	 * @return if iterates the directory recursively
	 */
	public boolean isRecursive() {
		return recursive;
	}

	/**
	 * @return the block
	 */
	public Executable2<File, File> getBlock() {
		return block;
	}

}
