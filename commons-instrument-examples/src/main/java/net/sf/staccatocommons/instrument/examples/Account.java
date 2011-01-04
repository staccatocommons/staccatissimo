package net.sf.staccatocommons.instrument.examples;

import java.math.BigDecimal;
import java.util.logging.Logger;

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

/**
 * @author flbulgarelli
 * 
 */
public class Account {

	private BigDecimal balance;

	private static final Logger logger = Logger.getLogger(Account.class.getCanonicalName());

	public Account(BigDecimal balance) {
		this.balance = balance;
	}

	public void deposit(BigDecimal amount) {
		if (amount == null) {
			String message = "amount must not be null";
			logger.severe(message);
			throw new IllegalArgumentException(message);
		}
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			String message = "amount must be positive";
			logger.severe(message);
			throw new IllegalArgumentException(message);
		}
		balance = balance.add(amount);
		logger.warning("deposit");
	}

	public void resetBalance() {
		balance = BigDecimal.ZERO;
		logger.warning("resetBalance");
	}

	public BigDecimal getBalance() {
		return balance;
	}

}