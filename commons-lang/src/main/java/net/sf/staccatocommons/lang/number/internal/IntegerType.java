package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class IntegerType extends AbstractNumberType<Integer> {

	/**
	 * An instance
	 */
	public static final IntegerType TYPE = new IntegerType();

	public Integer add(Integer n0, Integer n1) {
		return n0 + n1;
	}

	public Integer multiply(Integer n0, Integer n1) {
		return n0 * n1;
	}

	public Integer divide(Integer n0, Integer n1) {
		return n0 / n1;
	}

	public Integer negate(Integer n) {
		return -n;
	}

	public Integer zero() {
		return 0;
	}

	public Integer one() {
		return 1;
	}

	public Integer increment(Integer n) {
		return ++n;
	}

	public Integer decrement(Integer n) {
		return --n;
	}
}