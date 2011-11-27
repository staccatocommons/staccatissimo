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
package net.sf.staccatocommons.control.monad;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicative;

/**
 * A {@link MonadicFunction} is an {@link Applicative} from A to Monad&lt;B&gt;.
 * This interface adds to Applicable a function combinator called <a
 * href="http://en.wikipedia.org/wiki/Kleisli_category">Kleisli composition</a>
 * 
 * Fortunately, like with most classes within this package, it is not necessary
 * to understand its formal aspects in order to use it.
 * 
 * @author flbulgarelli
 * @since 1.2
 */
@Applicative
public interface MonadicFunction<A, B> extends Applicable<A, Monad<B>> {

  /**
   * Combines this function with another using the Kleisli composition.
   * 
   * Functions get combined in the following figure:
   * 
   * <pre>
   * >----this---+----other---->
   * </pre>
   * 
   * This enables to encapsulate a pipeline of monad transformation all
   * together. For example, the following code:
   * 
   * <pre>
   * 
   *  Monad&lt;X&gt; monad = ...;
   *  monad.bind(f1).bind(f2).bind(f3).run();
   * 
   * </pre>
   * 
   * Is equivalent to
   * 
   * <pre>
   * 
   *  Monad&lt;X&gt; monad = ...;
   *  monad.bind(f1.then(f2).then(f3)).run();
   * 
   * </pre>
   * 
   * This lets encapsulate a pipeline of common transformations into a single
   * function, so that it can be reused latter.
   * 
   * 
   * @param <C>
   * @param other
   * @return new {@link MonadicFunction} that combines this one with the given
   *         function into a pipeline
   */
  public <C> MonadicFunction<A, C> then(Applicable<? super B, Monad<C>> other);
}
