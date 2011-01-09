package net.sf.staccatocommons.lang.number.internal;

import java.math.BigDecimal;

import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class BigDecimalType extends AbstractNumberType<BigDecimal> {

	/**
	 * An instance
	 */
	public static final BigDecimalType TYPE = new BigDecimalType();

	public BigDecimal add(BigDecimal n0, BigDecimal n1) {
		return n0.add(n1);
	}

	public BigDecimal multiply(BigDecimal n0, BigDecimal n1) {
		return n0.multiply(n1);
	}

	public BigDecimal divide(BigDecimal n0, BigDecimal n1) {
		return n0.divide(n1);
	}

	public BigDecimal negate(BigDecimal n) {
		return n.negate();
	}

	public BigDecimal zero() {
		return BigDecimal.ZERO;
	}

	public BigDecimal one() {
		return BigDecimal.ONE;
	}
}