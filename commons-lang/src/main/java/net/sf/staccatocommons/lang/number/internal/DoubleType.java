package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class DoubleType extends AbstractNumberType<Double> {

	private static final long serialVersionUID = 2443701983046831362L;
	/**
	 * An instance
	 */
	public static final NumberType<Double> TYPE = new DoubleType();

	public Double add(Double n0, Double n1) {
		return n0 + n1;
	}

	public Double multiply(Double n0, Double n1) {
		return n0 * n1;
	}

	public Double divide(Double n0, Double n1) {
		return n0 / n1;
	}

	public Double negate(Double n) {
		return -n;
	}

	public Double zero() {
		return 0.0;
	}

	public Double one() {
		return 1.0;
	}

	public Double increment(Double n) {
		return n + 1;
	}

	public Double decrement(Double n) {
		return n - 1;
	}
}