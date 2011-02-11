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
package net.sf.staccatocommons.check.examples.phone;

import net.sf.staccatocommons.check.Assert;
import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.check.Validate;

/**
 * @author flbulgarelli
 * 
 */
public class PhoneCall3 {

	private final PhoneLine line;
	private final CallDestination destination;
	private CallLog log;

	/**
	 * Creates a new {@link PhoneCall}. Line and destination must being nonnull
	 * and destination must be included in allowed destinations set of the
	 * PhoneLine
	 */
	public PhoneCall3(PhoneLine line, CallDestination destination) {
		Ensure.that()//
			.isNotNull("line", line)
			.isNotNull("destination", destination)
			.contains("line.allowedDestinations", line.getAllowedDestinations(), destination);
		this.line = line;
		this.destination = destination;
	}

	/**
	 * Starts the call. In order to succeed, the line must be free, the
	 * destination reachable, and the calllog set. If not of such conditions is
	 * met, a {@link PhoneCallException} is thrown.
	 * 
	 * A postcondition of connect id that, if succeeds, the line must be busy.
	 **/
	public void connect() throws PhoneCallException {
		Validate
			.throwing(PhoneCallException.class)
			.that(!line.isBusy(), "The line %s is busy", line)
			.that(destination.isReachable(), "The destination %s is unreachable", destination)
			.that(log != null, "The log has not being set");

		// implementation of connect

		Assert.that(line.isBusy(), "The line %s should be busy", line);
	}

	public void setLog(CallLog log) {
		Ensure.isNotNull("log", log);
		this.log = log;
	}

	@SuppressWarnings("serial")
	public static class PhoneCallException extends Exception {
		/**
		 * Creates a new {@link PhoneCall.PhoneCallException}
		 */
		public PhoneCallException(String message) {
			super(message);
		}
	}

}
