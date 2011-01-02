package net.sf.staccato.commons.instrument.examples;

import java.math.BigDecimal;

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
public class Account2 {

	private BigDecimal balance;

	public Account2(BigDecimal balance) {
		this.balance = balance;
	}

	@Loggeable
	public void deposit(@NonNegativeDecimal("amount") BigDecimal amount) {
		balance = balance.add(amount);
	}

	@Loggeable
	public void resetBalance() {
		balance = BigDecimal.ZERO;
	}

	public BigDecimal getBalance() {
		return balance;
	}

}