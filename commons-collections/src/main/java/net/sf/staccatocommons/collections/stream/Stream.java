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
package net.sf.staccatocommons.collections.stream;

import java.util.Collection;
import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.properties.ConditionallyRepeatable;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.SizeAware;
import net.sf.staccatocommons.defs.restriction.ConditionallyImmutable;
import net.sf.staccatocommons.defs.restriction.Unmodifiable;
import net.sf.staccatocommons.lang.number.ImplicitNumberType;

/**
 * A {@link Stream} is a lazy, rich-interfaced, functional-style, unmodifiable,
 * {@link Iterable} object that can retrieve an arbitrary - and potentially
 * unbound - amount of other objects of a parameterized type.
 * 
 * 
 * <h2>Introduction</h2>
 * <p>
 * Stream generalize the concept of an unmodifiable {@link Collection}, by
 * exposing many methods analogous to most of the side-effect-free operation of
 * that interface, and adding dozens of new ones. However, Streams are not meant
 * to replace the <a href=
 * "http://download.oracle.com/javase/6/docs/technotes/guides/collections/index.html"
 * >Java Collection Framework</a>, but to be used on to of it.
 * </p>
 * <p>
 * Streams, also, due to its extremely general definition, are not tied to the
 * Collections handling, but can be used to apply transformation on objects of
 * very different nature: directories, file lines, database result sets,
 * characters in strings, and so on. In fact, any object that has sense to
 * "iterate" is a valid <em>source</em> for a Stream.
 * </p>
 * 
 * <h2>Elements and sources</h2>
 * 
 * <p>
 * As all {@link Iterable}s, a {@link Stream}s retrieve objects through its
 * {@link #iterator()}. Those objects are called, like for Collections, the
 * Stream's <em>elements</em>.
 * </p>
 * <p>
 * However, unlike most collections, an Stream does not normally generate the
 * objects it retrieves, but it gets from another objects, which is called the
 * Stream's <em>source</em>.
 * </p>
 * 
 * <h2>Streams properties</h2>
 * 
 * <p>
 * Streams have many intrinsic properties, while many others depend on its
 * actual source and elements type. Intrinsic properties are:
 * <ul>
 * <li>Laziness: {@link Stream}s are lazy, that means, messages sent to it will
 * normally return fast, and no processing will be done until it is really
 * needed. All such methods are marked as {@link Projection}, and return Streams
 * - but, on the other hand, not all method that return Streams are lazy.</li>
 * <li>Rich interfaced: {@link Stream}s offer a highly rich interface, this
 * interface exposes more than 50 methods. However, not like the
 * {@link Collection} interface, {@link Stream}s do not have subinterfaces nor
 * optional operations, and concrete Streams, do not necessary implement all
 * them in an efficient way.</li>
 * <li>Functional style: Stream methods names follow conventions that will be
 * familiar to any functional programmer. However, please notice that being
 * named in a functional way does not mean they pure functional. Such methods
 * will be side-effect-free and transparent only as long as source meets certain
 * conditions.</li>
 * <li>Unmodifiable: Streams do not expose any method that mutates its source
 * nor its elements, and its iterators do not support the
 * {@link Iterator#remove()}. However, certain sources and elements may still be
 * mutated externally, and iteration over the source might mutate them.</li>
 * </ul>
 * On the other hand, conditional properties are:
 * <ul>
 * <li>Repeatable iteration order: having an {@link Iterable} a repeatable
 * iteration order means that iterating over the stream does not alter by itself
 * the elements and elements retrieval order of the next iteration. In other
 * words, an iterable is repeatable if and only if:
 * 
 * <pre>
 * Streams.from(iterable).elementsEquals(iterable);
 * </pre>
 * 
 * Is always <code>true</code>, as long as the iterable or its elements are not
 * externally mutated. Streams have this property as long as its source has it -
 * for example {@link Iterator}s are a non repeatable iteration order source.
 * Streams that always ensure this property are marked as {@link Repeatable}.
 * Streams that may have it depending on its source are marked as
 * {@link ConditionallyRepeatable}</li>
 * <li>Immutability TODO</li>
 * <li>Referential transparency TODO</li>
 * <li>Random access TODO</li>
 * </ul>
 * 
 * <h2>Reference semantics</h2>
 * TODO Streams are not values.
 * 
 * <h2>Concrete Streams</h2>
 * 
 * TODO
 * 
 * 
 * @author fbulgarelli
 * 
 * @param <A>
 *          the type of object the stream is source of
 */
@Unmodifiable
@ConditionallyImmutable
public interface Stream<A> extends //
	Accessible<A>, //
	Appendable<A>,//
	Collectible<A>, //
	ContainsAware<A>, //
	Deconstructable<A>, //
	Filterable<A>, //
	Foldable<A>,//
	ImplicitNumberType<A>, //
	Interscalable<A>, //
	Iterable<A>, //
	Mappable<A>, //
	Reversable<A>, //
	Searchable<A>,//
	SizeAware, //
	Sortable<A>, //
	Testeable<A>, //
	Zippeable<A> {

	/**
	 * Lazily applies the given function to this {@link Stream}
	 * 
	 * @param <B>
	 * @param function
	 *          the function to apply to this stream
	 * @return a new stream that will retrieve elements from the result of
	 *         applying the given function to this stream
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	<B> Stream<B> then(@NonNull Applicable<Stream<A>, ? extends Iterable<B>> function);

}
