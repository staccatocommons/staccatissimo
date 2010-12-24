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
package net.sf.staccato.commons.check.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccato.commons.lang.collection.EmptyAware;
import net.sf.staccato.commons.lang.restriction.Restriction;

/**
 * A check annotation that signals that the annotated element can not be empty.
 * 
 * This annotation should only be applied to elements that understand a length
 * or size attribute, or length, size or isEmpty method, like {@link String}s or
 * {@link EmptyAware}s.
 * 
 * @author flbulgarelli
 * @see Restriction
 * @see EmptyAware
 */
@Restriction
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD })
public @interface NotEmpty {

	/**
	 * @return The variable name of the constrained element, or the empty string
	 *         if unspecified. This value may help tools that analyze this
	 *         annotation without access to source code.
	 */
	String value() default "";
}
