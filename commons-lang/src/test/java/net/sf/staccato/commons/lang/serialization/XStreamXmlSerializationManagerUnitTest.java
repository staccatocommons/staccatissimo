package net.sf.staccato.commons.lang.serialization;

public class XStreamXmlSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new XStreamXmlSerializationManager();
	}

}
