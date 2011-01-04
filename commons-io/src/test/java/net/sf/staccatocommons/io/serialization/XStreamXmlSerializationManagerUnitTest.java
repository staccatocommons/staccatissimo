package net.sf.staccatocommons.io.serialization;

import net.sf.staccatocommons.io.serialization.SerializationManager;
import net.sf.staccatocommons.io.serialization.XStreamXmlSerializationManager;

public class XStreamXmlSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new XStreamXmlSerializationManager();
	}

}
