package net.sf.staccato.commons.lang.predicate.internal;

import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * @author flbulgarelli
 * 
 */
public final class EqualsIgnoreCase extends Predicate<String> {
	private final String value;

	/**
	 * Creates a new {@link EqualsIgnoreCase}
	 */
	public EqualsIgnoreCase(String value) {
		this.value = value;
	}

	@Override
	public boolean eval(String arg0) {
		return value.equalsIgnoreCase(arg0);
	}
}