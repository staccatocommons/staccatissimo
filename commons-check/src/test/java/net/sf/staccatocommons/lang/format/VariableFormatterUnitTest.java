package net.sf.staccatocommons.lang.format;

import static org.junit.Assert.assertEquals;
import net.sf.staccatocommons.check.format.VariableFormatter;

import org.junit.Test;

/**
 * Test for {@link VariableFormatter}
 * 
 * @author flbulgarelli
 * 
 */
public class VariableFormatterUnitTest {

	@Test
	public void testFormatStringObject() {
		VariableFormatter formatter = new VariableFormatter();
		assertEquals("myVar=[76]", formatter.format("myVar", 76));
		assertEquals("myVar=[null]", formatter.format("myVar", null));
	}

	@Test
	public void testFormatStringStringObject() {
		VariableFormatter formatter = new VariableFormatter();
		assertEquals("The content is myVar=[76]", //
			formatter.format("The content is", "myVar", 76));

	}

	@Test
	public void testFormatStringStringObjectString() {
		VariableFormatter formatter = new VariableFormatter();
		assertEquals("The property myVar=[76] looks bad", //
			formatter.format("The property", "myVar", 76, "looks bad"));
	}

}
