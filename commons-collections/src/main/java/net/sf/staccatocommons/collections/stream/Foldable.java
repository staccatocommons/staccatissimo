/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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

package net.sf.staccatocommons.collections.stream;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.number.NumberTypeAware;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for folding -aka injecting - elements - producing a
 * single aggregated result from all the Stream elements.
 * 
 * {@link Streams} implements lefts folds, which consist of taking an initial
 * value and a {@link Stream} element, applying a function to them, and then
 * repeating the process with this result as the next initial value and the next
 * element from the stream. The last returned value is the folding result.
 * 
 * Due to previous definition, folds in this interface do not work on infinite
 * {@link Stream}s
 * 
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Fold_(higher-order_function)">Folds</a>
 * @author flbulgarelli
 */
public interface Foldable<A> {

  /**
   * (Left)folds this {@link Stream} using an initial value and the given
   * two-arg function
   * 
   * @param <B>
   * @param initial
   * @param function
   * @return the folding result
   */
  <B> B fold(B initial, @NonNull Applicable2<? super B, ? super A, ? extends B> function);

  /**
   * (Left)folds the tail of this {@link Stream} using the first element of the
   * stream as initial value
   * 
   * @param function
   * @return the folding result
   * @throws NoSuchElementException
   *           if the {@link Stream} is empty
   */
  A reduce(@NonNull Applicable2<? super A, ? super A, ? extends A> function) throws NoSuchElementException;

  /**
   * (Left)folds this {@link Stream} concatenating each elements toString with a
   * separator
   * 
   * @param separator
   * @return the string representation of each element concatenated using a
   *         separator
   */
  @NonNull
  String joinStrings(@NonNull String separator);

  /**
   * Answers the sum of the elements of this {@link Stream} using the given
   * {@link NumberType}
   * 
   * @param numberType
   * @return the result of adding each element, or zero, if this stream is empty
   * @see Iterables#sum(Iterable, NumberType)
   */
  @NonNull
  A sum(@NonNull NumberType<A> numberType);

  /**
   * Answers the product of the elements of this {@link Stream} using the given
   * {@link NumberType}
   * 
   * @param numberType
   * @return the result of multiplying each element, or one, if this stream is
   *         empty
   * @see Iterables#product(Iterable, NumberType)
   */
  @NonNull
  A product(@NonNull NumberType<A> numberType);

  /**
   * Answers the sum of the elements of this {@link Stream} using the Stream's
   * source as {@link NumberTypeAware}
   * 
   * @return the result of adding each element, or zero, if this stream is empty
   * @throws ClassCastException
   *           if the source is not an implicit number type
   * @see Iterables#sum(Iterable)
   */
  @NonNull
  A sum() throws ClassCastException;

  /**
   * Answers the product of the elements of this {@link Stream} using the
   * Stream's source as {@link NumberTypeAware}
   * 
   * @return the result of multiplying each element, or one, if this stream is
   *         empty
   * @throws ClassCastException
   *           if the source is not an implicit number type
   * @see Iterables#product(Iterable)
   */
  @NonNull
  A product() throws ClassCastException;

  /**
   * Answers the average of the stream elements, using the given
   * {@link NumberType} for performing addition and division.
   * 
   * @param numberType
   * @return the average of the stream elements
   * @throws ArithmeticException
   *           if the stream is empty and number type does not support zero
   *           division
   */
  A average(@NonNull NumberType<A> numberType) throws NoSuchElementException;

  /**
   * Answers the average of the stream elements, using the stream's source as an
   * {@link NumberTypeAware}.
   * 
   * @return the average of the stream elements
   * @throws ArithmeticException
   *           if the stream is empty and number type does not support zero
   *           division
   * @throws ClassCastException
   *           if the source is not an implicit number type
   * @see #average(NumberType)
   */
  A average() throws ClassCastException, NoSuchElementException;

}