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

public class PhoneCall {

	private final PhoneLine line;
	private final CallDestination destination;
	private CallLog log;

	/**
	 * Creates a new {@link PhoneCall}. Line and destination must being nonnull
	 * and destination must be included in allowed destinations set of the
	 * PhoneLine
	 */
	public PhoneCall(PhoneLine line, CallDestination destination) {
		if (line == null)
			throw new IllegalArgumentException("line must not be null");

		if (destination == null)
			throw new IllegalArgumentException("Destinantion must not be null");

		if (!line.getAllowedDestinations().contains(destination))
			throw new IllegalArgumentException("line.allowedDestinantions must contain" + destination);

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
		if (line.isBusy())
			throw new PhoneCallException("The line " + line + " is busy");

		if (!destination.isReachable())
			throw new PhoneCallException("The destination " + destination + " is unreachable");

		if (log == null)
			throw new PhoneCallException("The log has not been set");

		// ...implementation of connect...

		if (!line.isBusy())
			throw new AssertionError("The line " + line + " should be busy now");
	}

	public void setLog(CallLog log) {
		if (log == null)
			throw new IllegalArgumentException("log must not be null");

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
