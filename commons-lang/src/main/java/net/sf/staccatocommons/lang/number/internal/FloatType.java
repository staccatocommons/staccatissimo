package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class FloatType extends AbstractNumberType<Float> {

	private static final long serialVersionUID = 5307960326139220406L;
	/**
	 * An instance
	 */
	public static final NumberType<Float> TYPE = new FloatType();

	public Float add(Float n0, Float n1) {
		return n0 + n1;
	}

	public Float multiply(Float n0, Float n1) {
		return n0 * n1;
	}

	public Float divide(Float n0, Float n1) {
		return n0 / n1;
	}

	public Float negate(Float n) {
		return -n;
	}

	public Float zero() {
		return 0f;
	}

	public Float one() {
		return 1f;
	}

	public Float increment(Float n) {
		return n + 1;
	}

	public Float decrement(Float n) {
		return n - 1;
	}
}