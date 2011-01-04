package net.sf.staccatocommons.io.serialization;


public class XStreamXmlSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new XStreamXmlSerializationManager();
	}

}
