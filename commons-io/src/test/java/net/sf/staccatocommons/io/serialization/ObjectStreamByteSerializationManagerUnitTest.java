package net.sf.staccatocommons.io.serialization;


public class ObjectStreamByteSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new ObjectStreamByteSerializationManager();
	}
}
