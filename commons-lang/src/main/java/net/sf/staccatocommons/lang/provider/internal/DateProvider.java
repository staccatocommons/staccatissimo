package net.sf.staccatocommons.lang.provider.internal;

import java.util.Date;

import net.sf.staccatocommons.lang.provider.Provider;

/**
 * @author flbulgarelli
 * 
 */
public class DateProvider extends Provider<Date> {

	/** An instance */
	public static final DateProvider PROVIDER = new DateProvider();

	public Date value() {
		return new Date();
	}
}