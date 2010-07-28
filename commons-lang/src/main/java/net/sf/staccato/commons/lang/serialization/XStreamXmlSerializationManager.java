/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.serialization;

import java.io.Reader;
import java.io.Writer;

import org.apache.commons.lang.SerializationException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.BaseException;

/**
 * 
 * An {@link XmlSerializationManager} that converts objects using <a
 * href="http://xstream.codehaus.org/">XStream</a>
 * 
 * @author flbulgarelli
 * 
 */
public class XStreamXmlSerializationManager extends
	AbstractCharSerializationManager implements XmlSerializationManager {

	private final XStream xstream;

	/**
	 * Creates a new XStreamSerializationManager using a new XStream()
	 */
	public XStreamXmlSerializationManager() {
		this(new XStream());
	}

	/**
	 * Creates a new {@link XStreamXmlSerializationManager} using a given
	 * {@link XStream} instance.
	 * 
	 * @param xstream
	 */
	public XStreamXmlSerializationManager(XStream xstream) {
		this.xstream = xstream;
	}

	@Override
	public <T> T deserialize(Reader reader) {
		try {
			return (T) xstream.fromXML(reader);
		} catch (BaseException e) {
			throw new SerializationException(e);
		}
	}

	@Override
	public void serialize(Object object, Writer writer) {
		try {
			xstream.toXML(object, writer);
		} catch (BaseException e) {
			throw new SerializationException(e);
		}
	}

	/**
	 * @return the underlying {@link XStream} object
	 */
	public XStream getXstream() {
		return xstream;
	}

}
