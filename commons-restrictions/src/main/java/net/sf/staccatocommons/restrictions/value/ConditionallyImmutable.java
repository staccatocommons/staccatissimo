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
package net.sf.staccatocommons.restrictions.value;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import net.sf.staccatocommons.restrictions.Restriction;

/**
 * <p>
 * {@link ConditionallyImmutable} annotated classes are {@link Unmodifiable}s
 * whose instance can be treated as {@link Immutable} as long as their
 * attributes are {@link Immutable} too. There is no need to annotate those
 * classes as unmodifiable, as it is implied.
 * </p>
 * <p>
 * {@link ConditionallyImmutable} restriction is normally only useful on classes
 * that have as attributes generic types or interfaces that do not make any
 * assumption about immutability
 * </p>
 * <p>
 * Typical example of {@link ConditionallyImmutable} classes are Lists returned
 * by {@link Arrays#asList(Object...)}, which are immutable as long as their
 * elements are immutable too.
 * </p>
 * 
 * 
 * @author flbulgarelli
 * @see Restriction
 */
@Documented
@Inherited
@Restriction
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ConditionallyImmutable {

}
