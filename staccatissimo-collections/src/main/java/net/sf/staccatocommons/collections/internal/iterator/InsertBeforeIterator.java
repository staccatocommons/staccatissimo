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

/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.internal.iterator;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class InsertBeforeIterator<A> extends AbstractInsertBeforeIterator<A> {
  private A reference;

  private State state = State.NOT_INSERTED_YET;
  private boolean updated;

  /**
   * Creates a new {@link InsertBeforeIterator}
   */
  public InsertBeforeIterator(@NonNull A reference, A element, @NonNull Thriterator<A> iterator) {
    super(element, iterator);
    this.reference = reference;
  }
  public boolean hasNext() {
    updateState();
    return state != State.END;
  }

  void updateState() {
    if (!updated)
      state = state.nextState(iterator(), reference);
    updated = true;
  }

  public void advanceNext() {
    if (!hasNext())
      throw new NoSuchElementException("End of source");
    updated = false;
  }

  protected boolean atInsertionPoint() {
    return state == State.INSERTING_LAST || state == State.INSERTING;
  }
  
  private enum State {
    NOT_INSERTED_YET {
      State nextState(Thriterator<?> iter, Object reference) {
        if (!iter.hasNext())
          return INSERTING_LAST;
        iter.advanceNext();
        if (reference.equals(iter.current()))
          return INSERTING;
        return NOT_INSERTED_YET;
      }
    },
    INSERTING {
      State nextState(Thriterator<?> iter, Object reference) {
        return ALREADY_INSERTED;
      }
    },
    ALREADY_INSERTED {
      State nextState(Thriterator<?> iter, Object reference) {
        if (!iter.hasNext())
          return END;
        iter.advanceNext();
        return ALREADY_INSERTED;
      }
    },
    INSERTING_LAST {
      State nextState(Thriterator<?> iter, Object reference) {
        return END;
      }
    },
    END {
      State nextState(Thriterator<?> iter, Object reference) {
        throw new AssertionError();
      }
    };
    abstract State nextState(Thriterator<?> iter, Object reference);
  }
}
