package net.sf.staccato.commons.io.serialization;

import net.sf.staccato.commons.io.serialization.SerializationManager;
import net.sf.staccato.commons.io.serialization.XStreamXmlSerializationManager;

public class XStreamXmlSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new XStreamXmlSerializationManager();
	}

}
