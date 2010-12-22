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
import net.sf.staccato.commons.lang.internal.ToString;
import net.sf.staccato.commons.lang.value.BasicEquals;
import net.sf.staccato.commons.lang.value.ConditionallyImmutable;
import net.sf.staccato.commons.lang.value.Value;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A defined {@link Option}, that is, an option that holds a value.
 * 
 * @author flbulgarelli
 * @param <T>
 * @see Option
 */
@Value
@ConditionallyImmutable
public final class Some<T> extends Option<T> {

	private static final long serialVersionUID = 5981912873938772033L;

	private static final Some<?> SOME_NULL = new Some<Object>(null);
	private final T value;

	/**
	 * Creates a Some instance with the given value
	 * 
	 * @param value
	 *          the value wrapped. Nullable.
	 */
	public Some(T value) {
		this.value = value;
	}

	@Override
	public T value() {
		return value;
	}

	@Override
	public boolean isDefined() {
		return true;
	}

	@Override
	public T valueOrElse(T ifUndefined) {
		return value;
	}

	@Override
	public T valueOrElse(Provider<? extends T> ifUndefined) {
		return value;
	}

	@Override
	public T valueOrNull() {
		return value;
	}

	@Override
	public void ifDefined(@NonNull Executable<T> block) {
		block.exec(value);
	}

	public Iterator<T> iterator() {
		return Collections.singleton(value).iterator();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()//
			.append(value)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		BasicEquals be = BasicEquals.from(this, obj);
		if (be.isEqualsDone())
			return be.toEquals();
		Some<?> that = (Some<?>) obj;
		return new EqualsBuilder()//
			.append(this.value, that.value)
			.isEquals();
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(Object element) {
		return ObjectUtils.equals(value, element);
	}

	@Override
	public String toString() {
		return ToString.toString(this);
	}

	public static <T> Some<T> some(T value) {
		if (value != null)
			return new Some<T>(value);
		return someNull();
	}

	public static <T> Some<T> someNull() {
		return (Some<T>) SOME_NULL;
	}

}