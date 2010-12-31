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

import java.io.Closeable;

/**
 * Abstract {@link Lifecycle} that closes target on disposing it.
 * 
 * @author flbulgarelli
 * 
 * @param <TargetType>
 * @param <ReturnType>
 */
public abstract class CloseableLifecycle<TargetType extends Closeable, ReturnType> extends
	Lifecycle<TargetType, ReturnType> {

	public void dispose(TargetType target) throws Exception {
		target.close();
	}

}
