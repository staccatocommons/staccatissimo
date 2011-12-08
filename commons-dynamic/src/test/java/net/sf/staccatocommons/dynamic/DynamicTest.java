package net.sf.staccatocommons.dynamic;

/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */

import static org.junit.Assert.*;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.partial.EmptyAware;

import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class DynamicTest {
  /***/
  public static class Adder {
    /***/
    public int add(int x, int y) {
      return x + y;
    }
  }

  /***/
  public interface LongAdder {
    /***/
    long add(int x, int y);
  }

  /***/
  public interface IntegerAdder {
    /***/
    Integer add(int x, int y);
  }

  /***/
  public interface IntAdder {
    /***/
    int add(int x, int y);
  }

  /***/
  public static class Person {
    private final String name;
    private final Pet pet;

    /***/
    public Person(String name, Pet pet) {
      this.name = name;
      this.pet = pet;
    }

    /***/
    public Person(String name) {
      this(name, null);
    }

    /***/
    public String getName() {
      return name;
    }

    /***/
    public Pet getPet() {
      return pet;
    }

  }

  /***/
  public static class Pet {
    private final String name;

    /***/
    public Pet(String name) {
      this.name = name;
    }

    /***/
    public String getName() {
      return name;
    }
  }

  /***/
  public interface IPet {
    /***/
    String getName();
  }

  /***/
  public interface IPerson {
    /***/
    IPet getPet();
  }

  /**
   * Tests sending a message without arguments
   * 
   * @throws Exception
   */
  @Test
  public void sendNoArguments() throws Exception {
    assertEquals("Tom", Dynamics.from(new Person("Tom")).send("getName"));
  }

  /** Tests sending a message with arguments */
  @Test
  public void senedWithWrappedArguments() throws Exception {
    assertEquals(15, Dynamics.from(new Adder()).send("add", 10, 5));
  }

  /**
   * Tests sending a message with arguments that is not understood, because of
   * bad arguments counts
   */
  @Test(expected = MessageNotUnderstoodException.class)
  public void sendWithWrappedArgumentsMessageNotUnderstood() throws Exception {
    Dynamics.from(new Adder()).send("add", 10, true);
  }

  /**
   * Tests sending a message with arguments that is not understood, because of
   * bad arguments counts, using dynamicSend
   * 
   * @throws Exception
   */
  @Test
  public void dynamicSendMessageUnderstood() throws Exception {
    assertNull(Dynamics.from("hello").chainingSend("isEmpty", "156", 40).value());
  }

  /**
   * Tests chaining dynamic send
   */
  @Test
  public void dynamicSendChained() throws Exception {
    assertEquals(
      "Gardfield",
      Dynamics.from(new Person("Jon", new Pet("Gardfield"))).$("getPet").$("getName").value());
  }

  /** Test for dynamicsend */
  @Test
  public void dynammicSendReturnsNullDynamic() throws Exception {
    assertNull(Dynamics.from(new Person("Lily")).$("getPet").$("getName").value());
  }

  /***/
  @Test(expected = MessageNotUnderstoodException.class)
  public void asMessageNotUnderstood() throws Exception {
    Dynamics.from(new Person("Susy")).as(EmptyAware.class).isEmpty();
  }

  /***/
  @Test
  public void nullAs() throws Exception {
    assertNull(Dynamics.null_().as(IPet.class).getName());
    assertNull(Dynamics.null_().chainingAs(IPerson.class).getPet().getName());
  }

  /***/
  @Test
  public void asOk() throws Exception {
    assertFalse(Dynamics.from("hello").as(EmptyAware.class).isEmpty());
  }

  /** This may be a nice feature, but it is not currently supported */
  @Test(expected = ClassCastException.class)
  public void noConversionApplied() throws Exception {
    Dynamics.from(new Adder()).as(LongAdder.class).add(10, 20);
  }

  /** This may be a nice feature, but it is not currently supported */
  @Test
  public void conversionAppliedOnNull() throws Exception {
    assertEquals(0, Dynamics.null_().as(LongAdder.class).add(10, 20));
  }

  /** Test that as method performs a "shallow" cast */
  @Test(expected = ClassCastException.class)
  public void asCastException() throws Exception {
    Dynamics.from(new Person("Granny", new Pet("Sylvester"))).as(IPerson.class).getPet();
  }

  /** Tests that dynamicAs performs a "deep" cast */
  @Test
  public void dynamicAs() throws Exception {
    assertEquals(
      "Sylvester",
      Dynamics
        .from(new Person("Granny", new Pet("Sylvester")))
        .chainingAs(IPerson.class)
        .getPet()
        .getName());
  }

  /** Tests that dynamicAs works ok with primitives */
  @Test
  public void dynamicAsPrimitives() throws Exception {
    assertEquals(
      (Integer) 230,
      Dynamics.from(new Adder()).chainingAs(IntegerAdder.class).add(10, 220));
    assertEquals(230, Dynamics.from(new Adder()).chainingAs(IntAdder.class).add(10, 220));
  }

  /** Tests delayed send */
  @Test
  public void delayedSend() throws Exception {
    MutableInt mi = new MutableInt();
    Thunk<Object> thunk = Dynamics.from(mi).delayedSend("increment");
    assertEquals(mi.intValue(), 0);
    thunk.value();
    assertEquals(mi.intValue(), 1);
  }

  /***/
  @Test
  public void fromClassName() throws Exception {
    assertEquals(30, Dynamics
      .fromClassName("net.sf.staccatocommons.dynamic.DynamicTest$Adder")
      .send("add", 10, 20));
  }

}
