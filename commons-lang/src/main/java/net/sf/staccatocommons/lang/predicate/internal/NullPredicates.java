package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.lang.predicate.Predicate;

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
		/**
		 * 
		 */
		private static final long serialVersionUID = 876641857208937901L;

		public boolean eval(T argument) {
			return argument != null;
		}

		public Predicate<T> not() {
			return null_;
		}
	}

	private static class Null<T> extends NonAnnonymousPredicate<T> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7208581270049483766L;

		public boolean eval(T argument) {
			return argument == null;
		}

		public Predicate<T> not() {
			return notNull;
		}

	}

}