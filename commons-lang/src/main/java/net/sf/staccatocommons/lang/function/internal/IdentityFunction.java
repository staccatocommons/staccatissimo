package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class IdentityFunction<A> extends AbstractFunction<A, A> implements Serializable {

	private static final long serialVersionUID = -9042770205177366369L;

	@Override
	public A apply(A argument) {
		return argument;
	}

	@Override
	@NonNull
	public Function<A, A> nullSafe() {
		return this;
	}

	@NonNull
	@Override
	public Thunk<A> delayed(A arg) {
		return Thunks.constant(arg);
	}

	@NonNull
	@Override
	public Thunk<A> delayed(Thunk<? extends A> thunk) {
		return (Thunk<A>) thunk;
	}

	@NonNull
	@Override
	public <Tp1, Tp2> Function2<Tp1, Tp2, A> of(Applicable2<Tp1, Tp2, ? extends A> other) {
		return Functions.from(other);
	}

	@NonNull
	@Override
	public <C> Function<C, A> of(Applicable<? super C, ? extends A> other) {
		return Functions.from(other);
	}

	/**
	 * @param <I>
	 * @return a constant instance
	 */
	@Constant
	public static <I> Function<I, I> identity() {
		return new IdentityFunction();
	}

}