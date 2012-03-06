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
package net.sf.staccatocommons.collections.stream;

import java.util.NoSuchElementException;

import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public class InsertBeforeIterator<A> extends AdvanceThriterator<A> {
  private A reference;
  private final A element;
  private final Thriterator<A> iterator;

  private State state = State.NOT_INSERTED_YET;
  private boolean updated;

  private enum State {
	  NOT_INSERTED_YET, INSERTING, ALREADY_INSERTED, INSERTING_LAST, END
  }
  
  public InsertBeforeIterator(@NotNegative A reference, A element, @NonNull Thriterator<A> iterator) {
    this.reference = reference;
    this.element = element;
    this.iterator = iterator;
  }

  /**/
  public boolean hasNext() {
	updateState();
    return state != State.END;
  }
  
  void updateState() {
	  if(!updated)
	    state = getState();
	  updated = true;
  }
  
  State getState() {
	  switch(state) {
	  case NOT_INSERTED_YET:
		  if(!iterator.hasNext()) 
			  return  State.INSERTING_LAST;
		  iterator.advanceNext();
		  if(reference.equals(iterator.current())) 
			  return State.INSERTING;
		  return State.NOT_INSERTED_YET;
	  case INSERTING:
		  return State.ALREADY_INSERTED;
	  case ALREADY_INSERTED:
		  if(!iterator.hasNext()) 
			  return State.END;
		  iterator.advanceNext();
		  return State.ALREADY_INSERTED;
	  case INSERTING_LAST:
		  return State.END; 
	  default:
		  throw new AssertionError();
	  }
  }

  /**/

  public void advanceNext() {
	 if(!hasNext())
		 throw new NoSuchElementException("End of source");
	 updated = false;
  }

  /**/
  public A current() {
    return atInsertionPoint() ? element : iterator.current();
  }

private boolean atInsertionPoint() {
	return state == State.INSERTING_LAST || state == state.INSERTING;
}

  public Thunk<A> delayedCurrent() {
    return atInsertionPoint() ? Thunks.constant(element) : iterator.delayedCurrent();
  }
  /**/
}
