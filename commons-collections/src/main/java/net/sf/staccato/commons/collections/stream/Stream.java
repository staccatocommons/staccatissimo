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

import net.sf.staccato.commons.lang.collection.ContainsAware;
import net.sf.staccato.commons.lang.collection.SizeAware;

/**
 * TODO rewrite this javadoc! Is out of date an incomplete
 * 
 * A {@link Stream} is an object which is a source of other objects.
 * 
 * 
 * It is a generalization of a collection
 * 
 * {@link Stream}s are lazy, that means, messages passed to it will normally
 * return fast, and no really processing will be done until it is really needed.
 * 
 * 
 * {@link Iterable} object with a highly rich interface. Not like the
 * {@link Collection} interface, it is not aimed to be subclasified in order to
 * add extra semantics, nor to have concrete, standalone, implementations.
 * Instead, implementation are supposed to decorate other collections, and,
 * {@link Stream}s do not, nor should have subinterfaces that, make any
 * guarantee about the iteration order, mutability, number or duplication of
 * elements, etc, as they will vary from instance to instance.
 * 
 * Thus, {@link Stream} exposes several methods that will be only supported if
 * the concrete decorated collection supports them. Those methods are marked to
 * throw {@link UnsupportedOperationException}, and, like any of the optional
 * methods from {@link Collection}, should be only used on code that either
 * creates the {@link Stream}, or when it was get from a method that explicitly
 * document the Container behaviour.
 * 
 * {@link Stream} are unmodifiable. {@link #iterator()} message will always
 * return an unmodifible {@link Iterator}
 * 
 * {@link Stream} interface is heavily inspired in Smalltalk Collection
 * Protocol, excluding including non-pure functional methods
 * 
 * @author fbulgarelli
 * 
 * @param <A>
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
}
