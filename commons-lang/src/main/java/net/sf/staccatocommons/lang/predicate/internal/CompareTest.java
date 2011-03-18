package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class CompareTest<A extends Comparable<A>> extends AbstractEquiv<A> {

	public boolean eval(A arg0, A arg1) {
		return arg0.compareTo(arg1) == 0;
	}

	/**
	 * @return a constant instance
	 */
	@NonNull
	@Constant
	public static <A extends Comparable<A>> Predicate2<A, A> compareTest() {
		return new CompareTest();
	}
}