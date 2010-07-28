package net.sf.staccato.commons.lang;

import java.util.concurrent.Callable;

public class SoftException extends RuntimeException {

	private static final long serialVersionUID = 4364656280386270636L;

	public SoftException(Exception cause) {
		super(cause);
	}

	public static RuntimeException soften(Exception exception) {
		if (exception instanceof RuntimeException)
			return (RuntimeException) exception;
		return new SoftException(exception);
	}

	public static <T> T callOrSoften(Callable<T> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			throw soften(e);
		}
	}
}
