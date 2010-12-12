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
package net.sf.staccato.commons.collections.stream;

import java.util.Collection;
import java.util.Iterator;

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.collection.ContainsAware;
import net.sf.staccato.commons.lang.collection.SizeAware;

/**
 * A {@link Stream} represent a lazy, rich-interfaced, {@link Iterable} source
 * of objects, that generalizes the concept of a {@link Collection}.
 * <p>
 * {@link Stream}s are lazy, that means, messages passed to it will normally
 * return fast, and no really processing will be done until it is really needed.
 * </p>
 * <p>
 * {@link Stream}s offer a highly rich interface. Not like the
 * {@link Collection} interface, {@link Stream}s do not, nor should have
 * subinterfaces that make any guarantee about the iteration order, mutability,
 * number or duplication of elements, etc, as they will depend exclusively on
 * implementors.
 * </p> {@link Stream} exposes several methods that will be only supported only
 * by concrete implementors. Those methods are marked to throw
 * {@link UnsupportedOperationException}
 * <p>
 * {@link Stream}s are not standalone, that is, they will normally wrap other
 * objects will be the actually producer of elements. Those object are called
 * "source"s in Stream terminology.
 * </p>
 * <p>
 * {@link Stream}s implementors are required to be unmodifiable, and
 * {@link #iterator()} message must always return an unmodifible
 * {@link Iterator}. However, nothing prevents sources from being immutable, nor
 * being side-effect-free, so {@link Streams} will have such characteristics as
 * long as its sources has them
 * </p>
 * 
 * @author fbulgarelli
 * 
 * @param <A>
 *          the type of object the stream is source of
 */
public interface Stream<A> extends //
	Iterable<A>, //
	SizeAware, //
	ContainsAware<A>, //
	Filterable<A>, //
	Foldable<A>,//
	Searchable<A>,//
	Testeable<A>, //
	Mappable<A>, //
	Accessible<A>, //
	Collectible<A>, //
	Appendabable<A> {

	<B> Stream<B> then(Applicable<Stream<A>, ? extends Iterable<B>> function);

}
