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
package net.sf.staccatocommons.lang.number;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author flbulgarelli
 * 
 */
public class Numbers {

	public static BigDecimal d(long val) {
		return BigDecimal.valueOf(val);
	}

	public static BigDecimal d(long val, int scale) {
		return BigDecimal.valueOf(val, scale);
	}

	public static BigInteger i(long val) {
		return BigInteger.valueOf(val);
	}

}
