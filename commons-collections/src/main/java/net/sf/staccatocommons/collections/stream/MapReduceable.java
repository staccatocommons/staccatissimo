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

import java.util.Map;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;

/**
 * @author flbulgarelli
 * 
 */
public interface MapReduceable<A> {

  <K> Map<K, A> mapReduce(Applicable<? super A, K> groupFunction, Applicable2<? super A, ? super A, A> reduceFunction);

  <K, V> Map<K, V> mapReduce(Applicable<? super A, K> groupFunction, Applicable<? super A, V> mapFunction,
    Applicable2<? super V, ? super V, V> reduceFunction);

  <K, V> Map<K, V> mapReduce(Applicable<? super A, K> groupFunction, V initial,
    Applicable2<? super V, ? super A, V> foldFunction);
}
