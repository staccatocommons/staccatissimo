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
 * @author fbulgarelli
 * 
 * @param <ManagedObjectType>
 * @param <ExceptionType>
 * @param <ReturnType>
 */
public class LifecycleManager<ManagedObjectType, ExceptionType extends Exception, ReturnType>
	implements Runnable, Callable<ReturnType>, Provider<ReturnType> {

	private final Lifecycle<ManagedObjectType, ExceptionType, ReturnType> managedLifecycle;

	public LifecycleManager(
		Lifecycle<ManagedObjectType, ExceptionType, ReturnType> lifecycle) {
		this.managedLifecycle = lifecycle;
	}

	public ReturnType execute() throws ExceptionType {
		ManagedObjectType managed = null;
		try {
			managed = managedLifecycle.initialize();
			return managedLifecycle.doWork(managed);
		} finally {
			if (managed != null)
				managedLifecycle.dispose(managed);
		}
	}

	/*
	 * public ReturnType execute(ExceptionHandler handler) { try { return
	 * execute(); } catch (Exception e) { handler.handle(e); } }
	 */
	@Override
	public void run() {
		try {
			execute();
		} catch (Exception e) {
			throw SoftException.soften(e);
		}
	}

	@Override
	public ReturnType call() throws Exception {
		return execute();
	}

	@Override
	public ReturnType value() {
		return SoftException.callOrSoften(this);
	}

	public Lifecycle<ManagedObjectType, ExceptionType, ReturnType> getManagedLifecycle() {
		return managedLifecycle;
	}

	public static <MO, E extends Exception, R> LifecycleManager<MO, E, R> createManager(
		Lifecycle<MO, E, R> aLifecycle) {
		return new LifecycleManager<MO, E, R>(aLifecycle);
	}
}
