package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class CompareTest<A extends Comparable<A>> implements Evaluable2<A, A> {

	public boolean eval(A arg0, A arg1) {
		if (arg0 == null)
			return arg1 == null;
		if (arg1 == null)
			return false;
		return arg0.compareTo(arg1) == 0;
	}

	/**
	 * @return a constant instance
	 */
	@NonNull
	@Constant
	public static <A extends Comparable<A>> Evaluable2<A, A> compareTest() {
		return new CompareTest();
	}
}