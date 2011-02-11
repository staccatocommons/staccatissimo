/*
 Copyright (c) 2010, The Staccato-Commons Team   
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang;

import java.io.Serializable;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.SizeAware;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.ConditionallySerializable;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.ConditionallyImmutable;
import net.sf.staccatocommons.restrictions.value.Value;

/**
 * <p>
 * {@link Option} represent optional values, that can either be instances of
 * {@link Some} or {@link None}.
 * </p>
 * There are three possible scenarios where {@link Option} type should be used
 * <ul>
 * <li>When a method may fail without throwing an exception and returns an
 * unusable value. Traditionally, this problem is solved using null as centinel
 * value. For example, {@link Map#get(Object)}, returns null if there is no
 * object mapped for the given key. This kind of code can be tricky, as clients
 * may ignore this and continue using the returned value without checking it.
 * What is more, such client code can even consider null values valid and
 * usable, and then a return value of null has an ambiguous meaning. Using
 * {@link Option}s removes ambiguity.</li>
 * <li>When there are uninitialized attributes, but null is already a valid
 * initialized value, and should not be treated the same way. Traditionally,
 * this is solved with extra boolean variables, but this is error prone. Again,
 * use {@link Option} instead, which traits nulls and not-set values not the
 * same way</li>
 * <li>When properties may be unset as a valid state, and null is not a valid
 * value for the property if set. Client code may forget to check this
 * condition, and causing {@link NullPointerException}.</li>
 * </ul>
 * 
 * <p>
 * Option subclasses redefine {@link #equals(Object)} and {@link #hashCode()},
 * so that two instances are equal as long they values are equal, or as long
 * they are undefined.
 * </p>
 * 
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of optional value
 * 
 */
@Value
@ConditionallyImmutable
@ConditionallySerializable
public abstract class Option<T> implements Thunk<T>, Iterable<T>, SizeAware, ContainsAware<T>,
	Serializable {

	private static final long serialVersionUID = -4635925023376621559L;

	/**
	 * Package level visibility to allow subclasses inside package
	 */
	Option() {}

	/**
	 * Gets the optional value, if defined, or throws an
	 * {@link NoSuchElementException}, otherwise.
	 * 
	 * @return The optional value. This value is nullable, if client code
	 *         considers null as possible, valid values. Non null otherwise.
	 *         Please prefer the second approach, as normally, null values are
	 *         there in code to represent optional data, so nullable values in
	 *         optional values is in most scenarios completely redundant,
	 *         unnecessary an error prone.
	 * @throws NoSuchElementException
	 *           if this option is undefined, and thus there is no value.
	 */
	public abstract T value() throws NoSuchElementException;

	/**
	 * Returns if the value has been defined or not.
	 * 
	 * @return true is the value is defined. False otherwise
	 */
	public abstract boolean isDefined();

	/**
	 * 
	 * @return !{@link #isDefined()}
	 */
	public boolean isUndefined() {
		return !isDefined();
	}

	/**
	 * Factory method for creating an undefined value. This method guarantees to
	 * return always the same instance.
	 * 
	 * @param <T>
	 *          the type of optional value
	 * @return A constant {@link None} instance
	 */
	@Constant
	public static <T> None<T> none() {
		return None.none();
	}

	/**
	 * Returns the value of this {@link Option}, or the provided object if
	 * undefined
	 * 
	 * @param other
	 *          the return value in case this {@link Option} is undefined
	 * @return <code>this.value()</code> if defined, other <code>otherwise</code>
	 */
	public abstract T valueOrElse(T other);

	/**
	 * Returns the value of this {@link Option}, or the provided object if
	 * undefined
	 * 
	 * @param other
	 *          the thunk of the return value in case this {@link Option} is
	 *          undefined
	 * @return <code>this.value()</code> if defined, other.value()
	 *         <code>otherwise</code>
	 */
	public abstract T valueOrElse(Thunk<? extends T> other);

	/**
	 * Returns the value of this {@link Option}, or <code>null</code>, if
	 * undefined.
	 * 
	 * @return <code>this.value()</code> if defined, or <code>null</code>,
	 *         otherwise
	 */
	public abstract T valueOrNull();

	/**
	 * Executed the given block if this option is defined
	 * 
	 * @param block
	 */
	public abstract void ifDefined(@NonNull Executable<T> block);

	/**
	 * Factory method for creating defined values.This method does not guarantee
	 * either to return the same or different instances for the same argument.
	 * 
	 * @param <T>
	 *          the type of optional value
	 * @param value
	 *          May be null (although is discouraged). See {@link #value()} for
	 *          details
	 * @return Some(value)
	 */
	public static <T> Some<T> some(T value) {
		return Some.some(value);
	}

	/**
	 * Factory method for creating defined, null values. This method is just a
	 * shortcut for some(null). Guaranteed to return always the same instance.
	 * 
	 * @param <T>
	 * @return A shared null instance
	 */
	public static <T> Some<T> someNull() {
		return Some.someNull();
	}

	/**
	 * Creates an option (defined or not), mapping null values to undefined
	 * options, and non nulls to defined option. This method lets client code to
	 * convert between null-as-undefined and
	 * 
	 * @param <T>
	 * @param value
	 * @return <code>value != null ? Option.some(value) : Option.none()</code>
	 */
	public static <T> Option<T> nullToNone(T value) {
		if (value == null)
			return none();
		return some(value);
	}

}
