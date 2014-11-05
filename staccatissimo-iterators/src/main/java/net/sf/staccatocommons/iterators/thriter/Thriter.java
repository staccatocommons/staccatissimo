/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.iterators.thriter;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.effect.SideEffectFree;

/**
 * A {@link Thriter} - acronym for <strong>Thr</strong>ee-messages
 * <strong>Iter</strong>ator - is an object that can traverse elements of type
 * {@code A} from a collection-like object, much like an {@link Iterator} does,
 * but using three messages instead of two, enabling new lazy features.
 * 
 * Such messages are {@link #hasNext()}, {@link #advanceNext()} and
 * {@link #current()}:
 * <ul>
 * <li>
 * {@link #hasNext()} is completely equivalent to {@link Iterator#hasNext()} ,
 * this message just polls if the iterator has more elements. It
 * <strong>must</strong> be side-effect free, and its result must be constant as
 * long as the thriter is not advanced.</li>
 * <li>
 * {@link #advanceNext()} advances the iterator to its next element, but it does
 * not return it. Like {@link Iterator#next()}, it <strong>must</strong> throw a
 * no {@link NoSuchElementException} if the iterator has reached its end</li>
 * <li>{@link #current()} returns the element at the actual position. This
 * method <strong>should</strong> return the same element as long as the thriter
 * is not advanced, but the result of evaluating it if the thriter was never
 * advanced is undefined. Lazy thriters <strong>should</strong> implementing the
 * actual processing here</li>
 * </ul>
 * 
 * For example,
 * 
 * <pre>
 * while (thriter.hasNext) {
 *   thriter.advanceNext();
 *   A elem = thriter.current();
 * }
 * </pre>
 * 
 * <p>
 * Although normal iteration on thriters is more complex that in
 * {@link Iterator}s, the power of {@link Thriter}s lays if that it allows to
 * perform special lazy iterations that would be otherwise impossible with an
 * plain two-messages iterator.
 * </p>
 * <p>
 * For example, lets assume that a {@link Thriter} {@code thriter} can retrieve
 * two elements, and that its first element is <code>bottom</code> - that is, it
 * will never return normally, for example, by throwing an exception or by
 * falling in a infinite loop. Then, the second element of the {@code thriter}
 * is an actual value - accessing it would return normally. In such scenario,
 * the following successfully obtains the second element of the thriter:
 * </p>
 * 
 * <pre>
 * thriter.advance();
 * thriter.advance();
 * thriter.current();
 * </pre>
 * <p>
 * However, the same behavior is impossible to implement using a
 * {@link Iterator}, as in order to access the n element, it is necessary to
 * access the n-1 element.
 * </p>
 * 
 * @author flbulgarelli
 * @see Thriterator
 * @see AbstractThriterator
 * @param <A>
 *          the type of elements retrieved by this {@link Thriter}
 */
public interface Thriter<A> {

  /**
   * Answers if the thriter has more elements, that is, if sending
   * {@link #advanceNext()} would not result in a {@link NoSuchElementException}
   * 
   * @return if the {@link Thriter} has more elements
   */
  @SideEffectFree
  boolean hasNext();

  /**
   * Advances to the thriter to the position of the next element.
   * 
   * @throws NoSuchElementException
   *           if there are no more elements
   */
  void advanceNext() throws NoSuchElementException;

  /**
   * Answers element at the current position of this {@link Thriter}. Result of
   * this method if {@link #advanceNext()} was never evaluated before is
   * undefined.
   * 
   * @return the current element
   */
  A current();

  /**
   * Answers a {@link Thunk} that, when evaluated through {@link Thunk#value()},
   * will return the current element of this {@link Thriter} at the time of
   * being {@link #delayedCurrent()} sent.
   * 
   * This means, that the value of the returned thunk <strong>must</strong> must
   * always equals, even if this {@link Thriter} was advanced later.
   * 
   * @return a {@link Thunk} tha provides {@link #current()} at the time of this
   *         message being sent.
   */
  @NonNull
  Thunk<A> delayedCurrent();

  /**
   * Advances this thriter and the returns {@link #delayedCurrent()}
   * 
   * @return {@link #delayedCurrent()}
   */
  @NonNull
  default Thunk<A> delayedNext() {
    advanceNext();
    return delayedCurrent();
  }

}
