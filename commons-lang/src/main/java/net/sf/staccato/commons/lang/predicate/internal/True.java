package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;


/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class True<T> extends Predicate<T> {
	private static Predicate instance = new True();

	public boolean eval(T argument) {
		return true;
	}

	/**
	 * @return the instance
	 */
	public static Predicate getInstance() {
		return instance;
	}
}