package net.sf.staccato.commons.lang.builder;

/**
 * Exception that signals that a {@link Builder} has being used more than once,
 * but it does not support it.
 * 
 * @author flbulgarelli
 * 
 */
public class BuilderAlreadyUsedException extends IllegalStateException {

	private static final long serialVersionUID = 231498480728197116L;

	/**
	 * Creates an exception with a descriptive message, and no cause
	 */
	public BuilderAlreadyUsedException() {
		super("This builder has already being used");
	}

}
