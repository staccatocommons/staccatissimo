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
package net.sf.staccatocommons.restrictions.processing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.Restriction;

/**
 * {@link IgnoreRestrictions} is a directive for automated annotation processors,
 * indicating that {@link Restriction}s in the annotated element and its
 * descendants <strong>must not</strong> be processed
 * 
 * This annotation is incompatible with {@link ForceRestrictions} - an element
 * <strong>must not</strong> be annotated with {@link IgnoreRestrictions} and
 * {@link ForceRestrictions} at the same time
 * 
 * @author flbulgarelli
 * @see ForceRestrictions
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.TYPE })
public @interface IgnoreRestrictions {

}
