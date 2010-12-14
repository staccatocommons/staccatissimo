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
package net.sf.staccato.commons.lang.provider;

import java.util.concurrent.Callable;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.provider.internal.CallableProvider;
import net.sf.staccato.commons.lang.provider.internal.Constant;
import net.sf.staccato.commons.lang.provider.internal.NullProvider;

/**
 * @author flbulgarelli
 * 
 */
public class Providers {

	@NonNull
	public static <T> Provider<T> constant(T value) {
		return new Constant<T>(value);
	}

	@NonNull
	public static <T> Provider<T> callable(@NonNull Callable<T> callable) {
		return new CallableProvider<T>(callable);
	}

	@NonNull
	public static <T> Provider<T> null_() {
		return NullProvider.getInstance();
	}

}
