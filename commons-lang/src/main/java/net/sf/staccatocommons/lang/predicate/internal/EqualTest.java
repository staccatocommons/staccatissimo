package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.restriction.Constant;

import org.apache.commons.lang.ObjectUtils;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class EqualTest<A> implements Evaluable2<A, A> {

	public boolean eval(A arg0, A arg1) {
		return ObjectUtils.equals(arg0, arg1);
	}

	/**
	 * @return a constant instance
	 */
	@NonNull
	@Constant
	public static <A> Evaluable2<A, A> equalTest() {
		return new EqualTest();
	}
}