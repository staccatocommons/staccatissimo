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

import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.SoftException;

/**
 * 
 * A {@link LifecycleManager} is an object capable of executing a
 * {@link Lifecycle} in different fashions - using {@link #execute()},
 * {@link #call()}, {@link #run()} or {@link #value()}
 * 
 * @author fbulgarelli
 * 
 * @param <ManagedObjectType>
 * @param <ExceptionType>
 * @param <ReturnType>
 */
public final class LifecycleManager<ManagedObjectType, ReturnType> implements
	Runnable, Callable<ReturnType>, Provider<ReturnType> {

	private final Lifecycle<ManagedObjectType, ReturnType> managedLifecycle;

	public LifecycleManager(Lifecycle<ManagedObjectType, ReturnType> lifecycle) {
		this.managedLifecycle = lifecycle;
	}

	public ReturnType execute() {
		return SoftException.callOrSoften(this);
	}

	public <ExceptionType extends Exception> ReturnType executeThrowing(
		Class<ExceptionType> exceptionClass) throws ExceptionType {
		try {
			return executeInternal();
		} catch (Exception e) {
			if (exceptionClass.isAssignableFrom(e.getCause().getClass())) {
				throw (ExceptionType) e;
			}
			throw SoftException.soften(e);
		}
	}

	public <ExceptionType1 extends Exception, ExceptionType2 extends Exception> ReturnType executeThrowing(
		Class<ExceptionType1> exceptionClass1, Class<ExceptionType1> exceptionClass2)
		throws ExceptionType1, ExceptionType2 {
		try {
			return executeInternal();
		} catch (Exception e) {
			if (exceptionClass1.isAssignableFrom(e.getCause().getClass())) {
				throw (ExceptionType1) e;
			}
			if (exceptionClass2.isAssignableFrom(e.getCause().getClass())) {
				throw (ExceptionType2) e;
			}
			throw SoftException.soften(e);
		}
	}

	@Override
	public ReturnType call() throws Exception {
		return executeInternal();
	}

	@Override
	public void run() {
		execute();
	}

	@Override
	public ReturnType value() {
		return execute();
	}

	private ReturnType executeInternal() throws Exception {
		ManagedObjectType resource = null;
		try {
			resource = managedLifecycle.initialize();
			return managedLifecycle.doWork(resource);
		} finally {
			if (resource != null)
				managedLifecycle.dispose(resource);
		}
	}

	public Lifecycle<ManagedObjectType, ReturnType> getManagedLifecycle() {
		return managedLifecycle;
	}

	public static <MO, R> LifecycleManager<MO, R> createManager(
		Lifecycle<MO, R> aLifecycle) {
		return new LifecycleManager<MO, R>(aLifecycle);
	}
}
