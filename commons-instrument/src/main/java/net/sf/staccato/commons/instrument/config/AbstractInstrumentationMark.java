package net.sf.staccato.commons.instrument.config;

import java.nio.charset.Charset;

/**
 * @author flbulgarelli
 */
public abstract class AbstractInstrumentationMark implements InstrumentationMark {

	/**
	 * @return The version number, as a class file version attribute value
	 */
	public final byte[] getMarkAttributeValue() {
		return getMark().getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * @return the version number
	 */
	public abstract String getMark();

	public abstract String getMarkAttributeName();
}