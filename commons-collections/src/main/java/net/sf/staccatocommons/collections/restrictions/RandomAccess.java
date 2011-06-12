/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.restrictions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.staccatocommons.restrictions.Restriction;

/**
 * {@link RandomAccess} is a restriction for {@link Iterable}s taht has the same
 * meaning of the {@link java.util.RandomAccess} interface.
 * 
 * {@link RandomAccess} iterables <strong>must</strong> be {@link Repeatable},
 * thus is it not necessary to annotate them as {@link Repeatable}, as it is
 * implied.
 * 
 * @author flbulgarelli
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
@Restriction
public @interface RandomAccess {

}
