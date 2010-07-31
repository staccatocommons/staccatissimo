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

/**
 * 
 * @author flbulgarelli
 * 
 * @param <TargetType>
 * @param <ExceptionType>
 * @param <ReturnType>
 */
public abstract class DefaultLifecycle<TargetType, ExceptionType extends Exception, ReturnType>
	implements Lifecycle<TargetType, ExceptionType, ReturnType> {

	public abstract TargetType initialize() throws ExceptionType;

	public final ReturnType doWork(TargetType target) throws ExceptionType {
		performTask(target);
		return produceResult(target);
	}

	public ReturnType produceResult(TargetType target) throws ExceptionType {
		return null;
	}

	public void dispose(TargetType target) throws ExceptionType {
	}

	public void performTask(TargetType target) throws ExceptionType {
	}

	public LifecycleManager<TargetType, ExceptionType, ReturnType> createManager() {
		return LifecycleManager.createManager(this);
	}

	public ReturnType execute() throws ExceptionType {
		return createManager().execute();
	}
}
