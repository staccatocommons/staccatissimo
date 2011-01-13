package net.sf.staccatocommons.lang.number.internal;

import java.math.BigInteger;

import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class BigIntegerType extends AbstractNumberType<BigInteger> {

	private static final long serialVersionUID = 8595141753229390523L;
	/**
	 * An instance
	 */
	public static final BigIntegerType TYPE = new BigIntegerType();

	public BigInteger add(BigInteger n0, BigInteger n1) {
		return n0.add(n1);
	}

	public BigInteger multiply(BigInteger n0, BigInteger n1) {
		return n0.multiply(n1);
	}

	public BigInteger divide(BigInteger n0, BigInteger n1) {
		return n0.divide(n1);
	}

	public BigInteger negate(BigInteger n) {
		return n.negate();
	}

	public BigInteger zero() {
		return BigInteger.ZERO;
	}

	public BigInteger one() {
		return BigInteger.ONE;
	}
}