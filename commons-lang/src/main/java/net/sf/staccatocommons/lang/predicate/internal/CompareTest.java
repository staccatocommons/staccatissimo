package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Evaluable2;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class CompareTest<A extends Comparable<A>> implements Evaluable2<A, A> {

	private static final Evaluable2 INSTANCE = new CompareTest();

	public boolean eval(A arg0, A arg1) {
		return arg0 == null && arg1 == null || arg0.compareTo(arg1) == 0;
	}

	/**
	 * @return a constant instance
	 */
	@NonNull
	public static <A extends Comparable<A>> Evaluable2<A, A> compareTest() {
		return INSTANCE;
	}
}