package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Evaluable2;

import org.apache.commons.lang.ObjectUtils;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class EqualityTest<A> implements Evaluable2<A, A> {

	private static final EqualityTest INSTANCE = new EqualityTest();

	public boolean eval(A arg0, A arg1) {
		return ObjectUtils.equals(arg0, arg1);
	}

	/**
	 * @return a constant instance
	 */
	@NonNull
	public static <A> Evaluable2<A, A> equalityTest() {
		return INSTANCE;
	}
}