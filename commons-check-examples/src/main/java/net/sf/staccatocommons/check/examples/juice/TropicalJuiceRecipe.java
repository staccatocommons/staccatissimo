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

package net.sf.staccatocommons.check.examples.juice;

import java.util.Collection;
import java.util.Date;

/**
 * A Juice recipe based on bananas and pineapples
 */
@SuppressWarnings("unused")
class TropicalJuiceRecipe implements JuiceRecipe {

	private final int suger;
	private final OrangeJuice juice;
	private final Collection<Banana> bananas;
	private final Collection<Pineapple> pineapples;

	/**
	 * 
	 * Creates a new {@link TropicalJuiceRecipe}
	 * 
	 * It takes orange juice, some sugar, some bananas and at least 2 pinaples.
	 * 
	 * Juice must not have expired and that it must not be too acid.
	 */
	public TropicalJuiceRecipe(int sugar, OrangeJuice juice, Collection<Banana> bananas,
		Collection<Pineapple> pineapples) {
		// So lets ensure that input is right!

		if (juice == null)
			throw new IllegalArgumentException("juice must not be null");

		if (!juice.isNotTooAcid())
			throw new IllegalArgumentException("juice.isNotTooAcide must not be true");

		if (juice.getExpirationDate().compareTo(new Date()) >= 0)
			throw new IllegalArgumentException("juice.expirationDate must be greater than today");

		if (sugar <= 0)
			throw new IllegalArgumentException("sugar must be be greather than zero");

		if (bananas == null)
			throw new IllegalArgumentException("bananas must not be null");

		if (bananas.isEmpty())
			throw new IllegalArgumentException("bananas must not be empty");

		if (pineapples == null)
			throw new IllegalArgumentException("pineapples must be null");

		if (pineapples.size() < 2)
			throw new IllegalArgumentException("pineapples must be of at least size 2");

		this.suger = sugar;
		this.juice = juice;
		this.bananas = bananas;
		this.pineapples = pineapples;

	}

	public Juice prepare() {
		// Don't care....
		return null;
	}
}