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

package net.sf.staccatocommons.restrictions.value;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.Restriction;

/**
 * {@link Immutable}s are objects whose state, if any, can not be mutated in any
 * way. Such objects either have no attributes - stateless - or are
 * {@link Unmodifiable} whose attributes are primitive or immutable. Examples of
 * immutable objects are strings and {@code Collections.emptyList()}.
 * <p>
 * * Classes annotated as {@link Immutable} indicate that all their instances
 * have such property. Methods annotated as {@link Immutable} denote that all
 * the objects returned by them have such property.
 * </p>
 * <p>
 * {@link Immutable}s are inherently thread-safe.
 * </p>
 * 
 * @author fbulgarelli
 * @see Restriction
 */
@Documented
@Inherited
@Restriction
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Immutable {

}
