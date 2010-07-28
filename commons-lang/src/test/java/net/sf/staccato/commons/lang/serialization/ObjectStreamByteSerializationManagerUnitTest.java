package net.sf.staccato.commons.lang.serialization;

public class ObjectStreamByteSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new ObjectStreamByteSerializationManager();
	}
}
