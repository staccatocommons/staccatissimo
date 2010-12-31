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

import java.util.concurrent.Callable;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.defs.Provider;
import net.sf.staccato.commons.lang.SoftException;

/**
 * 
 * A {@link Lifecycle} is a logic of initialization, use and dispose of a
 * resource , that can be executed as a single unit of work. It can be executed
 * in different fashions - using {@link #execute()}, {@link #call()} ,
 * {@link #run()} or {@link #value()}
 * 
 * Lifecycles are abstract, so in order to use them, client code must implement
 * the following methods
 * <ul>
 * <li>{@link #initialize()}: this method will be invoked in order to create an
 * initialize the handled resource, and it can throw any exception</li>
 * <li>{@link #doWork(Object)}: this method executes the unit of work, it will
 * be invoked to produce the result of the lifecycle, passing the handled
 * resource returned by the {@link #initialize()} method.</li>
 * <li>{@link #dispose(Object)}</li>
 * </ul>
 * 
 * @author fbulgarelli
 * 
 * @param <ResourceType>
 * @param <ExceptionType>
 * @param <ResultType>
 */
public abstract class Lifecycle<ResourceType, ResultType> implements Runnable,
	Callable<ResultType>, Provider<ResultType> {

	/**
	 * Executes this {@link Lifecycle}, initializing the resource it handles,
	 * doing some work with it, disposing the resource and returning the work
	 * result.
	 * 
	 * @return the result of the work over the resource
	 */
	public ResultType execute() {
		return SoftException.callOrSoften(this);
	}

	public <ExceptionType extends Exception> ResultType executeThrowing(
		Class<ExceptionType> exceptionClass) throws ExceptionType {
		try {
			return executeInternal();
		} catch (Exception e) {
			if (shouldCatch(exceptionClass, e)) {
				throw (ExceptionType) e;
			}
			throw SoftException.soften(e);
		}
	}

	public <ExceptionType1 extends Exception, ExceptionType2 extends Exception> ResultType executeThrowing(
		Class<ExceptionType1> exceptionClass1, Class<ExceptionType1> exceptionClass2)
		throws ExceptionType1, ExceptionType2 {
		try {
			return executeInternal();
		} catch (Exception e) {
			if (shouldCatch(exceptionClass1, e)) {
				throw (ExceptionType1) e;
			}
			if (shouldCatch(exceptionClass2, e)) {
				throw (ExceptionType2) e;
			}
			throw SoftException.soften(e);
		}
	}

	private <ExceptionType1> boolean shouldCatch(Class<ExceptionType1> exceptionClass, Exception e) {
		return exceptionClass.isAssignableFrom(e.getClass());
	}

	@Override
	public ResultType call() throws Exception {
		return executeInternal();
	}

	@Override
	public void run() {
		execute();
	}

	@Override
	public ResultType value() {
		return execute();
	}

	private ResultType executeInternal() throws Exception {
		ResourceType resource = null;
		try {
			resource = initialize();
			return doWork(resource);
		} finally {
			if (resource != null)
				dispose(resource);
		}
	}

	/**
	 * Initializes and gets a resource of ResourceType
	 * 
	 * @return the initialized resources
	 * @throws Exception
	 *           if any error occurs
	 */
	protected abstract ResourceType initialize() throws Exception;

	/**
	 * Makes usage of a resource, and returns a result
	 * 
	 * @param resource
	 *          the resource to use
	 * @return the result of using the resource, of ResultType. It may be null, if
	 *         and only if ResultType is {@link Void}
	 * @throws Exception
	 *           if any error occurs
	 */
	protected ResultType doWork(@NonNull ResourceType resource) throws Exception {
		doVoidWork(resource);
		return null;
	}

	protected void doVoidWork(@NonNull ResourceType resource) throws Exception {
	}

	/**
	 * Disposes the resource.
	 * 
	 * Default implementation does nothing, subclasses may want to override this
	 * method to add disposal logic
	 * 
	 * @param resource
	 * @throws Exception
	 *           if any error occurs
	 */
	protected void dispose(@NonNull ResourceType resource) throws Exception {
	}
}
