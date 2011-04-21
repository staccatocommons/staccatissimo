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

import java.io.Serializable;
import java.util.Collection;

import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.SizeAware;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.number.NumberTypeAware;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.restrictions.effect.SideEffectFree;
import net.sf.staccatocommons.restrictions.effect.Transparent;
import net.sf.staccatocommons.restrictions.value.Unmodifiable;

/**
 * A {@link Stream} is a lazy, rich-interfaced, functional-style,
 * {@link Iterable} object that can retrieve an arbitrary - and potentially
 * unbound - amount of other objects of a parameterized type.
 * 
 * <h2>Introduction</h2>
 * <p>
 * Streams generalize the concept of a source of objects. They re rich rich,
 * lazy {@link Iterable} wrappers that expose many methods analogous to most of
 * the side-effect-free operation of the {@link Collection} interface, and
 * adding dozens of new ones. However, Streams are not meant to replace the <a
 * href=
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
 * Stream's <em>source</em>. So, with a few exceptions, Streams are just
 * wrappers around other object.
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
 * </li>
 * <li>Rich interfaced: {@link Stream}s offer a highly rich interface, this
 * interface exposes more than 50 methods. However, not like the
 * {@link Collection} interface, {@link Stream}s do not have subinterfaces nor
 * optional operations.</li>
 * <li>Functional style: Stream methods names follow conventions that will be
 * familiar to any functional programmer. However, please notice that being
 * named in a functional way does not mean they are pure functional. Such
 * methods will be {@link SideEffectFree} and {@link Transparent} only as long
 * as source meets certain conditions.</li>
 * <li>Operation oriented: Streams are not persistent classes, and do not
 * implement {@link Serializable}. They are normally created, used, and disposed
 * in the scope of a method execution, whenever a chain of operations over the
 * stream's source need to be performed. Thus, they should not be used as
 * attributes of long-living objects.</li>
 * </ul>
 * 
 * On the other hand, conditional properties are:
 * <ul>
 * <li>Repeatable iteration order: having an {@link Iterable} a repeatable
 * iteration order means that iterating over the stream - either directly or
 * indirectly through any of its methods - does not alter by itself the elements
 * and elements retrieval order of the next iteration. In other words, an
 * iterable is repeatable if and only if:
 * 
 * <pre>
 * Streams.from(iterable).equiv(iterable);
 * </pre>
 * 
 * Is always <code>true</code>, as long as the iterable or its elements are not
 * externally mutated. Only those streams marked as {@link Repeatable} grant to
 * meet such property. However, any Stream can be converted into an efficient
 * {@link Repeatable} by sending {@link #memorize()} or {@link #force()}.
 * Consult their javadoc for details.</li>
 * <li>Streams are in the general case not {@link Unmodifiable}, as streams with
 * modifiable sources like iterators will be modified by any of the stream
 * methods not marked as {@link SideEffectFree}.</li>
 * <li>Referential transparency TODO</li>
 * <li>Random access TODO</li>
 * </ul>
 * 
 * <h2>Reference semantics</h2>
 * TODO Streams are not values.
 * 
 * <h2>Concrete Streams</h2>
 * 
 * Staccato-Commnons-Collections offers several concrete implementations of
 * streams. They fall in three categories:
 * <ul>
 * <li>Streams that decorate collections, or that are standalone.
 * {@link Streams} is a class methods hub for instantiating such streams</li>
 * <li>Streams that are built on top of {@link Sequence}s. {@link Iterate} is a
 * class methods hub for instantiating such streams</li>
 * <li>Streams that are built specifying its elements, or in a head-tail manner.
 * {@link Cons} is a class methods hub for instantiating such streams</li>
 * </ul>
 * 
 * Aside from these concrete streams, client code may also implement new ones.
 * In order to do that, it <strong>must not</strong> implement this interface
 * directly, but inherit from {@link AbstractStream}, which implements all
 * methods except of {@link #iterator()}
 * 
 * @author fbulgarelli
 * 
 * @param <A>
 *          the type of object the stream is source of
 */
public interface Stream<A> extends //
	Accessible<A>, //
	Appendable<A>,//
	Collectible<A>, //
	ContainsAware<A>, //
	Crossable<A>, //
	Deconstructable<A>, //
	Filterable<A>, //
	Foldable<A>,//
	NumberTypeAware<A>, //
	Interscalable<A>, //
	Iterable<A>, //
	Mappable<A>, //
	Reversable<A>, //
	Searchable<A>,//
	SizeAware, //
	Sortable<A>, //
	Testeable<A>, //
	Transformable<A>, //
	Zippeable<A> {

	Thriterator<A> iterator();

}
