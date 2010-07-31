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
package net.sf.staccato.commons.lang.check;

/**
 * A policy encapsulates a sequence of checks
 * 
 * @author fbulgarelli
 * 
 * @param <TargetType>
 *          the type of object that will checked in order to see if it violates
 *          or not this policy
 */
public interface Policy<TargetType> {

	public <ExceptionType extends Throwable> void enforce(TargetType target,
		Check<ExceptionType> check) throws ExceptionType;

}
