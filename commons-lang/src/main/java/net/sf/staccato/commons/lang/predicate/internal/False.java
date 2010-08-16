package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;


/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class False<T> extends Predicate<T> {
	private static Predicate instance = new False();

	public boolean eval(T argument) {
		return false;
	}

	/**
	 * @return the instance
	 */
	public static Predicate getInstance() {
		return instance;
	}
}