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


package net.sf.staccatocommons.lang;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.builder.Builder;
import net.sf.staccatocommons.lang.builder.BuilderAlreadyUsedException;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Unmodifiable;

/**
 * a {@link Builder} for {@link Collection}s. With the exception of
 * {@link #from(Collection, Applicable)}, all its factory methods grant to
 * return CollectionBuilder that build {@link Unmodifiable} collections
 * 
 * @author flbulgarelli
 */
public class CollectionBuilder<A, B extends Collection<A>> implements Builder<B> {

  private B list;
  private Applicable<B, B> wrapperFunction;

  /**
   * Creates a new {@link CollectionBuilder}
   * 
   * @param list
   *          the list to be configured by this builder
   * @param wrapperFunction
   */
  public CollectionBuilder(@NonNull B list, Applicable<B, B> wrapperFunction) {
    this.list = list;
    this.wrapperFunction = wrapperFunction;
  }

  /**
   * Adds an element to the list
   * 
   * @param element
   * @return this builder
   */
  @NonNull
  public CollectionBuilder<A, B> with(A element) {
    list.add(element);
    return this;
  }

  /**
   * Adds an element to the list if it satisfies the given condition
   * 
   * @param element
   * @param evaluable
   * @return this
   */
  @NonNull
  public CollectionBuilder<A, B> withIf(A element, @NonNull Evaluable<? super A> evaluable) {
    if (evaluable.eval(element))
      with(element);
    return this;
  }

  /**
   * Sets the wrapper function, that is, the {@link Applicable} to apply to the
   * resulting collection before returning it through {@link #build()}.
   * 
   * By default, collections are wrapped with unmodifiable wrappers, but this
   * behavior can be changed through this method.
   * 
   * @param wrapperFunction
   * @return this
   */
  @NonNull
  public CollectionBuilder<A, B> withWrapper(@NonNull Applicable<B, B> wrapperFunction) {
    this.wrapperFunction = wrapperFunction;
    return this;
  }

  /**
   * Disables wrapping, which means that a built collection will be returned
   * just as it was created.
   * 
   * As a consequence, the collection returned by {@link #build()} will be
   * modifiable.
   * 
   * @return this
   */
  @NonNull
  public CollectionBuilder<A, B> unwrap() {
    return withWrapper(Functions.<B> identity());
  }

  @NonNull
  public B build() throws BuilderAlreadyUsedException {
    if (list == null)
      throw new BuilderAlreadyUsedException();
    B list = this.list;
    this.list = null;
    return wrapperFunction.apply(list);
  }

  /**
   * Answers a new {@link CollectionBuilder} that configures a
   * {@link Collection}
   * 
   * @param <A>
   * @return a new {@link CollectionBuilder} that builds a {@link Collection}
   */
  @NonNull
  public static <A> CollectionBuilder<A, Collection<A>> from(@NonNull Collection<A> collection) {
    return from(collection, CollectionBuilder.<A> toUnmodifiableCollection());
  }

  /**
   * Answers a new {@link CollectionBuilder} that configures a {@link SortedSet}
   * , with the given element already added
   * 
   * @param <A>
   * @return a new {@link CollectionBuilder} that builds a {@link SortedSet}
   */
  @NonNull
  public static <A> CollectionBuilder<A, SortedSet<A>> sortedSetWith(A element) {
    return from(new TreeSet<A>(), CollectionBuilder.<A> toUnmodifiableSortedSet()).with(element);
  }

  /**
   * Answers a new {@link CollectionBuilder} that configures a {@link Set}, with
   * the given element already added
   * 
   * @param <A>
   * @return a new {@link CollectionBuilder} that builds a set
   */
  @NonNull
  public static <A> CollectionBuilder<A, Set<A>> setWith(A element) {
    return from(new HashSet<A>(), CollectionBuilder.<A> toUnmodifiableSet()).with(element);
  }

  /**
   * Answers a new {@link CollectionBuilder} that configures a {@link List},
   * with the given element already added. The list built this builder grants to
   * be {@link Unmodifiable}
   * 
   * @param <A>
   * @return a new {@link CollectionBuilder} that builds lists
   */
  @NonNull
  public static <A> CollectionBuilder<A, List<A>> listWith(A element) {
    return from(new LinkedList<A>(), CollectionBuilder.<A> toUnmodifiableList()).with(element);
  }

  @NonNull
  private static <A, B extends Collection<A>> CollectionBuilder<A, B> from(@NonNull B collection,
    @NonNull Applicable<B, B> immutator) {
    return new CollectionBuilder<A, B>(collection, immutator);
  }

  /**
   * Answers a constant function that returns an {@link Unmodifiable} view of
   * its {@link SortedSet} argument
   * 
   * @param <A>
   * @return a function
   */
  @Constant
  public static <A> Applicable<Set<A>, Set<A>> toUnmodifiableSet() {
    return new AbstractFunction<Set<A>, Set<A>>() {
      public Set<A> apply(Set<A> arg) {
        return Collections.unmodifiableSet(arg);
      }
    };
  }

  /**
   * Answers a constant function that returns an {@link Unmodifiable} view of
   * its {@link SortedSet} argument
   * 
   * @param <A>
   * @return a function
   */
  @Constant
  public static <A> Applicable<SortedSet<A>, SortedSet<A>> toUnmodifiableSortedSet() {
    return new AbstractFunction<SortedSet<A>, SortedSet<A>>() {
      public SortedSet<A> apply(SortedSet<A> arg) {
        return Collections.unmodifiableSortedSet(arg);
      }
    };
  }

  /**
   * Answers a constant function that returns an {@link Unmodifiable} view of
   * its list argument
   * 
   * @param <A>
   * @return a function
   */
  @Constant
  public static <A> Applicable<List<A>, List<A>> toUnmodifiableList() {
    return new AbstractFunction<List<A>, List<A>>() {
      public List<A> apply(List<A> arg) {
        return Collections.unmodifiableList(arg);
      }
    };
  }

  /**
   * Answers a constant function that returns an {@link Unmodifiable} view of
   * its collection argument
   * 
   * @param <A>
   * @return a function
   */
  @Constant
  public static <A> Applicable<Collection<A>, Collection<A>> toUnmodifiableCollection() {
    return new AbstractFunction<Collection<A>, Collection<A>>() {
      public Collection<A> apply(Collection<A> arg) {
        return Collections.unmodifiableCollection(arg);
      }
    };
  }

}
