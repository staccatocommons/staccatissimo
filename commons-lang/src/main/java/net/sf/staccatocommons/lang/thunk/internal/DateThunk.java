package net.sf.staccatocommons.lang.thunk.internal;

import java.util.Date;

import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 */
public class DateThunk implements Thunk<Date> {

	/** An instance */
	public static final DateThunk INSTANCE = new DateThunk();

	public Date value() {
		return new Date();
	}
}