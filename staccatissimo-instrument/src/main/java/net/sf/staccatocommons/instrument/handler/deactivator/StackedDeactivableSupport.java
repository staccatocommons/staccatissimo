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

package net.sf.staccatocommons.instrument.handler.deactivator;

/**
 * @author flbulgarelli
 * 
 */
public class StackedDeactivableSupport implements Deactivable {

  private int activationCounter = -1;

  /**
   * Creates a new {@link StackedDeactivableSupport} initially inactive
   */
  public StackedDeactivableSupport() {
  }

  public void activate() {
    activationCounter++;
  }

  public void deactivate() {
    activationCounter--;
  }

  /**
   * Answers if this {@link Deactivable} is active, that is, if the number of
   * activations - times {@link #activate()} has been sent to it - is greater
   * than the number of deactivations - times {@link #deactivate()} has been
   * sent to it.
   * 
   * @return is this deactivable is active
   */
  public boolean isActive() {
    return activationCounter >= 0;
  }

  // public int getRemainingActivations() {
  // return -activationCounter;
  // }

}
