package net.sf.staccatocommons.io.serialization;

import net.sf.staccatocommons.io.serialization.ObjectStreamByteSerializationManager;
import net.sf.staccatocommons.io.serialization.SerializationManager;

public class ObjectStreamByteSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new ObjectStreamByteSerializationManager();
	}
}
