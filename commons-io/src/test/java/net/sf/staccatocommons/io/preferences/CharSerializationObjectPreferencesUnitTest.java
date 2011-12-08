/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.io.preferences;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.sf.staccatocommons.defs.tuple.Tuple3;
import net.sf.staccatocommons.io.serialization.CharSerializationManager;
import net.sf.staccatocommons.io.serialization.XStreamXmlSerializationManager;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/**
 * Test for {@link CharSerializationObjectPreferences}
 * 
 * @author flbulgarelli
 * 
 */
public class CharSerializationObjectPreferencesUnitTest extends JUnit4MockObjectTestCase {

  private static final String KEY = "key";
  private CharSerializationObjectPreferences objectPreferences;
  private Preferences preferences;
  private CharSerializationManager manager;

  /***/
  @Before
  public void setup() {
    setImposteriser(ClassImposteriser.INSTANCE);
    preferences = mock(Preferences.class);
    manager = new XStreamXmlSerializationManager(new XStream());
    objectPreferences = new CharSerializationObjectPreferences(preferences, manager);
  }

  /** test for {@link ObjectPreferences#getNode()} */
  @Test
  public void testGetPreferences() {
    assertSame(preferences, objectPreferences.getNode());
  }

  /** Test for {@link ObjectPreferences#put(String, Object)} */
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

  /** Test for {@link ObjectPreferences#get(String, Object)} */
  @Test
  public void testGetInvallidValue() throws Exception {
    checking(new Expectations() {
      {
        one(preferences).get(KEY, null);
        will(returnValue("fdsfsdfsd<>!!"));
      }
    });
    Tuple3<Integer, Integer, String> defaultValue = _(90, 56, "Hello");
    Tuple3<Integer, Integer, String> value = objectPreferences.get(KEY, defaultValue);
    assertEquals(defaultValue, value);
  }

  /** Test for {@link ObjectPreferences#get(String, Object)} */
  @Test
  public void testGetUnsetValue() throws Exception {
    checking(new Expectations() {
      {
        one(preferences).get(KEY, null);
        will(returnValue(null));
      }
    });
    Tuple3<Integer, Integer, String> defaultValue = _(90, 56, "Bye");
    Tuple3<Integer, Integer, String> value = objectPreferences.get(KEY, defaultValue);
    assertEquals(defaultValue, value);
  }

  /** Test for {@link ObjectPreferences#flush()} */
  @Test
  public void testFlush() throws BackingStoreException {
    checking(new Expectations() {
      {
        one(preferences).flush();
      }
    });
    objectPreferences.flush();
  }

  /** Test for {@link ObjectPreferences#sync()} */
  @Test
  public void testSync() throws BackingStoreException {
    checking(new Expectations() {
      {
        one(preferences).sync();
      }
    });
    objectPreferences.sync();
  }

  /** Test for {@link ObjectPreferences#remove(String)} */
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
