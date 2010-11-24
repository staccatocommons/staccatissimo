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
package net.sf.staccato.commons.lang.check.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * An annotation that signals that an annotated can not be empty. This
 * annotation should only be applied to elements that understand a length or
 * size attribute, or length, size or isEmpty method.
 * </p>
 * 
 * @author flbulgarelli
 * @see CheckAnnotation
 */
@CheckAnnotation
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD })
public @interface NotEmpty {

	/**
	 * @return The variable name of the constrained element, or the empty string
	 *         if unspecified. This value may help tools that analyze this
	 *         annotation without access to source code.
	 */
	String value() default "";
}