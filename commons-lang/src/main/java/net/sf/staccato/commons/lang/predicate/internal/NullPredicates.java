package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 */
public final class NullPredicates {
	private static Predicate null_ = new Null(), notNull = new NotNull();

	/**
	 * @return the notNull
	 */
	public static Predicate notNull() {
		return notNull;
	}

	/**
	 * @return the null_
	 */
	public static Predicate null_() {
		return null_;
	}

	private static class NotNull<T> extends NonAnnonymousPredicate<T> {
		public boolean eval(T argument) {
			return argument != null;
		}

		public Predicate<T> not() {
			return null_;
		}
	}

	private static class Null<T> extends NonAnnonymousPredicate<T> {
		public boolean eval(T argument) {
			return argument == null;
		}

		public Predicate<T> not() {
			return notNull;
		}

	}

}