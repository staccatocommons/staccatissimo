package net.sf.staccato.commons.lang.predicate.internal;

import static net.sf.staccato.commons.lang.predicate.Predicates.from;
import static net.sf.staccato.commons.lang.predicate.Predicates.true_;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.block.Blocks;
import net.sf.staccato.commons.lang.predicate.Predicate;
import net.sf.staccato.commons.lang.value.NamedTupleToStringStyle;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class False<T> extends Predicate<T> {
	private static final Predicate INSTANCE = new False();

	public boolean eval(T argument) {
		return false;
	}

	/**
	 * @return the instance
	 */
	public static Predicate getInstance() {
		return INSTANCE;
	}

	@Override
	public Predicate<T> and(Evaluable<T> other) {
		return this;
	}

	@Override
	public Predicate<T> or(Evaluable<T> other) {
		return from(other);
	}

	@Override
	public Predicate<T> not() {
		return true_();
	}

	@Override
	public Executable<T> ifTrue(Executable<T> aBlock) {
		return Blocks.empty();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
			this,
			NamedTupleToStringStyle.getInstance());
	}
}