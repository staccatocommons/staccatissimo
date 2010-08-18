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
package net.sf.staccato.commons.lang;

/**
 * {@link Applicable2}s are computations that take two arguments and whose
 * result is a return value.
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public interface Applicable2<T1, T2, R> {

	R apply(T1 arg1, T2 arg2);

}