package net.sf.staccatocommons.check.examples.juice;

import java.util.Collection;
import java.util.Date;

import net.sf.staccatocommons.check.Ensure;

/** Same receipt than {@link TropicalJuiceRecipe}, but using commons-check */
@SuppressWarnings("unused")
class TropicalJuiceRecipe2 implements JuiceRecipe {

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
	public TropicalJuiceRecipe2(int sugar, OrangeJuice juice, Collection<Banana> bananas,
		Collection<Pineapple> pineapples) {
		Ensure
			.that()
			.isTrue("juice.notTooAcid", juice.isNotTooAcid())
			.isGreaterThan("juice.expirationDate", juice.getExpirationDate(), new Date())
			.isPositive("sugar", sugar)
			.isNotEmpty("bananas", bananas)
			.isMinSize("pineapples", pineapples, 2);
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