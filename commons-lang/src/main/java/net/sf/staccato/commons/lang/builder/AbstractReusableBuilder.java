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
package net.sf.staccato.commons.lang.builder;

import net.sf.staccato.commons.lang.check.Check;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An abstract implementation of a builder pattern.
 * 
 * Guarantees the following aspects of a builder
 * <ul>
 * <li>Will not return the built object unless the construction process has
 * finished</li>
 * <li>Will not permit the builder to be used more than once, this is, the built
 * object can not be retrieved more than once</li>
 * </ul>
 * 
 * Client code has two responsibilities:
 * <ul>
 * <li>Add methods that actually configure the object under construction</li>
 * <li>Implement {@link #checkDone(Check)} to verify the object under
 * construction is actually done</li>
 * </ul>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the kind of object this {@link AbstractReusableBuilder} can create
 * 
 */
public abstract class AbstractReusableBuilder<T> implements ReusableBuilder<T> {

	private static final DoneCheck check = new DoneCheck();

	/**
	 * Checks the builder is ready for creating a new object, sending
	 * {@link #checkDone(DoneCheck)} messages, and then creates it, sending
	 * {@link #buildObject()}.
	 * 
	 * @see ReusableBuilder#build()
	 */
	public final T build() throws ObjectUnderConstructionException {
		checkDone(check);
		return buildObject();
	}

	protected void checkDone(DoneCheck check) throws ObjectUnderConstructionException {
	}

	protected abstract T buildObject();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * A {@link Check} that throws an {@link ObjectUnderConstructionException}
	 * when checks fail
	 */
	public static final class DoneCheck extends Check<ObjectUnderConstructionException> {

		private DoneCheck() {
		}

		protected ObjectUnderConstructionException createException(Failure failure) {
			return new ObjectUnderConstructionException(failure.createMessage());
		}

	}

}
