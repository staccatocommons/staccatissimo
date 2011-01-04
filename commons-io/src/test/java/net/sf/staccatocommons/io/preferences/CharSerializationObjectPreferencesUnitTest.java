package net.sf.staccatocommons.io.preferences;

import static net.sf.staccatocommons.lang.tuple.Tuple._;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.sf.staccatocommons.io.serialization.CharSerializationManager;
import net.sf.staccatocommons.io.serialization.XStreamXmlSerializationManager;
import net.sf.staccatocommons.lang.tuple.Triple;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class CharSerializationObjectPreferencesUnitTest extends
	JUnit4MockObjectTestCase {

	private static final String KEY = "key";
	private CharSerializationObjectPreferences objectPreferences;
	private Preferences preferences;
	private CharSerializationManager manager;

	@Before
	public void setup() {
		setImposteriser(ClassImposteriser.INSTANCE);
		preferences = mock(Preferences.class);
		manager = new XStreamXmlSerializationManager(new XStream());
		objectPreferences = new CharSerializationObjectPreferences(
			preferences,
			manager);
	}

	@Test
	public void testGetPreferences() {
		assertSame(preferences, objectPreferences.getNode());
	}

	// TODO bad test. provide binding capabilities. it is not actually of interest
	// of this test to know that the string is escaped
	@Test
	public void testPutAndGet() {
		final Calendar calendar = new GregorianCalendar(2010, Calendar.MAY, 25);
		final String calendarXML = manager.serialize(calendar);
		checking(new Expectations() {
			{
				one(preferences).put(KEY, calendarXML);
				one(preferences).get(with(KEY), with(any(String.class)));
				will(returnValue(calendarXML));
			}
		});
		objectPreferences.put(KEY, calendar);
		assertEquals(calendar, objectPreferences.get(KEY, null));

	}

	@Test
	public void testGetInvallidValue() throws Exception {
		checking(new Expectations() {
			{
				one(preferences).get(KEY, null);
				will(returnValue("fdsfsdfsd<>!!"));
			}
		});
		Triple<Integer, Integer, String> defaultValue = _(90, 56, "Hello");
		Triple<Integer, Integer, String> value = objectPreferences.get(
			KEY,
			defaultValue);
		assertEquals(defaultValue, value);
	}

	@Test
	public void testGetUnsetValue() throws Exception {
		checking(new Expectations() {
			{
				one(preferences).get(KEY, null);
				will(returnValue(null));
			}
		});
		Triple<Integer, Integer, String> defaultValue = _(90, 56, "Bye");
		Triple<Integer, Integer, String> value = objectPreferences.get(
			KEY,
			defaultValue);
		assertEquals(defaultValue, value);
	}

	@Test
	public void testFlush() throws BackingStoreException {
		checking(new Expectations() {
			{
				one(preferences).flush();
			}
		});
		objectPreferences.flush();
	}

	@Test
	public void testSync() throws BackingStoreException {
		checking(new Expectations() {
			{
				one(preferences).sync();
			}
		});
		objectPreferences.sync();
	}

	@Test
	public void testRemove() {
		checking(new Expectations() {
			{
				one(preferences).remove(KEY);
			}
		});
		objectPreferences.remove(KEY);
	}

}
