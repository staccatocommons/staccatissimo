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

import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for splitting an <code>Stream</code> into head - its
 * first element, aka <code>first</code> or <code>car</code> - and tail - an
 * <code>Stream</code> with the rest of the elements, aka <code>rest</code> or
 * <code>cdr</code>.
 * 
 * Constructing is the oposite of deconstructing. Such operations can be found
 * in {@link Cons}
 * 
 * @author flbulgarelli
 */
public interface Deconstructable<A> {

	/**
	 * Lazily applies the given <code>function</code> to this stream,
	 * deconstructing this stream into head and tail, or into an empty stream.
	 * 
	 * Unlike {@link Stream#then(Applicable)}, whose function will receive the
	 * whole stream, the given {@link DeconsApplicable}, when applied, will take
	 * the head and tail of this {@link Stream}, if non empty, or no arguments, if
	 * the stream is empty.
	 * 
	 * @param <B>
	 * @param function
	 * @return a new stream that will retrieve elements from the result of
	 *         applying the given function to this stream
	 * @see #decons()
	 */
	@NonNull
	@Projection
	<B> Stream<B> then(@NonNull DeconsApplicable<A, B> function);

	// XXX this method name is confusing. It should be "then" and take a different
	// DeconsApplicable

	/**
	 * Lazily applies the given <code>function</code> to this stream,
	 * deconstructing this stream into a head thunk and tail, or into an empty
	 * stream.
	 * 
	 * Unlike {@link Stream#then(Applicable)}, whose function will receive the
	 * whole stream, the given {@link DeconsApplicable}, when applied, will take a
	 * head's thunk and tail of this {@link Stream}, if non empty, or no
	 * arguments, if the stream is empty.
	 * 
	 * @param <B>
	 * @param function
	 * @return a new stream that will retrieve elements from the result of
	 *         applying the given function to this stream
	 * @see #delayedDecons()
	 */
	@NonNull
	@Projection
	<B> Stream<B> delayedThen(@NonNull DeconsApplicable<A, B> function);

	/**
	 * Answers this stream split into head and tail.
	 * 
	 * This method is preferred over {@link #head()} and {@link #tail()}, as it
	 * will work as expected even on non repeatable iteration streams.
	 * 
	 * @return a pair containing the head and the tail of this stream. The tail is
	 *         {@link NonNull} and a {@link Projection}, but it is always
	 *         non-repeatable.
	 * @throws NoSuchElementException
	 *           if stream is empty
	 */
	@NonNull
	Pair<A, Stream<A>> decons();

	/**
	 * Answers this non-empty stream split into a head thunk and tail.
	 * 
	 * This method is preferred over {@link #decons()} when the head value of the
	 * {@link Stream} is potentially irrelevant, as this methods grants to suceeds
	 * even in those cases where {@link #head()} fails.
	 * 
	 * @return a pair containing a thunk that will provide the head, and the tail
	 *         of this stream. The tail is {@link NonNull} and a
	 *         {@link Projection}, but it is always non-repeatable.
	 * @throws NoSuchElementException
	 *           if stream is empty
	 */
	@NonNull
	Pair<Thunk<A>, Stream<A>> delayedDecons();

	/**
	 * Answers the head of the {@link Stream}, which is the first element of the
	 * stream.
	 * 
	 * @return {@link Stream#first()}
	 * @throws NoSuchElementException
	 *           if stream is empty
	 */
	A head();

	/**
	 * Answers the tail of the {@link Stream}
	 * 
	 * @return an {@link Stream} that retrieves all its elements, except of the
	 *         first one
	 * @throws NoSuchElementException
	 *           if stream is empty
	 */
	@NonNull
	@Projection
	Stream<A> tail();

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
	public static interface DeconsApplicable<A, B> extends Applicable2<A, Stream<A>, Stream<B>> {

		/**
		 * Applies this transformation when this Stream can not be deconstructed in
		 * head and tail, because it is empty.
		 * 
		 * @return the result of applying this transformation over and empty
		 *         {@link Stream}
		 */
		Stream<B> emptyApply();

		/**
		 * Applies this transformation to a non empty Stream splitted into tail and
		 * head.
		 * 
		 * Independently of the original stream source, the tail Stream is always
		 * non-repeatable.
		 * 
		 * {@link Stream}s will send this message when evaluating
		 * {@link Stream#then(DeconsApplicable)}
		 */
		Stream<B> apply(A head, Stream<A> tail);

		/**
		 * Applies this transformation to a non empty Stream splitted into tail and
		 * head's thunk.
		 * 
		 * Independently of the original stream source, the tail Stream is always
		 * non-repeatable.
		 * 
		 * {@link Stream}s will send this message when evaluating
		 * {@link Stream#delayedThen(DeconsApplicable)}
		 */
		Stream<B> delayedApply(Thunk<A> head, Stream<A> tail);

	}

}
