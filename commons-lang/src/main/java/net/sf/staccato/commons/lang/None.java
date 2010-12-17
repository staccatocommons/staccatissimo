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
package net.sf.staccato.commons.lang;

import java.util.Collections;
import java.util.Iterator;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.value.Transparent;
import net.sf.staccato.commons.lang.value.Value;

/**
 * An undefined {@link Option}, that it, and option that does not have a value
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of value - although {@link None} options have no associated
 *          value at all.
 * @see Option
 */
@Value
@Transparent
public final class None<T> extends Option<T> {

	private static final long serialVersionUID = 6950027007611799776L;

	@SuppressWarnings("unchecked")
	private static final None<?> instance = new None();

	private None() {
	}

	@Override
	public T value() {
		throw new UndefinedOptionException();
	}

	@Override
	public boolean isDefined() {
		return false;
	}

	@Override
	public T valueOrElse(T ifUndefined) {
		return ifUndefined;
	}

	@Override
	public T valueOrElse(@NonNull Provider<? extends T> ifUndefined) {
		return ifUndefined.value();
	}

	@Override
	public void ifDefined(Executable<T> block) {

	}

	@Override
	public T valueOrNull() {
		return null;
	}

	public Iterator<T> iterator() {
		return Collections.<T> emptyList().iterator();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean contains(Object element) {
		return false;
	}

	@NonNull
	public static <T> None<T> none() {
		return (None<T>) instance;
	}

	@Override
	public String toString() {
		return "None";
	}

}