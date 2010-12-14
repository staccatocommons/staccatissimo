package net.sf.staccato.commons.lang.predicate.internal;


/**
 * @author flbulgarelli
 * 
 */
public final class EqualsIgnoreCase extends NonAnnonymousPredicate<String> {
	private final String value;

	/**
	 * Creates a new {@link EqualsIgnoreCase}
	 * 
	 * @param value
	 */
	public EqualsIgnoreCase(String value) {
		this.value = value;
	}

	@Override
	public boolean eval(String arg0) {
		return value.equalsIgnoreCase(arg0);
	}
}