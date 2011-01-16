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
package net.sf.staccatocommons.lang.lifecycle;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.provider.Provider;

/**
 * 
 * A {@link Lifecycle} is a logic of initialization, use and dispose of a
 * resource , that can be executed as a single unit of work. It can be executed
 * in different fashions - using {@link #value()}, {@link #call()} ,
 * {@link #run()} or {@link #value()}
 * 
 * Lifecycles are abstract, so in order to use them, client code must implement
 * the following methods
 * <ul>
 * <li>{@link #initialize()}: this method will be invoked in order to create an
 * initialize the handled resource. It <strong>may</strong> throw any exception.
 * Typical example of such initialization is instantiating the resource or
 * sending an <code>init</code>message.</li>
 * <li>
 * <p>
 * {@link #doWork(Object)}: this method uses the resource. It will be invoked to
 * produce the result of the lifecycle, passing the handled resource returned by
 * the {@link #initialize()} method. It <strong>may</strong> throw any
 * exception.
 * </p>
 * <p>
 * Lifecycles parameterized to have a result of type {@link Void}
 * <strong>must</strong> return <code>null</code>; for those cases, client code,
 * instead of overriding {@link #doWork(Object)}, <strong>should</strong>
 * override {@link #doVoidWork(Object)}
 * </p>
 * </li>
 * <li>
 * <p>
 * {@link #dispose(Object)}: this method release the handled resource, freeing
 * any system resources associated to it. It <strong>may</strong> throw any
 * exception. Typical example of such disposal is sending a <code>close</code>
 * message.
 * </p>
 * <p>
 * This method will be invoked even if the usage of the resource - that is
 * {@link #doWork(Object)} or {@link #doVoidWork(Object)} execution - failed
 * throwin and exception.
 * </p>
 * </li>
 * </ul>
 * 
 * @author fbulgarelli
 * 
 * @param <ResourceType>
 *          the type of resource handled by this lifecycle
 * @param <ResultType>
 *          the type of result returned by this lifecycle
 */
public abstract class Lifecycle<ResourceType, ResultType> extends Provider<ResultType> {

	/**
	 * Executes this {@link Lifecycle}, initializing the resource it handles,
	 * doing some work with it, disposing the resource and returning the work
	 * result.
	 * 
	 * Any checked or unchecked exception produced during the flow execution will
	 * be catch, soften and rethrown.
	 * 
	 * 
	 * @throws RuntimeException
	 *           if any exception is thrown during the flow execution
	 * @return the result of the work over the resource
	 * @see SoftException#soften(Exception)
	 */
	public ResultType call() throws Exception {
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
	 * {@link Lifecycle}s parameterized to have a {@link Void} result
	 * <strong>should not</strong> override this method, but
	 * {@link #doVoidWork(Object)} instead
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

	/**
	 * Makes usage of a resource, without returning a result.
	 * 
	 * This method <strong>should</strong> only be overriden in {@link Lifecycle}s
	 * parameterized to have a {@link Void} result. If it is not the case,
	 * override {@link #doWork(Object)} instead.
	 * 
	 * @param resource
	 *          the resource to use
	 * @throws Exception
	 *           is any error occurs
	 */
	protected void doVoidWork(@NonNull ResourceType resource) throws Exception {}

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
	protected void dispose(@NonNull ResourceType resource) throws Exception {}
}
