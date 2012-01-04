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

package net.sf.staccatocommons.collections.iterable;

import static net.sf.staccatocommons.collections.iterable.internal.IterablesInternal.*;

import java.util.Collection;
import java.util.Iterator;

import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * A bunch of static methods that extend the {@link java.util.Collections}
 * functionality, providing common algorithms for collections and iterables.
 * 
 * These methods will not work with unmodifiable collections, as may modify
 * input collections state, and thus we highly encourage to use
 * {@link Iterables} instead, when possible
 * 
 * @author flbulgarelli
 * 
 */
public class ModifiableIterables {

  /*
   * Removing
   */

  /**
   * Removes from the given iterable all the elements that evaluate to true
   * 
   * @param iterable
   * @param predicate
   * @param <T>
   * @param <I>
   * @return the given iterable
   */
  @NonNull
  public static <T, I extends Iterable<T>> I removeAll(@NonNull I iterable, @NonNull Evaluable<? super T> predicate) {
    for (Iterator<T> iter = iterable.iterator(); iter.hasNext();)
      if (predicate.eval(iter.next()))
        iter.remove();
    return iterable;
  }

  /**
   * Removes elements from the given iterable, while each element evaluates to
   * true
   * 
   * @param <T>
   * @param iterable
   * @param predicate
   */
  public static <T, I extends Iterable<T>> I removeWhile(@NonNull I iterable, @NonNull Evaluable<? super T> predicate) {
    for (Iterator<? extends T> iter = iterable.iterator(); iter.hasNext() && predicate.eval(iter.next());)
      iter.remove();
    return iterable;
  }

  /**
   * Removes up to N elements from the given iterable
   * 
   * @param <I>
   * 
   * @param iterable
   * @param amountOfElements
   *          Non negative
   * @return the given iterable
   */
  @NonNull
  public static <I extends Iterable<?>> I remove(@NonNull I iterable, @NotNegative int amountOfElements) {
    Iterator<?> iter = iterable.iterator();
    for (int i = 0; i < amountOfElements && iter.hasNext(); i++)
      iter.remove();
    return iterable;
  }

  /*
   * Adding
   */

  /**
   * Adds all the elements from a given {@link Iterable} to a given collection
   * 
   * @param collection
   * @param iterable
   * @param <T>
   * @param <C>
   * @return the given collection
   * 
   */
  @NonNull
  public static <T, C extends Collection<T>> C addAll(@NonNull C collection, @NonNull Iterable<? extends T> iterable) {
    addAllInternal(collection, iterable);
    return collection;
  }

  /*
   * Moving
   */
  /**
   * Removes all elements from the given iterable that evalute to true, and adds
   * them to the given collection
   * 
   * @param iterable
   * @param collection
   * @param predicate
   * @param <T>
   * @param <C>
   * @return the given collection
   * 
   */
  @NonNull
  public static <T, C extends Collection<T>> C move(@NonNull Iterable<T> iterable, @NonNull C collection,
    @NonNull Evaluable<T> predicate) {
    for (Iterator<T> iter = iterable.iterator(); iter.hasNext();) {
      T element = iter.next();
      if (predicate.eval(element)) {
        iter.remove();
        collection.add(element);
      }
    }
    return collection;
  }

  /**
   * Removes at most n elements from the given iterable, and adds it to the
   * output collection
   * 
   * @param <T>
   * @param <C>
   * @param iterable
   * @param amountOfElements
   *          the max amount of elements to select from the iterable. Must be >=
   *          0
   * @param collection
   * @return the output collection
   */
  @NonNull
  public static <T, C extends Collection<T>> C move(@NonNull Iterable<T> iterable, @NotNegative int amountOfElements,
    @NonNull C collection) {
    Iterator<? extends T> iter = iterable.iterator();
    for (int i = 0; i < amountOfElements && iter.hasNext(); i++) {
      collection.add(iter.next());
      iter.remove();
    }
    return collection;
  }

}
