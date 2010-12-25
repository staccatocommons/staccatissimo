package net.sf.staccato.commons.lang.block;

import org.apache.commons.lang.mutable.MutableInt;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;

/**
 * @author flbulgarelli
 * 
 */
final class IncrementAction extends CustomAction {
	/**
	 * 
	 */
	private final MutableInt mi;

	/**
	 * Creates a new {@link IncrementAction}
	 */
	IncrementAction(MutableInt mi) {
		super("increment");
		this.mi = mi;
	}

	public Object invoke(Invocation invocation) throws Throwable {
		mi.increment();
		return null;
	}
}