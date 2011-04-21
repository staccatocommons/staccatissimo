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

package net.sf.staccatocommons.lang;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.lang.internal.ToString;
import net.sf.staccatocommons.lang.value.BasicEquals;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.value.Immutable;
import net.sf.staccatocommons.restrictions.value.Value;

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
@Conditionally({ Immutable.class, Serializable.class })
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
	public T valueOrElse(Thunk<? extends T> ifUndefined) {
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
		return Thriterators.from(value);
	}

	public boolean contains(T element) {
		return ObjectUtils.equals(value, element);
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