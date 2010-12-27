package net.sf.staccato.commons.lang.predicate.internal;

/**
 * @author flbulgarelli
 * 
 */
public final class EqualsIgnoreCase extends NonAnnonymousPredicate<String> {
	private static final long serialVersionUID = -1136105243595734514L;
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
	public boolean eval(String argument) {
		return argument.equalsIgnoreCase(value);
	}
}