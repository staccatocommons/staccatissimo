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
package net.sf.staccato.commons.lang.lifecycle;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <ResourceType>
 * @param <ExceptionType>
 * @param <ReturnType>
 */
public abstract class DefaultLifecycle<ResourceType, ReturnType> implements
	Lifecycle<ResourceType, ReturnType> {

	public abstract ResourceType initialize() throws Exception;

	/**
	 * Uses the resource, first invoking {@link #performTask(Object)} and the
	 * {@link #produceResult(Object)}
	 * */
	public final ReturnType doWork(@NonNull ResourceType resource) throws Exception {
		performTask(resource);
		return produceResult(resource);
	}

	public void performTask(@NonNull ResourceType resource) throws Exception {
	}

	public ReturnType produceResult(@NonNull ResourceType resource) throws Exception {
		return null;
	}

	/**
	 * Does nothing, subclasses may want to override this method to add disposal
	 * logic
	 */
	public void dispose(@NonNull ResourceType resource) throws Exception {
	}

	/**
	 * Creates a {@link LifecycleManager} that is capable of executing this
	 * {@link Lifecycle}
	 * 
	 * @return a new {@link LifecycleManager}
	 */
	@NonNull
	public LifecycleManager<ResourceType, ReturnType> createManager() {
		return LifecycleManager.from(this);
	}

	/**
	 * Executes this {@link Lifecycle}, creating a manager and executing it.
	 * 
	 * @return the return type of this lifecycle
	 */
	public ReturnType execute() {
		return createManager().execute();
	}

	/**
	 * Executes this {@link Lifecycle}, creating a manager and executing it,
	 * checking a specific exception
	 * 
	 * @return the return type of this lifecycle
	 * @throws Exception
	 */
	public <ExceptionType extends Exception> ReturnType executeThrowing(
		Class<ExceptionType> exceptionClass) throws ExceptionType {
		return createManager().executeThrowing(exceptionClass);
	}

}
