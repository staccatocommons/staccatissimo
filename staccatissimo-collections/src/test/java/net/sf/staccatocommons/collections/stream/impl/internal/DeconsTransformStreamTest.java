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


package net.sf.staccatocommons.collections.stream.impl.internal;

import static net.sf.staccatocommons.lang.Compare.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.AbstractDeconsApplicable;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.collections.stream.Transformable.DeconsApplicable;
import net.sf.staccatocommons.defs.Evaluable;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class DeconsTransformStreamTest {

  /**
   * Dropwhile definition in functional-style. First equation is inherited from
   * {@link AbstractDeconsApplicable}
   * 
   * <pre>
   * dw _ [] = []
   * dw f (x:xs) | f x = dw f xs
   *             | otherwise  = x:xs
   * </pre>
   * */
  public static <A> Stream<A> dropWhile(final Evaluable<A> pred, Stream<A> stream) {
    return stream.transform(new AbstractDeconsApplicable<A, A>() {
      public Stream<A> apply(A head, Stream<A> tail) {
        if (pred.eval(head))
          return dropWhile(pred, tail);
        return Streams.cons(head, tail);
      }
    });
  }

  /**
   * Highlevel test that defines recursively a lazy dropWhile function
   * compatible with {@link Stream#dropWhile(Evaluable)}, but in a recursive way
   * using {@link Stream#transform(DeconsApplicable)}
   * 
   * @throws Exception
   */
  @Test
  public void testThen() throws Exception {
    assertEquals(Arrays.asList(1, 9, 2, 0), dropWhile(greaterThan(5), Streams.cons(1, 9, 2, 0)).toList());
    assertEquals(Arrays.asList(), dropWhile(greaterThanOrEqualTo(0), Streams.cons(1, 9, 2, 0)).toList());
    assertEquals(Arrays.asList(1, 9, 2, 0), dropWhile(greaterThanOrEqualTo(2), Streams.cons(1, 9, 2, 0)).toList());
    assertEquals(Arrays.asList(0), dropWhile(greaterThanOrEqualTo(1), Streams.cons(1, 9, 2, 0)).toList());
  }

}
