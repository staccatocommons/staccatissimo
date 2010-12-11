package net.sf.staccato.commons.io.serialization;

import net.sf.staccato.commons.io.serialization.ObjectStreamByteSerializationManager;
import net.sf.staccato.commons.io.serialization.SerializationManager;

public class ObjectStreamByteSerializationManagerUnitTest extends
	SerializationManagerAbstractUnitTest {

	@Override
	protected SerializationManager createSerializationManager() {
		return new ObjectStreamByteSerializationManager();
	}
}
