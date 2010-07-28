package net.sf.staccato.commons.lang;

public class IsNonNull {

	public static <T> T orElse(T value, T ifNull) {
		return value != null ? value : ifNull;
	}

	public static <T> T orElse(T value, Provider<T> ifNull) {
		return value != null ? value : ifNull.value();
	}

	public static <T> Option<T> orNone(T value) {
		return Option.nullToNone(value);
	}
}
