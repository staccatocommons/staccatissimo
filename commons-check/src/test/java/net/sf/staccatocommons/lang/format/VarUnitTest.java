package net.sf.staccatocommons.lang.format;

import static org.junit.Assert.assertNotNull;
import net.sf.staccatocommons.check.format.Var;

import org.junit.Test;

/**
 * Test for {@link Var}
 * 
 * @author flbulgarelli
 * 
 */
public class VarUnitTest {

	@Test
	public void testFormatStringObject() {
		assertNotNull(Var.format("myVar", "hello"));
	}

	@Test
	public void testFormatStringStringObjectString() {
		assertNotNull(Var.format("Hello, ", "myVar", (Object) "hello"));
	}

	@Test
	public void testFormatStringStringObject() {
		assertNotNull(Var.format("Hello", "myVar", 5, "end"));
	}

}
