package net.sf.staccatocommons.collections.internal;

import static net.sf.staccatocommons.lang.tuple.Tuple.*;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.lang.function.Function2;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public final class ToPair<A, B> extends Function2<A, B, Pair<A, B>> {

	private static final Function2 INSTANCE = new ToPair();

	@Override
	public Pair<A, B> apply(A arg1, B arg2) {
		return _(arg1, arg2);
	}

	/**
	 * Answers a singleton {@link ToPair}
	 * 
	 * @return the instance
	 */
	@NonNull
	public static <A, B> Function2<A, B, Pair<A, B>> getInstance() {
		return INSTANCE;
	}
}