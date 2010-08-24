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
package net.sf.staccato.commons.lang.value;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * {@link ValueObject} is aimed to be used as superclass for those object that
 * require to implement {@link Value}
 * 
 * {@link ValueObject} implements {@link #toString()}, {@link #equals(Object)}
 * and {@link HashCodeBuilder} methods using reflective methods from Apache
 * commons lang Builders.
 * 
 * @author flbulgarelli
 * 
 */
public class ValueObject implements Value {

	private static final long serialVersionUID = -5493064104089778188L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
			this,
			NamedTupleToStringStyle.getInstance());
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
