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
package net.sf.staccatocommons.restrictions.instrument;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import net.sf.staccatocommons.instrument.config.InstrumenterConfiguration;
import net.sf.staccatocommons.instrument.config.InstrumenterConfigurer;
import net.sf.staccatocommons.instrument.handler.AnnotationHandler;
import net.sf.staccatocommons.instrument.handler.deactivator.Deactivable;
import net.sf.staccatocommons.restrictions.instrument.check.MatchesHandler;
import net.sf.staccatocommons.restrictions.instrument.check.MaxSizeHandler;
import net.sf.staccatocommons.restrictions.instrument.check.MinSizeHandler;
import net.sf.staccatocommons.restrictions.instrument.check.NotEmptyHandler;
import net.sf.staccatocommons.restrictions.instrument.check.NotNegativeHandler;
import net.sf.staccatocommons.restrictions.instrument.check.NotNullHandler;
import net.sf.staccatocommons.restrictions.instrument.check.PositiveHandler;
import net.sf.staccatocommons.restrictions.instrument.check.SizeHandler;

/**
 * @author flbulgarelli
 * 
 */
public class RestrictionConfigurer implements InstrumenterConfigurer {

  private final boolean ignoreReturnChecks;
  private final boolean ignoreChecks;
  private final boolean ignoreConstants;

  /**
   * Creates a new {@link RestrictionConfigurer}
   */
  public RestrictionConfigurer(boolean ignoreCheckReturns, boolean ignoreChecks, boolean ignoreConstants) {
    this.ignoreReturnChecks = ignoreCheckReturns;
    this.ignoreChecks = ignoreChecks;
    this.ignoreConstants = ignoreConstants;
  }

  public void configureInstrumenter(InstrumenterConfiguration instrumenter) {
    IgnoreCheckHandler ignoreCheckHandler = new IgnoreCheckHandler();
    ForceChecksHandler forceCheckHandler = new ForceChecksHandler();

    instrumenter.addAnnotationHanlder(ignoreCheckHandler).addAnnotationHanlder(forceCheckHandler);

    Collection<AnnotationHandler<?>> handlers = new LinkedList<AnnotationHandler<?>>();

    if (!ignoreChecks) {
      handlers.addAll(Arrays.asList( //
        new NotNullHandler(ignoreReturnChecks),
        new SizeHandler(ignoreReturnChecks),
        new NotEmptyHandler(ignoreReturnChecks),
        new PositiveHandler(ignoreReturnChecks),
        new MatchesHandler(ignoreReturnChecks),
        new NotNegativeHandler(ignoreReturnChecks),
        new MinSizeHandler(ignoreReturnChecks),
        new MaxSizeHandler(ignoreReturnChecks)));
    }
    if (!ignoreConstants) {
      handlers.add(new ConstantHandler());
    }
    for (AnnotationHandler handler : handlers) {
      ignoreCheckHandler.addDeactivable((Deactivable) handler);
      forceCheckHandler.addDeactivable((Deactivable) handler);
      instrumenter.addAnnotationHanlder(handler);
    }
    instrumenter.setInstrumentationMark(RestrictionInstrumentationMark.INSTANCE);
  }
}
