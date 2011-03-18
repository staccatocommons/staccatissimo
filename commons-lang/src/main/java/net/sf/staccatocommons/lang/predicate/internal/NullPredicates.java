package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.defs.predicate.Predicate;

/**
 * @author flbulgarelli
 */
public final class NullPredicates {
	private static final Predicate NULL = new Null(), NOT_NULL = new NotNull();

	/**
	 * @return the notNull
	 */
	public static Predicate notNull() {
		return NOT_NULL;
	}

	/**
	 * @return the null_
	 */
	public static Predicate null_() {
		return NULL;
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
			return NULL;
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
			return NOT_NULL;
		}

	}

}