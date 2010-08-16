package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;


/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class NotNull<T> extends Predicate<T> {
	private static Predicate instance = new NotNull();

	public boolean eval(T argument) {
		return argument != null;
	}

	/**
	 * @return the instance
	 */
	public static Predicate getInstance() {
		return instance;
	}
}