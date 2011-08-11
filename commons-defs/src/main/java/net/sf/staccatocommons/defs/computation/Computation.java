/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.defs.computation;

import java.util.concurrent.Executor;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @since 1.2
 */
public interface Computation<A> {

  <B> B eval(Applicable<Thunk<A>, B> processor);

  void eval(Executable<Thunk<A>> processor);

  void eval(Executor executor);

}