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
package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.collections.restrictions.Projection;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Stream interface for transforming it into a new one supplying a
 * transformation function.
 * 
 * @author flbulgarelli
 * 
 */
public interface Transformable<A> {

  /**
   * Lazily applies the given function to this {@link Stream}.
   * 
   * @param <B>
   * @param function
   *          the function to apply to this stream
   * @return a new stream that will retrieve elements from the result of
   *         applying the given function to this stream
   */
  @Projection
  <B> Stream<B> transform(@NonNull Applicable<Stream<A>, ? extends Stream<B>> function);

  /**
   * Lazily applies the given <code>function</code> to this stream,
   * deconstructing this stream into head and tail, or into an empty stream.
   * 
   * Unlike {@link Stream#transform(Applicable)}, whose function will receive
   * the whole stream, the given {@link DeconsApplicable}, when applied, will
   * take the head and tail of this {@link Stream}, if non empty, or no
   * arguments, if the stream is empty.
   * 
   * @param <B>
   * @param function
   * @return a new stream that will retrieve elements from the result of
   *         applying the given function to this stream
   * @see #decons()
   */
  @Projection
  <B> Stream<B> transform(@NonNull DeconsApplicable<A, B> function);

  /**
   * Lazily applies the given <code>function</code> to this stream,
   * deconstructing this stream into a head thunk and tail, or into an empty
   * stream.
   * 
   * Unlike {@link Stream#transform(Applicable)}, whose function will receive
   * the whole stream, the given {@link DeconsApplicable}, when applied, will
   * take a head's thunk and tail of this {@link Stream}, if non empty, or no
   * arguments, if the stream is empty.
   * 
   * @param <B>
   * @param function
   * @return a new stream that will retrieve elements from the result of
   *         applying the given function to this stream
   * @see #delayedDecons()
   */
  @Projection
  <B> Stream<B> transform(@NonNull DelayedDeconsApplicable<A, B> function);

  /**
   * @author flbulgarelli
   * @param <A>
   */
  public static interface EmptyApplicable<A> {
    /**
     * Applies this transformation when this Stream can not be deconstructed in
     * head and tail, because it is empty.
     * 
     * @return the result of applying this transformation over and empty
     *         {@link Stream}
     */
    A emptyApply();
  }

  /**
   * An {@link Applicable2} that can transform a {@link Stream} by
   * deconstructing it into head and tail, or into an empty stream.
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   *          input stream type
   * @param <B>
   *          output stream type
   */
  public static interface DeconsApplicable<A, B> extends Applicable2<A, Stream<A>, Stream<B>>,
    EmptyApplicable<Stream<B>> {

    /**
     * Applies this transformation to a non empty Stream splitted into tail and
     * head.
     * 
     * Independently of the original stream source, the tail Stream is always
     * non-repeatable.
     * 
     * {@link Stream}s will send this message when evaluating
     * {@link Stream#transform(DeconsApplicable)}
     */
    Stream<B> apply(A head, Stream<A> tail);
  }

  /**
   * An {@link Applicable2} that can transform a {@link Stream} by
   * deconstructing it into head thunk and tail, or into an empty stream.
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   *          input stream type
   * @param <B>
   *          output stream type
   */
  public static interface DelayedDeconsApplicable<A, B> extends Applicable2<Thunk<A>, Stream<A>, Stream<B>>,
    EmptyApplicable<Stream<B>> {

    /**
     * Applies this transformation to a non empty Stream splitted into tail and
     * head's thunk.
     * 
     * Independently of the original stream source, the tail Stream is always
     * non-repeatable.
     * 
     * {@link Stream}s will send this message when evaluating
     * {@link Stream#transform(DeconsApplicable)}
     */
    Stream<B> apply(Thunk<A> head, Stream<A> tail);

  }
}
