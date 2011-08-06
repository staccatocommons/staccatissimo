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

package net.sf.staccatocommons.io;

import java.io.EOFException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.Reader;
import java.util.Scanner;

import net.sf.staccatocommons.collections.internal.iterator.NextOptionIterator;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.io.restrictions.KeepOpen;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.restrictions.check.NonNull;

import org.apache.commons.io.LineIterator;

/**
 * Class methods for creating {@link Stream}s that retrieve elements from
 * different IO sources
 * 
 * @author flbulgarelli
 */
public class IOStreams {

  private IOStreams() {}

  /**
   * Answers a {@link Stream} that retrieves words from the given
   * {@link Readable}
   * 
   * @param readable
   * @return a new single-iteration {@link Stream}
   */
  @NonNull
  public static Stream<String> fromWords(@KeepOpen @NonNull Readable readable) {
    return Streams.from(new Scanner(readable));
  }

  @NonNull
  public static Stream<String> fromWords(@NonNull InputStream in) {
    return fromWords(new InputStreamReader(in));
  }

  /**
   * Answers a {@link Stream} that retrieves lines from the given
   * {@link Readable}
   * 
   * @param readable
   * @return a new single-iteration {@link Stream}
   */
  @NonNull
  public static Stream<String> fromLines(@KeepOpen @NonNull Reader readable) {
    return Streams.from(new LineIterator(readable));
  }

  @NonNull
  public static Stream<String> fromLines(@KeepOpen @NonNull InputStream in) {
    return fromLines(new InputStreamReader(in));
  }

  /**
   * Answers a {@link Stream} that retrieves tokens that match the given regular
   * expression from the given {@link Readable}
   * 
   * @param readable
   * @param regexp
   * @return a new single-iteration {@link Stream}
   */
  @NonNull
  public static Stream<String> fromTokens(@KeepOpen @NonNull Readable readable, @NonNull String regexp) {
    return Streams.from(new Scanner(readable).useDelimiter(regexp));
  }

  @NonNull
  public static Stream<String> fromTokens(@KeepOpen @NonNull InputStream in, @NonNull String regexp) {
    return fromTokens(new InputStreamReader(in), regexp);
  }

  /**
   * Answers a {@link Stream} that retrieves objects from the given
   * {@link ObjectInput}
   * 
   * @param <A>
   *          the type of object to read from the given <code>redable</code>
   * @param readable
   *          an {@link ObjectInput} that is the source of the returned
   *          {@link Stream}
   * @return a new single-iteration {@link Stream}
   */
  @NonNull
  public static <A> Stream<A> fromObjects(@KeepOpen @NonNull final ObjectInput readable) {
    return Streams.from(new NextOptionIterator<A>() {
      protected Option<A> nextOption() {
        try {
          return Option.some((A) readable.readObject());
        } catch (EOFException e) {
          return Option.none();
        } catch (Exception e) {
          throw SoftException.soften(e);
        }
      }
    });
  }
}
