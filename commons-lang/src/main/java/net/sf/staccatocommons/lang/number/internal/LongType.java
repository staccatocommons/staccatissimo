package net.sf.staccatocommons.lang.number.internal;

import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.number.AbstractNumberType;

/**
 * @author flbulgarelli
 * 
 */
public final class LongType extends AbstractNumberType<Long> {

	/**
	 * An instance
	 */
	public static final NumberType<Long> TYPE = new LongType();

	public Long add(Long n0, Long n1) {
		return n0 + n1;
	}

	public Long multiply(Long n0, Long n1) {
		return n0 * n1;
	}

	public Long divide(Long n0, Long n1) {
		return n0 / n1;
	}

	public Long negate(Long n) {
		return -n;
	}

	public Long zero() {
		return 0L;
	}

	public Long one() {
		return 1L;
	}

	public Long increment(Long n) {
		return ++n;
	}

	public Long decrement(Long n) {
		return --n;
	}
}